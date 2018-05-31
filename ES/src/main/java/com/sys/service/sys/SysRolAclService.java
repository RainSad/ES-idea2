package com.sys.service.sys;

import com.core.common.utills.IpUtil;
import com.core.common.webUtils.RequestHolder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sys.entity.sys.SysRoleAcl;
import com.sys.repository.sys.SysRoleAclRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysRolAclService {

    @Resource
    private SysRoleAclRepositoryImp sysRoleAclRepositoryImp;

    public void changeRoleAcls(String roleId, List<String> aclIdList) {
        List<String> originAclIdList = sysRoleAclRepositoryImp.getAclIdListByRoleIdList(Lists.newArrayList(roleId));
        if (originAclIdList.size() == aclIdList.size()) {
            Set<String> originAclIdsSet = Sets.newHashSet(originAclIdList);
            Set<String> AclIdsSet = Sets.newHashSet(aclIdList);
            originAclIdsSet.removeAll(AclIdsSet);
            if (CollectionUtils.isEmpty(originAclIdsSet)) {
                return;
            }
        }
        updateRoleAcls(roleId, aclIdList);
    }

    @Transactional
    public void updateRoleAcls(String roleId, List<String> aclIdList) {

        //删除旧权限
        sysRoleAclRepositoryImp.deleteByRoleId(roleId);
        //如果权限点id为空，则不做任何操作
        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        //存储权限点列表，后面批量插入
        List<SysRoleAcl> sysRoleAcls = Lists.newArrayList();
        //利用java8的函数接口
        aclIdList.forEach(id -> {
            SysRoleAcl sysRoleAcl = SysRoleAcl.builder()
                    .roleId(roleId)
                    .aclId(id)
                    .opertor(RequestHolder.getCurrentUser().getUsername())
                    .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()))
                    .operateTime(new Date())
                    .build();
            sysRoleAcls.add(sysRoleAcl);
        });
        //增加新权限
        sysRoleAclRepositoryImp.save(sysRoleAcls);
    }
}
