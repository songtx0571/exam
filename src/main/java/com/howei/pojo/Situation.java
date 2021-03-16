package com.howei.pojo;

public class Situation {
    private String UserName;
    private int CourseID;
    private double ActualCredit;
    private double Actualperiod;
    private double TestScore;
    private String Rank;
    private Integer times;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public double getActualCredit() {
        return ActualCredit;
    }

    public void setActualCredit(double ActualCredit) {
        this.ActualCredit = ActualCredit;
    }

    public double getActualperiod() {
        return Actualperiod;
    }

    public void setActualperiod(double Actualperiod) {
        this.Actualperiod = Actualperiod;
    }

    public double getTestScore() {
        return TestScore;
    }

    public void setTestScore(double TestScore) {
        this.TestScore = TestScore;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String Rank) {
        this.Rank = Rank;
    }

    @Override
    public String toString() {
        return "Situation{" + "UserName='" + UserName + '\'' + ", CourseID='" + CourseID + '\'' + ", ActualCredit='"
                + ActualCredit + '\'' + ", Actualperiod='" + Actualperiod + '\'' + ", TestScore='" + TestScore + '\''
                + ", Rank='" + Rank + '\'' + '}';
    }
}
