package com.bluewind.mock.module.mapper;

import com.bluewind.mock.module.model.SysMockInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxingyu01
 * @date 2022-03-27 10:42
 * @description
 **/
@Repository
public interface MockMapper {

    List<SysMockInfo> list(String projectId);

    SysMockInfo getOne(SysMockInfo sysMockInfo);
}
