package com.craffic.security.dao;

import com.craffic.security.domain.Role;
import com.craffic.security.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User loadUserByUserName(@Param("userName") String userName);

    List<Role> getUserRolesByUid(Integer id);

}