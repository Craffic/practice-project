package com.craffic.practice.dao;

import com.craffic.practice.vo.TbUserVo;
import org.apache.ibatis.annotations.Param;

public interface TbUserDao {
    TbUserVo selectById(@Param("id") Long id);
}
