package com.howei.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.howei.pojo.Achievement;
import com.howei.pojo.Examination;
import com.howei.pojo.Exampublish;
import com.howei.pojo.Situation;

@Component
@Mapper
public interface ExampublishMapper {

	List<Exampublish> findExampublish();
	
	int addExampublish(Exampublish exampublish);
	
	int deleteExampublish(int id);
	
	int updateExampublish(Exampublish exampublish);
	
	List<Examination> findExamHis(String userName);
	
	Examination getExamTimes(String userName, String cycle, Integer week, Integer questionId, Integer times);
	Examination getWeek(Integer week);

    void insertExamHis(@Param("examination") Examination examination);
    
    int updatescore(String testScore, String Username);
    
    Achievement findAchievement(@Param("userName") String userName, @Param("week") int week, @Param("cycle") String cycle);

    int addAchievement(@Param("userName") String userName, @Param("TestScore") int TestScore, @Param("week") int week, @Param("cycle") String cycle);

    int updAchievement(@Param("id") int id, @Param("TestScore") int TestScore, @Param("times") int times);

    List<Situation> findbyUsr(@Param("Username") String Username);
}
