package com.knight.ucenter.server.controller;

import com.knight.common.result.BaseServerResponse;
import com.knight.ucenter.api.UcenterUserService;
import com.knight.ucenter.dao.model.UcenterUser;
import com.knight.upms.api.UpmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;
@RestController
@RequestMapping("/users/")
public class SignController {
    @Autowired
    UcenterUserService ucenterUserService;
    @Reference(version = "1.0.0",check = true)
    UpmsLogService upmsLogService;

    @RequestMapping(value = "/register",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseServerResponse register(@ModelAttribute UcenterUser ucenterUser){
        upmsLogService.test();
        return BaseServerResponse.createBySuccess();
    }

}
