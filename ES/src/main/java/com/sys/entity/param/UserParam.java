package com.sys.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserParam {

    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不可以为空")
    @Length(min = 1, max = 32, message = "y用户名长度需要在32个字之内")
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系电话
     */
    @NotBlank(message = "电话不可以为空")
    @Length(min = 1, max = 13, message = "电话长度要在13个数字之内")
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不可以为空")
    @Length(min = 1, max = 32, message = "邮箱长度要在20个数字之内")
    private String email;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别 0不详 1男  2女
     */
    private String geneder;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * 部门id
     */
    @NotNull(message = "必须提供所在部门")
    private String deptId;

    /**
     * 1正常   0禁用 2删除
     */
    @NotNull(message = "必须指定用户状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private String status;

    /**
     * 描述
     */
    @Length(min = 0, max = 255, message = "备注长度需要在120个字以内")
    private String remark;
}
