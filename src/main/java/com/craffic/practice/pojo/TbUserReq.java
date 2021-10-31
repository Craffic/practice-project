package com.craffic.practice.pojo;

import com.craffic.practice.common.BaseEntity;
import com.craffic.practice.util.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TbUserReq extends BaseEntity {

    private String userId;

    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
//    @Length(min = 6, max = 20, message = "密码长度必须介于 6 和 20 之间")
    private String password;

    /**
     * 手机号码
     * 使用RegexpUtils.phone的格式验证
     */
//    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;

    /**
     * 邮箱
     * 使用RegexpUtils.EMAIL的格式验证邮箱
     */
//    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
}