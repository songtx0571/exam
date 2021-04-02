package com.howei.mapper;

import com.howei.pojo.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Component
@Mapper
public interface KnowledgeMapper {
    List<Knowledge> getByEmployeeId(@Param("employeeId") Integer employeeId);

    Integer deleteById(@Param("id") Integer id);

    int insert(@Param("record") Knowledge record);

    int updateById(@Param("record") Knowledge record);

    Knowledge getById(@Param("id") Integer id);

    int insertCheckEmployee(@Param("map") Map<String, Object> map);

    List<Knowledge> getByMap(@Param("map") Map<String, Object> map);

    Map<String, Object> getChechEmployeeByMap(@Param("map") Map<String, Object> map);

    int deleteByKid(@Param("id") Integer id);
}
