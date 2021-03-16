package com.howei.service;

import java.text.ParseException;
import java.util.List;

import com.howei.pojo.AchievementRecord;
import com.howei.pojo.Examination;
import com.howei.pojo.Exampublish;
import com.howei.pojo.Situation;

public interface ExampublishService {

	List<Exampublish> findExampublish();
	
	int addExampublish(Exampublish exampublish);
	
	int deleteExampublish(int id);
	
	int updateExampublish(Exampublish exampublish);
	
	List<AchievementRecord> findAchievementRecords(String cycle,String userNumber);


	List<Examination> findExamHis(String userName);

	
	
	Examination getExamTimes(String userName, String cycle, Integer week, Integer questionId, Integer times);
	Examination getWeek(Integer week);
	void insertExamHis(Examination examination);
	
	int updatescore(String testScore, String Username);
	List<Situation> findbyUsr(String Username);
	int addAchievement(int TestScore, String userName) throws ParseException;
}
