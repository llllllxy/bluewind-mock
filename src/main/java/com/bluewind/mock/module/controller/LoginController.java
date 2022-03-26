package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.consts.SystemConst;
import com.bluewind.mock.common.util.SHA256Utils;
import com.bluewind.mock.module.model.SysUserInfo;
import com.bluewind.mock.module.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @author liuxingyu01
 * @date 2022-03-26 19:09
 * @description
 **/
@Controller
public class LoginController {
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 加密盐值
     */
    @Value("${hash.salt}")
    private String salt;


    @PostMapping("/doLogin")
    @ResponseBody
    public Result doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {
        logger.info("LoginController doLogin username = {}, password = {}", username, password);

        // 根据用户名查找到用户信息
        SysUserInfo userInfo = loginService.getUserInfo(username);

        // 没找到帐号(用户不存在)
        if (userInfo == null) {
            return Result.error("账户不存在！");
        }

        // 校验用户状态(用户已失效)
        if ("1".equals(userInfo.getStatus())) {
            return Result.error("该账户已被冻结！");
        }

        String localPassword = userInfo.getPassword();
        password = SHA256Utils.SHA256Encode(salt + password);

        if (localPassword.equals(password)) {
            logger.info("LoginController - doLogin - {}登陆成功！", username);
            session.setAttribute(SystemConst.SYSTEM_USER_KEY, userInfo);
            return Result.ok("登录成功，欢迎回来！",null);
        } else {
            return Result.error("密码错误，请重新输入！");
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        // 退出系统时，清除session，invalidate()方法可以清除session对象中的所有信息。
        session.invalidate();
        return Result.ok("退出登陆成功！",null);
    }

}
