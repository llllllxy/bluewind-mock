package com.bluewind.mock.module.service;

import com.bluewind.mock.module.mapper.LoginMapper;
import com.bluewind.mock.module.model.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxingyu01
 * @date 2022-03-26 19:09
 * @description
 **/
@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public SysUserInfo getUserInfo(String account) {
        return loginMapper.getUserInfo(account);
    }
}
