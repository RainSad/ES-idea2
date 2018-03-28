package com.sys.entity.resdata;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TestVo {

    @NotBlank
    private String notNull;

    @NotNull
    private Integer nots;
}
