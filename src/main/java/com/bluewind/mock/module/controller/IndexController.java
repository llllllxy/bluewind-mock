package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.base.BaseController;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.consts.SystemConst;
import com.bluewind.mock.module.model.SysUserInfo;
import com.bluewind.mock.common.util.StringUtils;
import com.bluewind.mock.module.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * @author liuxingyu01
 * @date 2022-03-26 20:38
 * @description
 **/
@Controller
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;


    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/getInvitationCode")
    @ResponseBody
    public Result getInvitationCode(HttpSession session) {
        String invitationCode = "";
        SysUserInfo sysUserInfo = getUserInfo();
        if (StringUtils.isEmpty(sysUserInfo.getInvitationCode())) {
            boolean ifContinue = true;
            while (ifContinue) {
                String randomStr = StringUtils.getRandomStr(6);
                Integer exist = indexService.checkInvitationCode(randomStr);
                if (exist != null ) {
                    // 当存在时，继续循环执行
                } else {
                    // 当不存在时，说明未重复，可以更新
                    sysUserInfo.setInvitationCode(randomStr);
                    int num = indexService.setInvitationCode(sysUserInfo);
                    invitationCode = randomStr;
                    // 刷新session缓存
                    session.setAttribute(SystemConst.SYSTEM_USER_KEY, sysUserInfo);
                    ifContinue = false;
                }
            }
        } else {
            invitationCode = sysUserInfo.getInvitationCode();
        }
        return Result.ok("获取推荐码成功！",invitationCode);
    }

}
