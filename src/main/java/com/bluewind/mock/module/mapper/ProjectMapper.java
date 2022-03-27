package com.bluewind.mock.module.mapper;

import com.bluewind.mock.module.model.SysProjectInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxingyu01
 * @date 2022-03-26 21:25
 * @description
 **/
@Repository
public interface ProjectMapper {
    List<SysProjectInfo> list(String userId);

    SysProjectInfo getOne(SysProjectInfo sysProjectInfo);
}
