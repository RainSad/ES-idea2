package com.sys.service.sys;

import com.core.common.utills.LevelUtil;
import com.google.common.collect.*;
import com.sys.entity.dto.AclDto;
import com.sys.entity.dto.AclModuleLevelDto;
import com.sys.entity.dto.DeptLevelDto;
import com.sys.entity.sys.SysAcl;
import com.sys.entity.sys.SysAclModule;
import com.sys.entity.sys.SysDepartment;
import com.sys.repository.sys.SysAclModuleRepositoryImp;
import com.sys.repository.sys.SysAclRepositoryImp;
import com.sys.repository.sys.SysDepartmentRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class SysTreeService {

    @Resource
    private SysDepartmentRepositoryImp sysDepartmentRepositoryImp;

    @Resource
    private SysAclModuleRepositoryImp sysAclModuleRepositoryImp;

    @Resource
    private SysCoreService sysCoreService;

    @Resource
    private SysAclRepositoryImp sysAclRepositoryImp;


    /**
     * 获取特定用户的权限列表树
     *
     * @param userId
     * @return
     */
    public List<AclModuleLevelDto> userTree(String userId) {
        //当前用户已分配的权限点
        Iterable<SysAcl> userAclList = sysCoreService.getUserAclList(userId);
        List<AclDto> aclDtoList = Lists.newArrayList();

        for (SysAcl sysAcl : userAclList) {
            AclDto aclDto = AclDto.adapt(sysAcl);
            aclDto.setChecked(true);
            aclDto.setHasAcl(true);
            aclDtoList.add(aclDto);
        }
        return aclListToTree(aclDtoList);
    }


    /**
     * 得到角色权限树
     *
     * @param roleId
     * @return
     */
    public List<AclModuleLevelDto> roleTree(String roleId) {

        //当前用户已分配的权限点
        Iterable<SysAcl> userAclList = sysCoreService.getCurrentUserAclList();

        //当前角色分配的权限点
        Iterable<SysAcl> roleAclList = sysCoreService.getRoleAclList(roleId);

        //系统所有的权限点
        Iterable<SysAcl> allAclList = sysAclRepositoryImp.findAll();

        Set<String> userAclIdSet = Sets.newHashSet();
        Set<String> roleAclIdSet = Sets.newHashSet();
        userAclList.forEach(sysAcl -> userAclIdSet.add(sysAcl.getId()));
        roleAclList.forEach(sysAcl -> roleAclIdSet.add(sysAcl.getId()));

        //存储权限点列表
        List<AclDto> aclDtoList = Lists.newArrayList();
        for (SysAcl sysAcl : allAclList) {
            AclDto aclDto = AclDto.adapt(sysAcl);
            if (userAclIdSet.contains(sysAcl.getId())) {
                aclDto.setHasAcl(true);
            }
            if (roleAclIdSet.contains(sysAcl.getId())) {
                aclDto.setChecked(true);
            }
            aclDtoList.add(aclDto);
        }

        return aclListToTree(aclDtoList);

    }

    private List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList) {

        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        List<AclModuleLevelDto> aclModuleLevelList = aclMuduleTree();
        Multimap<String, AclDto> moduleIdAclMap = ArrayListMultimap.create();
        for (AclDto acl : aclDtoList) {
            if (acl.getStatus().equals("1"))
                moduleIdAclMap.put(acl.getAclModuleId(), acl);
        }
        bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
        return aclModuleLevelList;
    }


    private void bindAclsWithOrder(List<AclModuleLevelDto> aclModuleLevelList, Multimap<String, AclDto> moduleIdAclMap) {

        if (CollectionUtils.isEmpty(aclModuleLevelList))
            return;
        for (AclModuleLevelDto dto : aclModuleLevelList) {
            List<AclDto> aclDtoList = (List<AclDto>) moduleIdAclMap.get(dto.getId());
            if (!CollectionUtils.isEmpty(aclDtoList)) {
                Collections.sort(aclDtoList, aclSeqComparator);
                dto.setAclList(aclDtoList);
            }
            bindAclsWithOrder(dto.getNodes(), moduleIdAclMap);
        }
    }


    /**
     * 得到权限模块树
     *
     * @return
     */
    public List<AclModuleLevelDto> aclMuduleTree() {
        Iterable<SysAclModule> aclModuleList = sysAclModuleRepositoryImp.findAll();
        List<AclModuleLevelDto> dtoList = Lists.newArrayList();
        for (SysAclModule aclModule : aclModuleList) {
            AclModuleLevelDto dto = AclModuleLevelDto.adapt(aclModule);
            dtoList.add(dto);
        }
        return aclModuleListToTree(dtoList);
    }

    private List<AclModuleLevelDto> aclModuleListToTree(List<AclModuleLevelDto> aclModuleList) {
        if (CollectionUtils.isEmpty(aclModuleList)) {
            return Lists.newArrayList();
        }
        //level -> [dept1, dept2, ...]
        Multimap<String, AclModuleLevelDto> levelaclModuleMap = ArrayListMultimap.create();
        List<AclModuleLevelDto> rootList = Lists.newArrayList();

        for (AclModuleLevelDto dto : aclModuleList) {
            levelaclModuleMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        //按照seq从小到大排序
        Collections.sort(rootList, Comparator.comparing(AclModuleLevelDto::getSeq));
        //递归生成树
        transformaclModuleTree(rootList, LevelUtil.ROOT, levelaclModuleMap);
        return rootList;
    }

    private void transformaclModuleTree(List<AclModuleLevelDto> aclModuleLevelList, String level, Multimap<String, AclModuleLevelDto> levelAclModuleMap) {

        for (int i = 0; i < aclModuleLevelList.size(); i++) {
            //遍历改层每个元素
            AclModuleLevelDto AclModuleLevelDto = aclModuleLevelList.get(i);
            //处理当前层级数据
            String nextLevel = LevelUtil.calculateLevel(level, AclModuleLevelDto.getId());
            //处理下一层
            List<AclModuleLevelDto> tempAclModuleList = (List<AclModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempAclModuleList)) {
                //排序
                Collections.sort(tempAclModuleList, aclModuleSeqComparator);
                //设置下一层部门
                AclModuleLevelDto.setNodes(tempAclModuleList);
                //进入下一层处理
                transformaclModuleTree(tempAclModuleList, nextLevel, levelAclModuleMap);
            }
        }
    }

    /**
     * 得到部门递归树
     *
     * @return
     */
    public List<DeptLevelDto> deptTree() {
        Iterable<SysDepartment> deptList = sysDepartmentRepositoryImp.findAll();

        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDepartment dept : deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }


    private List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList) {
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        //level -> [dept1, dept2, ...]
        Multimap<String, DeptLevelDto> leveldeptMap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for (DeptLevelDto dto : deptLevelList) {
            leveldeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        //按照seq从小到大排序
        Collections.sort(rootList, Comparator.comparing(DeptLevelDto::getSeq));
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, leveldeptMap);
        return rootList;
    }

    private void transformDeptTree(List<DeptLevelDto> deptLevelList, String level, Multimap<String, DeptLevelDto> levelDeptMap) {

        for (int i = 0; i < deptLevelList.size(); i++) {
            //遍历改层每个元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            //处理当前层级数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            //处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempDeptList)) {
                //排序
                Collections.sort(tempDeptList, deptSeqComparator);
                //设置下一层部门
                deptLevelDto.setNodes(tempDeptList);
                //进入下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    private Comparator<DeptLevelDto> deptSeqComparator = Comparator.comparing(DeptLevelDto::getSeq);

    private Comparator<AclModuleLevelDto> aclModuleSeqComparator = Comparator.comparing(AclModuleLevelDto::getSeq);

    private Comparator<AclDto> aclSeqComparator = Comparator.comparing(AclDto::getSeq);
}
