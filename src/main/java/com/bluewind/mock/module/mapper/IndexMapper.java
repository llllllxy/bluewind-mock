package com.bluewind.mock.module.mapper;

import com.bluewind.mock.module.model.SysUserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author liuxingyu01
 * @date 2022-03-30 16:26
 * @description
 **/
@Repository
public interface IndexMapper {
    Integer checkInvitationCode(String randomStr);

    int setInvitationCode(SysUserInfo sysUserInfo);
}
