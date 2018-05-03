package com.knight.ucenter.server.controller;

import com.knight.common.result.BaseServerResponse;
import com.knight.ids.client.IdsClient;
import com.knight.ids.client.IdsClient;
import com.knight.ucenter.api.UcenterUserService;
import com.knight.ucenter.dao.model.UcenterUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
@Log4j2
public class SignController {
    @Autowired
    UcenterUserService ucenterUserService;
//    @Autowired
//    UpmsLogService upmsLogService;

    @RequestMapping(value = "/register",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseServerResponse register(@ModelAttribute UcenterUser ucenterUser){

        int userId = IdsClient.getNextId(1, "user_id");
        log.debug("生成 userId "+userId);
        return BaseServerResponse.createBySuccess();
    }

}
