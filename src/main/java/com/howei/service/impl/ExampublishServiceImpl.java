package com.howei.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howei.pojo.Achievement;
import com.howei.pojo.AchievementRecord;
import com.howei.mapper.AchievementMapper;
import com.howei.mapper.ExampublishMapper;
import com.howei.mapper.QuestionBankMapper;
import com.howei.pojo.Exampublish;
import com.howei.pojo.QuestionBank;
import com.howei.pojo.Situation;
import com.howei.pojo.Examination;
import com.howei.service.ExampublishService;

@Service
public class ExampublishServiceImpl implements ExampublishService {

	@Autowired
	ExampublishMapper exampublishMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Autowired
	QuestionBankMapper questionBankMapper;

	
	@Override
	public List<Exampublish> findExampublish() {
		return exampublishMapper.findExampublish();
	}

	@Override
	public int addExampublish(Exampublish exampublish) {
		return exampublishMapper.addExampublish(exampublish);
	}

	@Override
	public int deleteExampublish(int id) {
		return exampublishMapper.deleteExampublish(id);
	}

	@Override
	public int updateExampublish(Exampublish exampublish) {
		return exampublishMapper.updateExampublish(exampublish);
	}

	
	
	 public List<AchievementRecord> findAchievementRecords(String cycle,String userNumber) {
	        List<AchievementRecord> achievementRecords = achievementMapper.findAchievementRecords(userNumber);
	        List<Achievement> achievements = achievementMapper.findAchievementsByCycle(cycle);
	        for (AchievementRecord achievementRecord : achievementRecords) {
	            for (Achievement achievement : achievements) {
	                if (achievementRecord.getUserName().equals(achievement.getUserName()) && achievement.getWeek() == 1) {
	                    achievementRecord.setWeek1Result1(achievement.getResult1());
	                    achievementRecord.setWeek1Result2(achievement.getResult2());
	                    achievementRecord.setWeek1Result3(achievement.getResult3());
	                    achievementRecord.setWeek1Result4(achievement.getResult4());
	                }
	                if (achievementRecord.getUserName().equals(achievement.getUserName()) && achievement.getWeek() == 2) {
	                    achievementRecord.setWeek2Result1(achievement.getResult1());
	                    achievementRecord.setWeek2Result2(achievement.getResult2());
	                    achievementRecord.setWeek2Result3(achievement.getResult3());
	                    achievementRecord.setWeek2Result4(achievement.getResult4());
	                }
	                if (achievementRecord.getUserName().equals(achievement.getUserName()) && achievement.getWeek() == 3) {
	                    achievementRecord.setWeek3Result1(achievement.getResult1());
	                    achievementRecord.setWeek3Result2(achievement.getResult2());
	                    achievementRecord.setWeek3Result3(achievement.getResult3());
	                    achievementRecord.setWeek3Result4(achievement.getResult4());
	                }
	                if (achievementRecord.getUserName().equals(achievement.getUserName()) && achievement.getWeek() == 4) {
	                    achievementRecord.setWeek4Result1(achievement.getResult1());
	                    achievementRecord.setWeek4Result2(achievement.getResult2());
	                    achievementRecord.setWeek4Result3(achievement.getResult3());
	                    achievementRecord.setWeek4Result4(achievement.getResult4());
	                }
	            }
	        }
	        return achievementRecords;
	    }
	 
	 
	 
	 public List<Examination> findExamHis(String userName) {
		 return exampublishMapper.findExamHis(userName);
	 }
	 
	 
	 
	 public Examination getExamTimes(String userName, String cycle, Integer week, Integer questionId, Integer times) {
		 return exampublishMapper.getExamTimes(userName, cycle, week, questionId, times);
	 }

	 public Examination getWeek(Integer week) {
		 return exampublishMapper.getWeek(week);
	 }
	 
	 public void insertExamHis(Examination examination) {
		 exampublishMapper.insertExamHis(examination);
	 }
	 
	 
	 
	 
	 public int updatescore(String testScore, String Username) {
		 return exampublishMapper.updatescore(testScore, Username);
	 }
	 
	 
	 public int addAchievement(int TestScore, String userName) throws ParseException {
		 int num = 0;
		 QuestionBank questionbank = questionBankMapper.findAllByState();
		 SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
		 String cycle = sim.format(sim.parse(questionbank.getStartdate()));
		 
		 int week = questionbank.getWeek();
		 Achievement achievement = exampublishMapper.findAchievement(userName, week, cycle);
		 if (achievement == null) {
			 num = exampublishMapper.addAchievement(userName, TestScore, week, cycle);
		 } else {
			 num = exampublishMapper.updAchievement(achievement.getId(), TestScore, achievement.getTimes());
		 }
		 return num;
	 }
	 
	 public List<Situation> findbyUsr(String Username) {
	        return exampublishMapper.findbyUsr(Username);
	 }

}
