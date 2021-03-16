package com.howei.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.howei.pojo.Achievement;
import com.howei.pojo.AchievementRecord;
import com.howei.pojo.Behavior;

@Component
@Mapper
public interface AchievementMapper {
	
	Achievement findAchievement(@Param("userName") String userName, @Param("week") int week, @Param("cycle") String cycle);

    int addAchievement(@Param("userName") String userName, @Param("TestScore") int TestScore, @Param("week") int week, @Param("cycle") String cycle);

    int updAchievement(@Param("id") int id, @Param("TestScore") int TestScore, @Param("times") int times);

    List<Achievement> findAchievementsByUserName(@Param("userName") String userName, @Param("cycle") String cycle);

    List<Achievement> findAchievementsByUserName1(@Param("behavior") Behavior behavior);

    List<Achievement> findAchievementsByCycle(@Param("cycle") String cycle);

    List<AchievementRecord> findAchievementRecords(@Param("userNumber") String userNumber);

}
