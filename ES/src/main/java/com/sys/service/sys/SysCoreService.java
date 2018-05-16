package com.sys.service.sys;

import com.core.common.webUtils.RequestHolder;
import com.google.common.collect.Lists;
import com.sys.entity.sys.SysAcl;
import com.sys.repository.sys.SysAclRepositoryImp;
import com.sys.repository.sys.SysRoleAclRepositoryImp;
import com.sys.repository.sys.SysRoleUserRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysCoreService {

    @Resource
    private SysAclRepositoryImp sysAclRepositoryImp;

    @Resource
    private SysRoleUserRepositoryImp sysRoleUserRepositoryImp;

    @Resource
    private SysRoleAclRepositoryImp sysRoleAclRepositoryImp;


    /**
     * 得到当前用户已分配权限点列表
     *
     * @return
     */
    public Iterable<SysAcl> getCurrentUserAclList() {

        String userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);

    }


    /**
     * 得到当前角色权限点列表
     *
     * @return
     */
    public Iterable<SysAcl> getRoleAclList(String roleId) {

        List<String> aclIdList = sysRoleAclRepositoryImp.getAclIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclRepositoryImp.findAll(aclIdList);
    }

    private Iterable<SysAcl> getUserAclList(String userId) {
        if (isSuperAdmin()) {
            return sysAclRepositoryImp.findAll();
        }
        List<String> roleUsers = sysRoleUserRepositoryImp.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(roleUsers)) {
            return Lists.newArrayList();
        }
        List<String> aclIdList = sysRoleAclRepositoryImp.getAclIdListByRoleIdList(roleUsers);

        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclRepositoryImp.findAll(aclIdList);

    }

    /**
     * 是否为超级超管理员
     *
     * @return
     */
    public Boolean isSuperAdmin() {
        return true;
    }
}
