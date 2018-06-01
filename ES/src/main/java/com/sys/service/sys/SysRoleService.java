package com.sys.service.sys;

import com.core.common.exception.ParamException;
import com.core.common.utills.IpUtil;
import com.core.common.webUtils.BeanValidator;
import com.core.common.webUtils.RequestHolder;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sys.entity.param.RoleParam;
import com.sys.entity.sys.SysRole;
import com.sys.entity.sys.SysUser;
import com.sys.repository.sys.SysRoleAclRepositoryImp;
import com.sys.repository.sys.SysRoleRepositoryImp;
import com.sys.repository.sys.SysRoleUserRepositoryImp;
import com.sys.repository.sys.SysUserRepositoryImp;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysRoleService {

    @Resource
    private SysRoleRepositoryImp sysRoleRepositoryImp;

    @Resource
    private SysRoleUserRepositoryImp sysRoleUserRepositoryImp;

    @Resource
    private SysRoleAclRepositoryImp sysRoleAclRepositoryImp;

    @Resource
    private SysUserRepositoryImp sysUserRepositoryImp;

    /**
     * 保存角色
     *
     * @param param
     */
    public void save(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getRoleName(), null)) {
            throw new ParamException("角色名称已存在！");
        }

        SysRole sysRole = SysRole.builder()
                .roleName(param.getRoleName())
                .type(param.getType())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        sysRole.setCreateTime(new Date());
        sysRole.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysRole.setOperateTime(new Date());
        sysRole.setOperator(RequestHolder.getCurrentUser().getUsername());

        sysRoleRepositoryImp.save(sysRole);
    }

    /**
     * 更新角色
     *
     * @param param
     */
    public void update(RoleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getRoleName(), param.getId())) {
            throw new ParamException("角色名称已存在！");
        }


        SysRole before = sysRoleRepositoryImp.findOne(param.getId());
        Preconditions.checkNotNull(before, "待更新的权限点不存在！");

        SysRole after = SysRole.builder()
                .id(param.getId())
                .roleName(param.getRoleName())
                .type(param.getType())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        after.setOperator(RequestHolder.getCurrentUser().getUsername());

        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        after.setOperator(RequestHolder.getCurrentUser().getUsername());

        sysRoleRepositoryImp.save(after);

    }

    /**
     * 获取所有的角色列表
     *
     * @return
     */
    public Iterable<SysRole> getAll() {

        return sysRoleRepositoryImp.findAll();
    }

    public List<SysRole> getRoleListByUserId(String userId) {
        List<String> roleIdList = sysRoleUserRepositoryImp.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(sysRoleRepositoryImp.findAll(roleIdList));
    }

    private boolean checkExist(String name, String id) {

        return StringUtils.isEmpty(id) ? sysRoleRepositoryImp.checkRoleIsExistWithBlankId(name) > 0 : sysRoleRepositoryImp.checkRoleIsExist(name, id) > 0;

    }

    /**
     * 根据权限点id获取角色列表
     *
     * @param aclId
     * @return
     */
    public List<SysRole> getRoleListByAclId(String aclId) {
        List<String> roleIds = sysRoleAclRepositoryImp.getRoleIdsByAclId(aclId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(sysRoleRepositoryImp.findAll(roleIds));
    }

    public List<SysUser> getUserListByRoleList(List<SysRole> roleList) {
        if (CollectionUtils.isEmpty(roleList))
            return Lists.newArrayList();
        List<String> roleIds = roleList.stream().map(SysRole -> SysRole.getId()).collect(Collectors.toList());
        List<String> userIds = sysRoleUserRepositoryImp.getUserIdListByRoleIdList(roleIds);
        if (CollectionUtils.isEmpty(userIds))
            return Lists.newArrayList();
        return Lists.newArrayList(sysUserRepositoryImp.findAll(userIds));
    }

}
