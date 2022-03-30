package com.bluewind.mock.module.service;

import com.bluewind.mock.common.util.idgen.IdGenerate;
import com.bluewind.mock.module.mapper.RegisterMapper;
import com.bluewind.mock.module.model.SysInviteesInfo;
import com.bluewind.mock.module.model.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuxingyu01
 * @date 2022-03-30 18:00
 * @description
 **/
@Service
public class RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    public SysUserInfo checkInvitation(String invitationCode) {
        return registerMapper.checkInvitation(invitationCode);
    }

    @Transactional
    public int register(String inviteUserId, String invitationCode, SysUserInfo params) {
        int num1 = registerMapper.register(params);

        SysInviteesInfo sysInviteesInfo =  new SysInviteesInfo();
        sysInviteesInfo.setInvitationCode(invitationCode);
        sysInviteesInfo.setInviteUserId(inviteUserId);
        sysInviteesInfo.setInviteesUserId(params.getUserId());
        sysInviteesInfo.setId(IdGenerate.nextId());

        int num2 = registerMapper.registerLog(sysInviteesInfo);

        return num1 + num2;
    }

}
