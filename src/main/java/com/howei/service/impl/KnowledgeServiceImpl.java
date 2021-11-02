package com.howei.service.impl;

import com.howei.mapper.KnowledgeMapper;
import com.howei.pojo.Knowledge;
import com.howei.pojo.KnowledgeKeyword;
import com.howei.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeMapper mapper;


    @Override
    public List<Knowledge> getAllByEmployeeId(Integer employeeId) {
        return mapper.getByEmployeeId(employeeId);
    }

    @Override
    public Integer deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public int insert(Knowledge knowledge) {
        return mapper.insert(knowledge);
    }

    @Override
    public int updateById(Knowledge knowledge) {
        return mapper.updateById(knowledge);
    }

    @Override
    public Knowledge getById(Integer id) {
        return mapper.getById(id);
    }

    @Override
    public int insertCheckEmployee(Map<String, Object> map) {
        return mapper.insertCheckEmployee(map);
    }

    @Override
    public List<Knowledge> getByMap(Map<String, Object> map) {
        return mapper.getByMap(map);
    }

    @Override
    public Map<String, Object> getCheckEmployeeByMap(Map<String, Object> map) {
        return mapper.getChechEmployeeByMap(map);

    }

    @Override
    public int deleteKcrByKid(Integer id) {
        return mapper.deleteByKid(id);
    }

    @Override
    public int deleteKkByKid(Integer knowledgeId) {
        return mapper.deleteKkByKid(knowledgeId);
    }

    @Override
    public int insertKk(List<KnowledgeKeyword> knowledgeKeywordList) {
        return mapper.insertKk(knowledgeKeywordList);
    }

}
