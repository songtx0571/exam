package com.howei.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.howei.pojo.Poision;
import com.howei.pojo.QuestionBank;


public interface QuestionBankService {
	
	List<QuestionBank> findRand(String CourseID,String ChapterID);

	List<String> findPoisionA();
	
	List<String> findPoisionB1(String option1);
	
	List<QuestionBank> findquestionpublish(QuestionBank questionBank);

	List<QuestionBank> findExercise(String PoisionA, String PoisionB1);

	int delete(String questionID);

	int update(QuestionBank questionBank);
	
	int insert(QuestionBank questionBank);

    List<QuestionBank> findquestiontips();
    
    List<QuestionBank> showQ(String PoisionA, String PoisionB1, int ChapterID);
    
    List<Poision> findbypoision(String PoisionA);
    
    
    List<QuestionBank> showByExam(@Param("UserName") String UserName, @Param("Cycle") String Cycle, @Param("Week") Integer Week, @Param("times") Integer times);

    QuestionBank findAllfrompublish(Integer id);
    
    Integer countstart();
   
    void startexam(Integer id);
    
    void finishexam(Integer id);
    
    void insertscore(String week, String cycle);
    List<Integer> selectemployeeId(String cycle);
    
    void insertcycle(String cycle, Integer employeeId);
    
    QuestionBank findAllByState();
}
