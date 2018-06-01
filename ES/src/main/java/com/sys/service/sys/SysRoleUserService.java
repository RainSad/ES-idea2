package com.sys.service.sys;

import com.core.common.utills.IpUtil;
import com.core.common.webUtils.RequestHolder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sys.entity.sys.SysRoleUser;
import com.sys.entity.sys.SysUser;
import com.sys.repository.sys.SysRoleUserRepositoryImp;
import com.sys.repository.sys.SysUserRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 角色用户service类
 */
@Service
public class SysRoleUserService {

    @Resource
    private SysRoleUserRepositoryImp sysRoleUserRepositoryImp;

    @Resource
    private SysUserRepositoryImp sysUserRepositoryImp;

    /**
     * 通过角色id得到用户列表
     *
     * @param roleId
     * @return
     */
    public Iterable<SysUser> getListByRoleId(String roleId) {

        List<String> userIdList = sysRoleUserRepositoryImp.getUserIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserRepositoryImp.findAll(userIdList);
    }

    /**
     * 角色用户对应关系修改
     *
     * @param roleId
     * @param userIdsList
     */
    public void changeRoleUsers(String roleId, List<String> userIdsList) {

        List<String> originUserIdList = sysRoleUserRepositoryImp.getUserIdListByRoleId(roleId);
        if (originUserIdList.size() == userIdsList.size()) {
            Set<String> originUserIdsSet = Sets.newHashSet(originUserIdList);
            Set<String> userIdsSet = Sets.newHashSet(userIdsList);
            originUserIdsSet.removeAll(userIdsSet);
            if (CollectionUtils.isEmpty(originUserIdsSet)) {
                return;
            }
        }
        updateRoleUsers(roleId, userIdsList);
    }

    @Transactional
    public void updateRoleUsers(String roleId, List<String> userIdList) {

        //删除旧权限
        sysRoleUserRepositoryImp.deleteByRoleId(roleId);
        //如果权限点id为空，则不做任何操作
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        //存储权限点列表，后面批量插入
        List<SysRoleUser> sysRoleUsers = Lists.newArrayList();
        //利用java8的函数接口
        userIdList.forEach(id -> {
            SysRoleUser sysRoleUser = SysRoleUser.builder()
                    .roleId(roleId)
                    .userId(id)
                    .operator(RequestHolder.getCurrentUser().getUsername())
                    .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()))
                    .operateTime(new Date())
                    .build();
            sysRoleUsers.add(sysRoleUser);
        });
        //增加新权限
        sysRoleUserRepositoryImp.save(sysRoleUsers);
    }
}
