package com.bluewind.mock.module.mapper;

import com.bluewind.mock.module.model.SysInviteesInfo;
import com.bluewind.mock.module.model.SysUserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author liuxingyu01
 * @date 2022-03-30 18:00
 * @description
 **/
@Repository
public interface RegisterMapper {

    SysUserInfo checkInvitation(String invitationCode);

    int register(SysUserInfo sysUserInfo);

    int registerLog(SysInviteesInfo sysInviteesInfo);

}
