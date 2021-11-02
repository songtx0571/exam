package com.howei.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 知识库
 */
public class Knowledge {

    private Integer id; //
    private Integer employeeId;
    private String employeeName;


    private String title;
    private String keyword;
    private String content;
    private String remark;
    private Integer status; //审核状态,0不通过,1通过
    private Integer type; //类型,0编辑库,1文档库
    private Integer heat;//热度
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime passTime;
    private List<KnowledgeCheckRecord> knowledgeCheckRecordList;


    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", title='" + title + '\'' +
                ", keyword='" + keyword + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", heat=" + heat +
                ", createTime=" + createTime +
                ", knowledgeCheckRecordList=" + knowledgeCheckRecordList +
                ", updateTime=" + updateTime +
                '}';
    }


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<KnowledgeCheckRecord> getKnowledgeCheckRecordList() {
        return knowledgeCheckRecordList;
    }

    public void setKnowledgeCheckRecordList(List<KnowledgeCheckRecord> knowledgeCheckRecordList) {
        this.knowledgeCheckRecordList = knowledgeCheckRecordList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getPassTime() {
        return passTime;
    }

    public void setPassTime(LocalDateTime passTime) {
        this.passTime = passTime;
    }
}
