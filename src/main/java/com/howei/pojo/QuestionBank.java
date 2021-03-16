package com.howei.pojo;

public class QuestionBank {
	
	private int  courseId;
	
	private int  chapterId;
	
	private String poisionA;

	private String poisionB1;

	private String poisionB2;

	private String poisionB3;
	
	private String question;

	private String answer;

	private String optionA;

	private String optionB;
	
	private String optionC;
	
	private String optionD;

	private String remarks;
	
	private int  questionTime;

	private int  type;

	private int  questionId;
		

    private int id;
	private String startdate;
    private String enddate;
    private Integer times;
    private int state;
    private int week;
    private int examTime;
    private String UserName;
    private Integer Cycle;

	public QuestionBank() {
		super();
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
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

	public String getPoisionB2() {
		return poisionB2;
	}

	public void setPoisionB2(String poisionB2) {
		this.poisionB2 = poisionB2;
	}

	public String getPoisionB3() {
		return poisionB3;
	}

	public void setPoisionB3(String poisionB3) {
		this.poisionB3 = poisionB3;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getQuestionTime() {
		return questionTime;
	}

	public void setQuestionTime(int questionTime) {
		this.questionTime = questionTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
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

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public Integer getCycle() {
		return Cycle;
	}

	public void setCycle(Integer cycle) {
		Cycle = cycle;
	}

	@Override
	public String toString() {
		return "QuestionBank [courseId=" + courseId + ", chapterId=" + chapterId + ", poisionA=" + poisionA
				+ ", poisionB1=" + poisionB1 + ", poisionB2=" + poisionB2 + ", poisionB3=" + poisionB3 + ", question="
				+ question + ", answer=" + answer + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC="
				+ optionC + ", optionD=" + optionD + ", remarks=" + remarks + ", questionTime=" + questionTime
				+ ", type=" + type + ", questionId=" + questionId + "]";
	}
	
	
	
}
