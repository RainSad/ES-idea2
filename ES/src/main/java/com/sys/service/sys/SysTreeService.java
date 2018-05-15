package com.sys.service.sys;

import com.core.common.utills.LevelUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.sys.entity.dto.AclModuleLevelDto;
import com.sys.entity.dto.DeptLevelDto;
import com.sys.entity.sys.SysAclModule;
import com.sys.entity.sys.SysDepartment;
import com.sys.repository.sys.SysAclModuleRepositoryImp;
import com.sys.repository.sys.SysDepartmentRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SysTreeService {

    @Resource
    private SysDepartmentRepositoryImp sysDepartmentRepositoryImp;

    @Resource
    private SysAclModuleRepositoryImp sysAclModuleRepositoryImp;

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
}
