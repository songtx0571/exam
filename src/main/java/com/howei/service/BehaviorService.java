package com.howei.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howei.pojo.Assessment;
import com.howei.pojo.Behavior;

import java.util.List;


public interface BehaviorService  {

 
    Behavior findAllBe(Behavior behavior);
    
    List<String> selectBeCycle(int employeeId);

    void updateCycle(Behavior behavior);

    List<String> selectAllCycle();


    List<Assessment> getAssessment(String cycle);

    List<Assessment> getAssessmentBy(String cycle, String manager);
    Assessment getAssessmentByUserName(String cycle, String userName);


    Assessment getAssessmentByEmployeeId(String cycle, String employeeId);
    //根据用户id月份查询成绩为空的记录
     Behavior selscore(@Param("cycle") String cycle, @Param("week") String week);
    //修改周考成绩
    void updateWeek(Behavior behavior);
}
