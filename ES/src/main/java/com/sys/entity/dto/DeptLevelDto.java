package com.sys.entity.dto;

import com.sys.entity.sys.SysDepartment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDepartment {

    private List<DeptLevelDto> nodes = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDepartment dept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }


}
