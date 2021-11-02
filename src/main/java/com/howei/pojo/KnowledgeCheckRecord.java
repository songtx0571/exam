package com.howei.pojo;

import java.time.LocalDateTime;

public class KnowledgeCheckRecord {
    private Integer id;
    private Integer knowledgeId;
    private Integer employeeId;
    private String employeeName;
    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "KnowledgeCheckRecord{" +
                "id=" + id +
                ", knowledgeId=" + knowledgeId +
                ", employeeId=" + employeeId +
                ", employeeName=" + employeeName +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
