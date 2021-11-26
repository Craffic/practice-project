package com.craffic.security.test;

import com.craffic.security.domain.Menu;
import com.craffic.security.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTest {

    @Autowired
    MenuService menuService;

    @Test
    public void contextLoads(){
        List<Menu> allMenus = menuService.getAllMenus();
        System.out.println(allMenus);
    }

}
