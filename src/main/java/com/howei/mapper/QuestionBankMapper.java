package com.howei.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.howei.pojo.Poision;
import com.howei.pojo.QuestionBank;


@Component
@Mapper
public interface QuestionBankMapper {

	List<QuestionBank> findRand(@Param("CourseID") String CourseID, @Param("ChapterID") String ChapterID);

	List<String> findPoisionA();
	
	List<String> findPoisionB1(@Param("option1") String option1);
	
	List<QuestionBank> findquestionpublish(QuestionBank questionBank);

	List<QuestionBank> findExercise(@Param("PoisionA") String PoisionA, @Param("PoisionB1") String PoisionB1);

	int delete(@Param("questionID") String questionID);

	int update(QuestionBank questionBank);
	
	int insert(QuestionBank questionBank);


    List<QuestionBank> findquestiontips();
    
    List<QuestionBank> showQ(String poisionA, String poisionB1, int chapterId);
    
    List<Poision> findbypoision(String PoisionA);
    
    List<QuestionBank> showByExam(@Param("UserName") String UserName, @Param("Cycle") String Cycle, @Param("Week") Integer Week, @Param("times") Integer times);

    
    void insertscore(@Param("week") String week, @Param("cycle") String cycle);//1

    QuestionBank findAllfrompublish(@Param("id") Integer id);  //1
    
    List<Integer> selectemployeeId(@Param("cycle") String cycle);//1

    void insertcycle(@Param("cycle") String cycle, @Param("employeeId") Integer employeeId);  //1

    void startexam(@Param("id") Integer id);//1

    void finishexam(@Param("id") Integer id);//1

    void clearScore();
    
    Integer countstart();
    
    
    
    QuestionBank findAllByState();
}
