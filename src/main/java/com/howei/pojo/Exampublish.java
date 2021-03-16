package com.howei.pojo;

/**
 * 考试历史记录
 * @author zd
 *
 */
public class Exampublish {
	
	private int id;
	
	private int chapterId;
	
	private int courseId;
	
	private String poisionA;
	
	private String poisionB1;
	
	private String startdate;
	
	private String enddate;
	
	private int  state;
	
	private int  week;
	
	private int examTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getPoisionA() {
		return poisionA;
	}

	public void setPoisionA(String poisionA) {
		this.poisionA = poisionA;
	}

	public String getPoisionB1() {
		return poisionB1;
	}

	public void setPoisionB1(String poisionB1) {
		this.poisionB1 = poisionB1;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getExamTime() {
		return examTime;
	}

	public void setExamTime(int examTime) {
		this.examTime = examTime;
	}

	
}
