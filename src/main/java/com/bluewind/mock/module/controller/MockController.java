package com.bluewind.mock.module.controller;

import com.bluewind.mock.common.base.BaseController;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.module.model.SysMockInfo;
import com.bluewind.mock.module.service.MockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author liuxingyu01
 * @date 2022-03-26 23:06
 * @description 接口管理控制器
 **/
@Api(value = "MockController", description = "接口管理控制器")
@RequestMapping("/mock")
@Controller
public class MockController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(MockController.class);

    @Autowired
    private MockService mockService;

    @PostMapping("/list")
    @ResponseBody
    public Result list(@RequestParam("projectId") String projectId,
                       @RequestParam("pageNum") Integer pageNum,
                       @RequestParam("pageSize") Integer pageSize,
                       @RequestParam(required = false, defaultValue = "", value = "url") String url,
                       @RequestParam(required = false, defaultValue = "", value = "method") String method,
                       @RequestParam(required = false, defaultValue = "", value = "sortName") String sortName,
                       @RequestParam(required = false, defaultValue = "", value = "sortOrder") String sortOrder) {
        //分页查询
        PageHelper.startPage(pageNum, pageSize);
        if (logger.isInfoEnabled()) {
            logger.info("MockController -- list -- 页面大小：" + pageSize + "--页码:" + pageNum);
        }
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("projectId", projectId);
        paraMap.put("method", method);
        paraMap.put("url", url);
        paraMap.put("sortName", sortName);
        paraMap.put("sortOrder", sortOrder);

        List<SysMockInfo> mockList = mockService.list(paraMap);

        PageInfo<SysMockInfo> pageinfo = new PageInfo<>(mockList);

        //取出查询结果
        List<SysMockInfo> rows = pageinfo.getList();
        int total = (int) pageinfo.getTotal();
        Map<String, Object> result = new HashMap<>();
        result.put(RESULT_ROWS, rows);
        result.put(RESULT_TOTLAL, total);

        return Result.ok("获取mock列表成功！", result);
    }


    @ApiOperation(value = "删除接口", notes = "删除接口")
    @GetMapping(value = "/delete/{mockId}")
    @ResponseBody
    public Result delete(@PathVariable String mockId) {
        int num = mockService.delete(mockId);
        return Result.ok("删除接口成功！", num);
    }


    @ApiOperation(value = "批量删除接口", notes = "批量删除接口")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result batchDelete(@RequestBody List<String> mockIds) {
        int num = mockService.batchDelete(mockIds);
        return Result.ok("批量删除接口成功！", num);
    }


}
