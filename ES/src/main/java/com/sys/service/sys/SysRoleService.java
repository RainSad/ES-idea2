package com.sys.service.sys;

import com.core.common.exception.ParamException;
import com.core.common.utills.IpUtil;
import com.core.common.webUtils.BeanValidator;
import com.core.common.webUtils.RequestHolder;
import com.google.common.base.Preconditions;
import com.sys.entity.param.RoleParam;
import com.sys.entity.sys.SysRole;
import com.sys.repository.sys.SysRoleRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleService {

    @Resource
    private SysRoleRepositoryImp sysRoleRepositoryImp;


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

    public Iterable<SysRole> getAll() {

        return sysRoleRepositoryImp.findAll();
    }


    private boolean checkExist(String name, String id) {

        return StringUtils.isEmpty(id) ? sysRoleRepositoryImp.checkRoleIsExistWithBlankId(name) > 0 : sysRoleRepositoryImp.checkRoleIsExist(name, id) > 0;

    }
}
