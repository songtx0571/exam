package com.howei.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howei.mapper.AchievementMapper;
import com.howei.mapper.Behaviormapper;
import com.howei.pojo.Achievement;
import com.howei.pojo.Assessment;
import com.howei.pojo.Behavior;
import com.howei.service.BehaviorService;

import java.util.List;


@Service
public class BehaviorServiceImpl implements BehaviorService {

	@Autowired
    Behaviormapper behaviormapper;

	@Autowired
	AchievementMapper achievementMapper;
	
	@Override
    public Behavior findAllBe(Behavior behavior) {
        Behavior Behavior = behaviormapper.findAll(behavior);
        List<Achievement> achievements = achievementMapper.findAchievementsByUserName1(behavior);
        System.out.println(achievements.size());
        for (Achievement achievement : achievements) {
            if (achievement.getWeek() == 1 && Behavior.getWeek1() != "") {
                Behavior.setWeek1("" + ((double) achievement.getMaxValue()) / 10);
            }
            if (achievement.getWeek() == 2 && Behavior.getWeek2() != "") {
                Behavior.setWeek2("" + ((double) achievement.getMaxValue()) / 10);
            }
            if (achievement.getWeek() == 3 && Behavior.getWeek3() != "") {
                Behavior.setWeek3("" + ((double) achievement.getMaxValue()) / 10);
            }
            if (achievement.getWeek() == 4 && Behavior.getWeek4() != "") {
                Behavior.setWeek4("" + ((double) achievement.getMaxValue()) / 10);
            }
        }
        return Behavior;

    }

	@Override
    public List<String> selectBeCycle(int employeeId) {
        return behaviormapper.selectBeCycle(employeeId);
    }

	@Override
    public void updateCycle(Behavior behavior) {
        behaviormapper.updateCycle(behavior);
    }

	@Override
    public List<String> selectAllCycle() {
        return behaviormapper.selectAllCycle();
    }

	@Override
    public List<Assessment> getAssessment(String cycle) {
        return behaviormapper.getAssessment(cycle);
    }
	@Override
    public List<Assessment> getAssessmentBy(String cycle, String manager) {
        return behaviormapper.getAssessmentBy(cycle, manager);
    }
	@Override
    public Assessment getAssessmentByUserName(String cycle, String userName) {
        return behaviormapper.getAssessmentByUserName(cycle, userName);
    }

	@Override
    public Assessment getAssessmentByEmployeeId(String cycle, String employeeId) {
        return behaviormapper.getAssessmentByEmployeeId(cycle, employeeId);
    }
    //根据用户id月份查询成绩为空的记录
	@Override
    public Behavior selscore(@Param("cycle") String cycle, @Param("week") String week){
        return behaviormapper.selscore(cycle,week);
    }
    //修改周考成绩
	@Override
    public void updateWeek(Behavior behavior){
        behaviormapper.updateWeek(behavior);
    }
}
