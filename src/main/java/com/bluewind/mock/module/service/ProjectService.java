package com.bluewind.mock.module.service;

import com.bluewind.mock.module.mapper.ProjectMapper;
import com.bluewind.mock.module.model.SysProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxingyu01
 * @date 2022-03-26 21:25
 * @description
 **/
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<SysProjectInfo> list(String userId) {
        return projectMapper.list(userId);
    }

    public SysProjectInfo getOne(SysProjectInfo sysProjectInfo) {
        return projectMapper.getOne(sysProjectInfo);
    }

    public int add(SysProjectInfo sysProjectInfo) {
        return projectMapper.add(sysProjectInfo);
    }

    public int update(SysProjectInfo sysProjectInfo) {
        return projectMapper.update(sysProjectInfo);
    }

    public int delete(String projectId) {
        return projectMapper.delete(projectId);
    }
}
