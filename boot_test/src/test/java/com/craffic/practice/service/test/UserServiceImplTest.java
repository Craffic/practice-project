package com.craffic.practice.service.test;


import com.craffic.practice.domain.TbUser;
import com.craffic.practice.service.impl.UserServiceImpl;
import com.craffic.practice.vo.TbUserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void getUserByIdCacheTest1(){
        TbUserVo user1 = userService.getUserByIdCache_1(3L);
        TbUserVo user2 = userService.getUserByIdCache_1(3L);
        System.out.println(user1);
        System.out.println(user2);
    }

    @Test
    public void getUserByIdCacheTest2(){
        TbUserVo user1 = userService.getUserByIdCache_2(3L);
        TbUserVo user2 = userService.getUserByIdCache_2(3L);
        System.out.println(user1);
        System.out.println(user2);
    }

    @Test
    public void getUserByIdCacheTest3(){
        TbUserVo user1 = userService.getUserByIdCache_2(3L);
        userService.deleteByUserId_3(3L);
        TbUserVo user2 = userService.getUserByIdCache_2(3L);
        System.out.println(user1);
        System.out.println(user2);
    }

    @Test
    public void getUserByIdCacheTest4(){
        TbUserVo user1 = userService.getUserByIdCache_2(2L);
        TbUser user = new TbUser();
        user.setId(2L);
        user.setUserName("234423423234werwer23");
        user.setPhone("wrw");
        user.setEmail("234sdfs!Q");
        userService.updateUser(user);
        TbUserVo user2 = userService.getUserByIdCache_2(2L);
        System.out.println(user1);
        System.out.println(user2);
    }
}
