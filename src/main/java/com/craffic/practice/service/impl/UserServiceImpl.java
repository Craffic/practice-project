package com.craffic.practice.service.impl;

import com.craffic.practice.dao.TbUserDao;
import com.craffic.practice.service.UserService;
import com.craffic.practice.vo.TbUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserDao userDao;

    @Override
    public TbUserVo getTbUSerDetail(String userId) {
        if (userId == null){
            return null;
        }
        TbUserVo user = userDao.selectById(Long.parseLong(userId));
        if (user == null) return null;
        return user;
    }

}
