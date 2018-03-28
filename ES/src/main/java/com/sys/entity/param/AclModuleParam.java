package com.sys.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AclModuleParam {

    private String id;

    @NotBlank(message = "权限模块名称不能为空！")
    @Length(min = 2, max = 64, message = "权限模块名称需要在2-64字节之间")
    private String name;

    private String parentId = "0";

    @NotNull(message = "权限模块展示顺序不能为空")
    private String seq;

    @Length(max = 255, message = "权限模块备注需小于255字节")
    private String remark;

    @Length(min = 1, max = 2, message = "状态不能为空")
    private String status;
}
