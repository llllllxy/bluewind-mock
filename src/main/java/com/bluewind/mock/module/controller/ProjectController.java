package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.base.BaseController;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.util.idgen.IdGenerate;
import com.bluewind.mock.module.model.SysProjectInfo;
import com.bluewind.mock.module.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public Result list() {
        List<SysProjectInfo> resultList = projectService.list(getUserId());
        return Result.ok("退出登陆成功！",resultList);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestParam("projectName") String projectName,
                      @RequestParam("path") String path,
                      @RequestParam(required = false, defaultValue = "", value = "introduce") String introduce) {
        String userId = getUserId();
        String projectId = IdGenerate.nextId();
        SysProjectInfo sysProjectInfo = new SysProjectInfo();
        sysProjectInfo.setProjectId(projectId);
        sysProjectInfo.setUserId(userId);
        sysProjectInfo.setProjectName(projectName);
        sysProjectInfo.setPath(path);
        sysProjectInfo.setIntroduce(introduce);
        sysProjectInfo.setCreateUser(userId);
        int num = projectService.add(sysProjectInfo);

        return Result.ok("新增项目成功！",num);
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestParam("projectId") String projectId,
                         @RequestParam("projectName") String projectName,
                         @RequestParam("path") String path,
                         @RequestParam(required = false, defaultValue = "", value = "introduce") String introduce) {

        SysProjectInfo sysProjectInfo = new SysProjectInfo();
        sysProjectInfo.setProjectId(projectId);
        sysProjectInfo.setUpdateUser(getUserId());
        sysProjectInfo.setProjectName(projectName);
        sysProjectInfo.setPath(path);
        sysProjectInfo.setIntroduce(introduce);
        int num = projectService.update(sysProjectInfo);

        return Result.ok("修改项目成功！",num);
    }


    /**
     * 岗位信息修改
     *
     * @return
     */
    @GetMapping(value = "/delete/{projectId}")
    @ResponseBody
    public Result delete(@PathVariable String projectId) {


        return Result.ok("删除项目成功！",projectId);
    }

}
