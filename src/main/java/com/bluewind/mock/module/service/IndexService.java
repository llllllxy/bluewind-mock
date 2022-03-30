package com.bluewind.mock.module.service;

import com.bluewind.mock.module.mapper.IndexMapper;
import com.bluewind.mock.module.model.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxingyu01
 * @date 2022-03-30 16:26
 * @description
 **/
@Service
public class IndexService {

    @Autowired
    private IndexMapper indexMapper;

    public Integer checkInvitationCode(String randomStr) {
        return indexMapper.checkInvitationCode(randomStr);
    }

    public int setInvitationCode(SysUserInfo sysUserInfo) {
        return indexMapper.setInvitationCode(sysUserInfo);
    }
}
