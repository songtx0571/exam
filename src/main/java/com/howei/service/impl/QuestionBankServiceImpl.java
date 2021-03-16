package com.howei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howei.mapper.QuestionBankMapper;
import com.howei.pojo.Poision;
import com.howei.pojo.QuestionBank;
import com.howei.service.QuestionBankService;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

	@Autowired
	QuestionBankMapper questionBankMapper;

	
	@Override
	public List<QuestionBank> findRand(String CourseID, String ChapterID) {
		return questionBankMapper.findRand(CourseID, ChapterID);
	}


	@Override
	public List<String> findPoisionA() {
		return questionBankMapper.findPoisionA();
	}

	@Override
	public List<String> findPoisionB1(String option1) {
		return questionBankMapper.findPoisionB1(option1);
	}

	@Override
	public List<QuestionBank> findquestionpublish(QuestionBank questionBank) {
		return questionBankMapper.findquestionpublish(questionBank);
	}

	@Override
	public List<QuestionBank> findExercise(String PoisionA, String PoisionB1) {
		return questionBankMapper.findExercise(PoisionA, PoisionB1);
	}

	@Override
	public int delete(String questionID) {
		return questionBankMapper.delete(questionID);
	}

	@Override
	public int update(QuestionBank questionBank) {
		return questionBankMapper.update(questionBank);
	}

	@Override
	public int insert(QuestionBank questionBank) {
		return questionBankMapper.insert(questionBank);
	}


	@Override
	public List<QuestionBank> findquestiontips() {
		return questionBankMapper.findquestiontips();
	}
	
	@Override
	public List<QuestionBank> showQ(String PoisionA, String PoisionB1, int ChapterID) {
		return questionBankMapper.showQ(PoisionA, PoisionB1, ChapterID);
	}
	
	@Override
	public List<Poision> findbypoision(String PoisionA) {
		return questionBankMapper.findbypoision(PoisionA);
	}


	@Override
	public List<QuestionBank> showByExam(String UserName, String Cycle, Integer Week, Integer times) {
		return questionBankMapper.showByExam(UserName, Cycle, Week, times);
	}


	@Override
	public QuestionBank findAllfrompublish(Integer id) {
		return questionBankMapper.findAllfrompublish(id);
	}

	
	
	@Override
	public List<Integer> selectemployeeId(String cycle) {
		return questionBankMapper.selectemployeeId(cycle);
	}
	@Override
	public void startexam(Integer id) {
		questionBankMapper.startexam(id);
	}
	
	//结束考试：修改考试状态及员工考试情况（分数调整为0）
	@Override
	public void finishexam(Integer id) {
		questionBankMapper.finishexam(id);
		questionBankMapper.clearScore();
	}

	@Override
	public void insertscore(String week, String cycle) {
		questionBankMapper.insertscore(week, cycle);
	}


	@Override
	public Integer countstart() {

        return questionBankMapper.countstart();
	}

	@Override
	public void insertcycle(String cycle, Integer employeeId) {
		questionBankMapper.insertcycle(cycle, employeeId);
	}
	
	@Override
	public QuestionBank findAllByState() {
        return questionBankMapper.findAllByState();
    }
		
}
