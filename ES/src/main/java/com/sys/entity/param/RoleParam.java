package com.sys.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class RoleParam {

    private String id;

    @NotBlank(message = "角色名称不能为空！")
    @Length(min = 2, max = 64, message = "长度需要在2-64个字符之类！")
    private String roleName;

    @Min(value = 1, message = "权限类型不合法")
    @Max(value = 2, message = "权限类型不合法")
    private String type;

    @NotNull(message = "状态值不能为空！")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 2, message = "角色状态不合法")
    private String status;

    @Length(min = 0, max = 255, message = "权限说明要在0-255个字符之间！")
    private String remark;
}
