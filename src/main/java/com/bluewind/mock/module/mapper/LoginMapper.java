package com.bluewind.mock.module.mapper;

import com.bluewind.mock.module.model.SysUserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author liuxingyu01
 * @date 2022-03-26 19:09
 * @description
 **/
@Repository
public interface LoginMapper {
    SysUserInfo getUserInfo(String account);
}
