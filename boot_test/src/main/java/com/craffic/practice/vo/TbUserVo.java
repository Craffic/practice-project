package com.craffic.practice.vo;

import com.craffic.practice.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class TbUserVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String phone;
    private String email;
}