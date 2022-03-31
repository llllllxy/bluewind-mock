package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.base.BaseController;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.util.idgen.IdGenerate;
import com.bluewind.mock.module.model.SysProjectInfo;
import com.bluewind.mock.module.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author liuxingyu01
 * @date 2022-03-26 21:24
 * @description
 **/
@Api(value = "ProjectController", description = "项目管理控制器")
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;


    @ApiOperation(value = "项目管理首页", notes = "项目管理首页")
    @GetMapping("/index")
    public String index() {
        return "project_list";
    }

    @ApiOperation(value = "退出登陆", notes = "退出登陆")
    @GetMapping("/list")
    @ResponseBody
    public Result list() {
        List<SysProjectInfo> resultList = projectService.list(getUserId());
        return Result.ok("退出登陆成功！",resultList);
    }

    @ApiOperation(value = "新增项目", notes = "新增项目")
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

    @ApiOperation(value = "更新项目", notes = "更新项目")
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


    @ApiOperation(value = "删除项目", notes = "删除项目")
    @GetMapping(value = "/delete/{projectId}")
    @ResponseBody
    public Result delete(@PathVariable String projectId) {
        int num = projectService.delete(projectId);
        return Result.ok("删除项目成功！",num);
    }

}
