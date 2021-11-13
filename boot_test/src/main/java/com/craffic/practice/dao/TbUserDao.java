package com.craffic.practice.dao;

import com.craffic.practice.domain.TbUser;
import com.craffic.practice.vo.TbUserVo;
import org.apache.ibatis.annotations.Param;

public interface TbUserDao {
    TbUserVo selectById(@Param("id") Long id);

    Integer deleteById(@Param("id") Long id);

    Integer updateUser(TbUser user);
}
