package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.annotation.AccessLimit;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.consts.SystemConst;
import com.bluewind.mock.common.util.SHA256Utils;
import com.bluewind.mock.common.util.idgen.IdGenerate;
import com.bluewind.mock.module.model.SysUserInfo;
import com.bluewind.mock.module.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "RegisterController", description = "注册服务控制器")
@Controller
public class RegisterController {
    final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;

    /**
     * 加密盐值
     */
    @Value("${hash.salt}")
    private String salt;


    @ApiOperation(value = "注册页", notes = "注册页")
    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @ApiOperation(value = "注册", notes = "注册")
    @AccessLimit(seconds = 60, maxCount = 2, msg = "操作频率过高，请稍后再试")
    @PostMapping("/doRegister")
    @ResponseBody
    public Result doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String invitationCode,
                             HttpSession session) {
        logger.info("LoginController doLogin username = {}, invitationCode = {}", username, invitationCode);

        // 先去校验邀请码是否正确
        SysUserInfo exist = registerService.checkInvitation(invitationCode);
        if (exist == null ) {
            // 当不存在时，直接返回
            return Result.error("邀请码不存在！");
        } else {
            // 邀请码正确的话，则插入用户表和邀请记录表
            // 邀请人编码
            String inviteUserId = exist.getUserId();

            SysUserInfo params = new SysUserInfo();
            params.setAccount(username);
            params.setPassword(SHA256Utils.SHA256Encode(salt + password));
            params.setUserId(IdGenerate.nextId());
            params.setStatus("0");
            params.setDelFlag("0");

            int num = registerService.register(inviteUserId, invitationCode, params);

            if (num > 1) {
                // 数据写入session，完成注册
                session.setAttribute(SystemConst.SYSTEM_USER_KEY, params);
                return Result.ok("注册成功，即将前往首页！",null);
            } else {
                return Result.error("注册失败，系统出错！");
            }
        }

    }


}
