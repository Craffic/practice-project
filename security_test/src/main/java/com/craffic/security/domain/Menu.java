package com.craffic.security.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private Integer id;

    private String pattern;

    // 每个资源都有某些角色才能访问的
    List<Role> roleList = new ArrayList<>();

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pattern='" + pattern + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
