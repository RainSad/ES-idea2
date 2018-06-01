package com.sys.service.sys;

import com.google.common.collect.Lists;
import com.sys.entity.sys.SysUser;
import com.sys.repository.sys.SysRoleUserRepositoryImp;
import com.sys.repository.sys.SysUserRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色用户service类
 */
@Service
public class SysRoleUserService {

    @Resource
    private SysRoleUserRepositoryImp sysRoleUserRepositoryImp;

    @Resource
    private SysUserRepositoryImp sysUserRepositoryImp;

    public Iterable<SysUser> getListByRoleId(String roleId) {

        List<String> userIdList = sysRoleUserRepositoryImp.getUserIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserRepositoryImp.findAll(userIdList);
    }

}
