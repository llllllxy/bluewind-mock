package com.bluewind.mock.module.service;

import com.bluewind.mock.module.mapper.MockMapper;
import com.bluewind.mock.module.model.SysMockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liuxingyu01
 * @date 2022-03-27 10:42
 * @description
 **/
@Service
public class MockService {

    @Autowired
    private MockMapper mockMapper;


    public List<SysMockInfo> list(Map<String, String> paraMap) {
        return mockMapper.list(paraMap);
    }

    public SysMockInfo getOne(SysMockInfo sysMockInfo) {
        return mockMapper.getOne(sysMockInfo);
    }
}
