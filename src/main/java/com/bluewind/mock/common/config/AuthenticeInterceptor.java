package com.bluewind.mock.common.config;

import com.bluewind.mock.common.consts.SystemConst;
import com.bluewind.mock.module.model.SysUserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuxingyu01
 * @date 2022-03-26 20:48
 * @description
 **/
@Component
public class AuthenticeInterceptor implements HandlerInterceptor {
    final static Logger logger = LoggerFactory.getLogger(AuthenticeInterceptor.class);

    /*
     * 进入controller层之前拦截请求
     * 返回值：表示是否将当前的请求拦截下来  false：拦截请求，请求别终止。true：请求不被拦截，继续执行
     * Object obj:表示被拦的请求的目标对象（controller中方法）
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        SysUserInfo userInfo = (SysUserInfo) request.getSession().getAttribute(SystemConst.SYSTEM_USER_KEY);
        logger.info("AuthenticeInterceptor -- preHandle -- userInfo = {}", userInfo);

        if (userInfo == null) {
            // 拦截后跳转至登录页
            response.sendRedirect("/login");
            System.out.println("已成功拦截并转发跳转");
            return false;
        }

        // 合格不需要拦截，放行
        return true;
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     * 通过ModelAndView参数改变显示的视图，或发往视图的方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //System.out.println("执行了postHandle方法");
    }

    /*
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        //System.out.println("执行到了afterCompletion方法");
    }

}
