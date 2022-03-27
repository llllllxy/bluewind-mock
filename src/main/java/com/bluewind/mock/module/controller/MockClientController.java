package com.bluewind.mock.module.controller;

import com.alibaba.fastjson.JSONObject;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.util.RandomValue;
import com.bluewind.mock.module.model.SysMockInfo;
import com.bluewind.mock.module.service.MockService;
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

    /**
     * 匹配mock请求，
     * 地址示例：GET请求 http://127.0.0.1:8077/api/929292901911010/cainiao
     *         POST请求 http://127.0.0.1:8077/api/939393939393/cainiao2
     * 地址详解：api / projectId / url
     *
     * @param request
     * @return
     */
    @RequestMapping(value = URL_PREFIX + "/**", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object mockClient(HttpServletRequest request) throws ScriptException {
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        logger.info("MockClientController mockClient requestUri = {}, method= {}", requestUri, method);

        // uri = /api/929292901911010/cainiao, method= GET
        String[] strs = requestUri.split("/");

        if (strs.length != 4) {
            return Result.error("Mock地址错误！");
        }

        logger.info("MockClientController mockClient strs.length = {}", strs.length);

        logger.info("MockClientController mockClient strs = {}", strs[1]);

        String projectId = strs[2];
        String url = strs[3];

        if (StringUtils.isBlank(projectId) || StringUtils.isBlank(url)) {
            return Result.error("Mock地址错误！");
        }

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

    }

}
