package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.annotation.AccessLimit;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.consts.SystemConst;
import com.bluewind.mock.common.util.SHA256Utils;
import com.bluewind.mock.module.model.SysUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author liuxingyu01
 * @date 2022-03-30 10:13
 * @description 注册控制器
 **/
@Controller
public class RegisterController {
    final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    /**
     * 加密盐值
     */
    @Value("${hash.salt}")
    private String salt;

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @AccessLimit(seconds = 60, maxCount = 2, msg = "操作频率过高，请稍后再试")
    @PostMapping("/doRegister")
    @ResponseBody
    public Result doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String invitationCode,
                             HttpSession session) {
        logger.info("LoginController doLogin username = {}, invitationCode = {}", username, invitationCode);

        // 先去校验邀请码是否正确


        // 邀请码正确的话，则插入用户表和邀请记录表


        // 数据写入session，完成注册


        return Result.ok("注册成功，即将前往首页！",null);
    }


}
