package com.oldhandgo.system.modules.quartz.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
@Table(name = "quartz_log", schema = "personnel_management_server")
public class QuartzLog {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String baenName;
    private String cronExpression;
    private String jobName;
    private Boolean isSuccess;
    private String methodName;
    private String params;
    private Long jobTime;
    private String exceptionDetail;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "baen_name")
    public String getBaenName() {
        return baenName;
    }

    public void setBaenName(String baenName) {
        this.baenName = baenName;
    }

    @Basic
    @Column(name = "cron_expression")
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Basic
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "is_success")
    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    @Basic
    @Column(name = "method_name")
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Basic
    @Column(name = "params")
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Basic
    @Column(name = "job_time")
    public Long getJobTime() {
        return jobTime;
    }

    public void setJobTime(Long jobTime) {
        this.jobTime = jobTime;
    }

    @Basic
    @Column(name = "exception_detail")
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuartzLog quartzLog = (QuartzLog) o;
        return Objects.equals(id, quartzLog.id) &&
                Objects.equals(createTime, quartzLog.createTime) &&
                Objects.equals(updateTime, quartzLog.updateTime) &&
                Objects.equals(baenName, quartzLog.baenName) &&
                Objects.equals(cronExpression, quartzLog.cronExpression) &&
                Objects.equals(jobName, quartzLog.jobName) &&
                Objects.equals(isSuccess, quartzLog.isSuccess) &&
                Objects.equals(methodName, quartzLog.methodName) &&
                Objects.equals(params, quartzLog.params) &&
                Objects.equals(jobTime, quartzLog.jobTime) &&
                Objects.equals(exceptionDetail, quartzLog.exceptionDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, baenName, cronExpression, jobName, isSuccess, methodName, params, jobTime, exceptionDetail);
    }
}
