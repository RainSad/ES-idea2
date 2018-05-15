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
public class AclParam {

    private String id;

    @NotBlank(message = "权限点名称不能为空！")
    @Length(min = 2, max = 32, message = "长度需要在2-63个字符之类！")
    private String name;

    @NotNull(message = "必须指定权限模块！")
    private String aclModuleId;

    @Length(min = 6, max = 64, message = "权限点url长度需要在6-256个字符之间")
    private String url;

    @NotNull(message = "必须指定权限点的类型！")
    @Length(min = 0, max = 2, message = "权限点类型不合法")
    private String type;

    @NotNull(message = "必须指定权限点的状态！")
    @Length(min = 1, max = 2, message = "权限点状态不合法")
    private String status;

    @NotNull(message = "必须指定权限点展示顺序！")
    private String seq;

    @Length(min = 0, max = 255, message = "权限说明要在0-255个字符之间！")
    private String remark;
}
