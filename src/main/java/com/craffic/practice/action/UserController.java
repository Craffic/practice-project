package com.craffic.practice.action;

import com.craffic.practice.pojo.TbUserReq;
import com.craffic.practice.service.impl.UserServiceImpl;
import com.craffic.practice.vo.TbUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String queryUserById(TbUserReq tbUser){

        String userId = String.valueOf(tbUser.getUserId());
        TbUserVo tbUSerDetail = userService.getTbUSerDetail(userId);
        if (tbUSerDetail == null){
            return "请输入有效的用户ID！";
        }
        return tbUSerDetail.toString();
    }
}
