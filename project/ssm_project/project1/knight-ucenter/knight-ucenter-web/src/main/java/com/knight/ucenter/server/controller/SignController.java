package com.knight.ucenter.server.controller;

import com.knight.common.result.BaseServerResponse;
import com.knight.ucenter.api.UcenterUserService;
import com.knight.ucenter.dao.model.UcenterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class SignController {
    @Autowired
    UcenterUserService ucenterUserService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public BaseServerResponse register(@ModelAttribute UcenterUser ucenterUser){
        return BaseServerResponse.createBySuccess();
    }

}
