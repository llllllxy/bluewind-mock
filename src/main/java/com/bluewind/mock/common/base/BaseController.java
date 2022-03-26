package com.bluewind.mock.common.base;

import com.bluewind.mock.common.consts.SystemConst;
import com.bluewind.mock.module.model.SysUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author liuxingyu01
 * @date 2022-03-26 18:44
 * @description BaseController
 **/
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    public static final String RESULT_ROWS = "rows";
    public static final String RESULT_TOTLAL = "total";

    public BaseController() {
    }

    /**
     * 获取当前请求对象
     *
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            return request;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前响应对象
     *
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = null;
        try {
            response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            return response;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取session
     *
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取当前会话信息
     * @return
     */
    public static SysUserInfo getUserInfo() {
        SysUserInfo userInfo = (SysUserInfo) getSession().getAttribute(SystemConst.SYSTEM_USER_KEY);
        return userInfo;
    }

    /**
     * 获取当前会话信息
     * @return
     */
    public static String getUserId() {
        return getUserInfo().getUserId();
    }
}
