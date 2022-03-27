package com.bluewind.mock.module.controller;

import com.alibaba.fastjson.JSONObject;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.util.RandomValue;
import com.bluewind.mock.module.model.SysMockInfo;
import com.bluewind.mock.module.model.SysProjectInfo;
import com.bluewind.mock.module.service.MockService;
import com.bluewind.mock.module.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author liuxingyu01
 * @date 2022-03-26 23:08
 * @description
 **/
@Controller
public class MockClientController {
    final static Logger logger = LoggerFactory.getLogger(MockClientController.class);

    private static final String URL_PREFIX = "/api";

    @Autowired
    private MockService mockService;

    @Autowired
    private ProjectService projectService;

    /**
     * 匹配mock请求，
     * 地址示例：GET请求 http://127.0.0.1:8077/api/929292901911010/first/cainiao
     * POST请求 http://127.0.0.1:8077/api/939393939393/first/cainiao2
     * 地址详解：api / projectId / projectPath / url（url里可能还有好多斜杠）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = URL_PREFIX + "/**", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object mockClient(HttpServletRequest request) {
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        logger.info("MockClientController mockClient requestUri = {}, method= {}", requestUri, method);

        // uri = /api/929292901911010/first/cainiao, method= GET
        String[] strs = requestUri.split("/");
        try {
            logger.info("MockClientController mockClient strs.length = {}", strs.length);
            logger.info("MockClientController mockClient strs = {}", Arrays.toString(strs));
            if (strs.length < 5) {
                return Result.error("Mock地址解析错误！");
            }

            String projectId = strs[2];
            String projectPath = strs[3];
            // 因为url可能有多个斜杠，所以特殊处理
            String url = "";
            for (int i = 4; i < strs.length; i++) {
                url = url + strs[i] + "/";
            }
            url = url.substring(0, url.length() - 1);

            if (StringUtils.isBlank(projectId) || StringUtils.isBlank(url)) {
                return Result.error("Mock地址错误！");
            }

            // 先校验projectId和projectPath是否匹配
            SysProjectInfo projectParams = new SysProjectInfo();
            projectParams.setPath("/" + projectPath);
            projectParams.setProjectId(projectId);
            SysProjectInfo SysProjectInfo = projectService.getOne(projectParams);
            if (SysProjectInfo == null) {
                return Result.error("Mock项目路径不存在！");
            }

            // 然后校验url是否存在
            SysMockInfo params = new SysMockInfo();
            params.setProjectId(projectId);
            params.setUrl("/" + url);

            // 根据 projectId 和 url 寻找 对应的 sys_mock_info 记录
            SysMockInfo sysMockInfo = mockService.getOne(params);
            if (sysMockInfo == null) {
                return Result.error("Mock地址不存在！");
            }
            // 然后判断 请求类型是否相同，如果不同，则返回错误，
            String mockMethod = sysMockInfo.getMethod();
            if (!mockMethod.equals(method)) {
                return Result.error("HTTP Method不匹配！");
            }

            // 如果相同，则返回表里存的json数据
            String jsonData = sysMockInfo.getJsonData();
            String mockjsFlag = sysMockInfo.getMockjsFlag();
            // 判断是否需要使用mockjs进行解析
            if ("1".equals(mockjsFlag)) {
                Map map = RandomValue.mock(jsonData);
                return map;
            } else {
                return JSONObject.parse(jsonData);
            }
        } catch (ScriptException e) {
            return Result.error("Mockjs解析错误！");
        } catch (Exception e2) {
            return Result.error("Mock地址解析错误！");
        }

    }

}
