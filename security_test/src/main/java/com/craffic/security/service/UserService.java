package com.craffic.security.service;

import com.craffic.security.dao.UserMapper;
import com.craffic.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在！");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }
}
