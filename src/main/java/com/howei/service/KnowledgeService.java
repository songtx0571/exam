package com.howei.service;

import com.howei.pojo.Knowledge;

import java.util.List;
import java.util.Map;

public interface KnowledgeService {
    List<Knowledge> getAllByEmployeeId(Integer employeeId);

    Integer deleteById(Integer id);

    int insert(Knowledge knowledge);

    int updateById(Knowledge knowledge);

    Knowledge getById(Integer id);

    int insertCheckEmployee(Map<String, Object> map);

    List<Knowledge> getByMap(Map<String, Object> map);

    Map<String, Object> getCheckEmployeeByMap(Map<String, Object> map);

    int deleteKcrByKid(Integer id);
}
