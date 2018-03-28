package com.sys.entity.dto;

import com.google.common.collect.Lists;
import com.sys.entity.sys.SysAclModule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;
@Getter
@Setter
@ToString
public class AclModuleLevelDto extends SysAclModule {
    private List<AclModuleLevelDto> nodes = Lists.newArrayList();

    public static AclModuleLevelDto adapt(SysAclModule sysAclModule) {
        AclModuleLevelDto dto = new AclModuleLevelDto();
        BeanUtils.copyProperties(sysAclModule, dto);
        return dto;
    }
}
