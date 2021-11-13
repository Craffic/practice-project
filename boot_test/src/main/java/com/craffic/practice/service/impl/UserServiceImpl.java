package com.craffic.practice.service.impl;

import com.craffic.practice.dao.TbUserDao;
import com.craffic.practice.domain.TbUser;
import com.craffic.practice.service.UserService;
import com.craffic.practice.vo.TbUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 整合redis cache案例
     */
    @Cacheable(cacheNames = "c1")
    public TbUserVo getUserByIdCache_1(Long id){
        System.out.println("getUserByIdCache>>>>>>>>>>>>>>>>>>>>>>" + id);
        return userDao.selectById(id);
    }

    @Cacheable(cacheNames = "c1", key="#id")
    public TbUserVo getUserByIdCache_2(Long id){
        System.out.println("getUserByIdCache>>>>>>>>>>>>>>>>>>>>>>" + id);
        return userDao.selectById(id);
    }

    @CacheEvict(cacheNames = "c1", key = "#id")
    public void deleteByUserId_3(Long id){
        System.out.println("deleteByUserId>>>>>>>>>>>>>>>>>>>>>>" + id);
        userDao.deleteById(id);
    }

    @CachePut(cacheNames = "c1", key = "#user.userName")
    public TbUserVo updateUser(TbUser user){
        System.out.println("updateUser>>>>>>>>>>>>>>>>>>>>>>" + user.toString());
        userDao.updateUser(user);
        return null;
    }
}
