package com.sys.service.sys;

import com.core.common.exception.ParamException;
import com.core.common.utills.IdToolUtil;
import com.core.common.utills.IpUtil;
import com.core.common.webUtils.BeanValidator;
import com.core.common.webUtils.RequestHolder;
import com.google.common.base.Preconditions;
import com.sys.entity.param.AclParam;
import com.sys.entity.sys.SysAcl;
import com.sys.repository.sys.SysAclRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysAclService {

    @Resource
    private SysAclRepositoryImp sysAclRepositoryImp;


    public void save(AclParam aclParam) {
        BeanValidator.check(aclParam);
        if (checkExist(aclParam.getAclModuleId(), aclParam.getName(), null)) {
            throw new ParamException("当前权限模块下，存在相同名称的权限点");
        }

        SysAcl sysAcl = SysAcl.builder()
                .name(aclParam.getName())
                .aclModuleId(aclParam.getAclModuleId())
                .url(aclParam.getUrl())
                .type(aclParam.getType())
                .status(aclParam.getStatus())
                .seq(aclParam.getSeq())
                .remark(aclParam.getRemark())
                .build();
        sysAcl.setCode(gennerateCode());
        sysAcl.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysAcl.setOperateTime(new Date());
        sysAcl.setOperator(RequestHolder.getCurrentUser().getUsername());

        sysAclRepositoryImp.save(sysAcl);

    }


    public void update(AclParam aclParam) {
        BeanValidator.check(aclParam);
        if (checkExist(aclParam.getAclModuleId(), aclParam.getName(), aclParam.getId())) {
            throw new ParamException("当前权限模块下，存在相同名称的权限点！");
        }

        SysAcl before = sysAclRepositoryImp.findOne(aclParam.getId());
        Preconditions.checkNotNull(before, "待更新的权限点不存在！");

        SysAcl after = SysAcl.builder()
                .id(aclParam.getId())
                .name(aclParam.getName())
                .aclModuleId(aclParam.getAclModuleId())
                .url(aclParam.getUrl())
                .type(aclParam.getType())
                .status(aclParam.getStatus())
                .seq(aclParam.getSeq())
                .remark(aclParam.getRemark())
                .build();
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        after.setOperator(RequestHolder.getCurrentUser().getUsername());

        sysAclRepositoryImp.save(after);
    }

    public boolean checkExist(String aclModuleId, String name, String id) {

        return StringUtils.isEmpty(id) ? sysAclRepositoryImp.checkAclNameIsExistWithBlankId(aclModuleId, name) > 0 : sysAclRepositoryImp.checkAclNameIsExist(aclModuleId, name, id) > 0;

    }

    private String gennerateCode() {
        return IdToolUtil.getUUID();
    }
}
