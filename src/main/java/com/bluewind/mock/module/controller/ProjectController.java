package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.base.BaseController;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.module.model.SysProjectInfo;
import com.bluewind.mock.module.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxingyu01
 * @date 2022-03-26 21:24
 * @description
 **/
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/index")
    public String index() {
        return "project_list";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result list() throws UnknownHostException {
        List<SysProjectInfo> resultList = projectService.list(getUserId());
        return Result.ok("退出登陆成功！",resultList);
    }

}
