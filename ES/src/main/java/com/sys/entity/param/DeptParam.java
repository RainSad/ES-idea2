package com.sys.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
public class DeptParam {

    private String id;

    @NotBlank(message = "部门名称不能为空")
    @Length(max = 15, min = 2, message = "部门名称需要在2-15个字之间")
    private String name;

    private String parentId = "0";

    @NotNull(message = "展示顺序不可以为空")
    private Integer seq;

    @Length(max = 150, message = "备注长度需要在150个字之内")
    private String remark;

    private String deptPhone;

    private String deptAddress;

    private String ip;

}
