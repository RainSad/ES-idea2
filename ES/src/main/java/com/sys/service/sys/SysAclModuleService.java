package com.sys.service.sys;

import com.core.common.exception.ParamException;
import com.core.common.utills.IpUtil;
import com.core.common.utills.LevelUtil;
import com.core.common.webUtils.BeanValidator;
import com.core.common.webUtils.RequestHolder;
import com.google.common.base.Preconditions;
import com.sys.entity.param.AclModuleParam;
import com.sys.entity.sys.SysAclModule;
import com.sys.repository.sys.SysAclModuleRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class SysAclModuleService {

    @Autowired
    SysAclModuleRepositoryImp sysAclModuleRepositoryImp;

    public void save(AclModuleParam param) {
        BeanValidator.check(param);
        if (checkAclModuleNameExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同的权限某块");
        }
        SysAclModule sysAclModule = SysAclModule.builder()
                .id(param.getId())
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .status(param.getStatus())
                .build();
        sysAclModule.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysAclModule.setOperateTime(new Date());
        sysAclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysAclModule.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        sysAclModuleRepositoryImp.save(sysAclModule);

    }

    public void update(AclModuleParam param) {
        BeanValidator.check(param);
        if (checkAclModuleNameExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同的权限某块");
        }
        SysAclModule before = sysAclModuleRepositoryImp.findOne(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在!");
        SysAclModule after = SysAclModule.builder()
                .id(param.getId())
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .status(param.getStatus())
                .build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateTime(new Date());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        updateWithChild(before, after);
    }

    @Transactional
    public void updateWithChild(SysAclModule before, SysAclModule after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel() + "." + before.getId();
        if (!newLevelPrefix.equals(oldLevelPrefix)) {
            List<SysAclModule> aclModuleList = sysAclModuleRepositoryImp.findByLevelIsLike(oldLevelPrefix + ".%");
            if (!CollectionUtils.isEmpty(aclModuleList)) {
                for (SysAclModule aclModule : aclModuleList) {
                    String level = aclModule.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        aclModule.setLevel(level);
                    }
                }
                sysAclModuleRepositoryImp.save(aclModuleList);
            }
        }
        sysAclModuleRepositoryImp.save(after);
    }

    private boolean checkAclModuleNameExist(String parentId, String aclModuleName, String aclModuleId) {
        return StringUtils.isEmpty(aclModuleId) ? sysAclModuleRepositoryImp.checkAclModuleNameIsExistWithBlankId(parentId, aclModuleName) > 0 : sysAclModuleRepositoryImp.checkAclModuleNameIsExist(parentId, aclModuleName, aclModuleId) > 0;
    }

    private String getLevel(String aclModuleId) {
        SysAclModule sysAclModule = sysAclModuleRepositoryImp.findOne(aclModuleId);
        if (sysAclModule == null) {
            return null;
        }
        return sysAclModule.getLevel();
    }
}
