package com.bluewind.mock.module.mapper;

import com.bluewind.mock.module.model.SysMockInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author liuxingyu01
 * @date 2022-03-27 10:42
 * @description
 **/
@Repository
public interface MockMapper {

    List<SysMockInfo> list(Map<String, String> paraMap);

    SysMockInfo getOne(SysMockInfo sysMockInfo);

    int delete(String mockId);

    int batchDelete(@Param("mockIds")List<String> mockIds);

}
