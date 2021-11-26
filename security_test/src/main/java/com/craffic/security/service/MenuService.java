package com.craffic.security.service;

import com.craffic.security.dao.MenuMapper;
import com.craffic.security.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;


    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }

}
