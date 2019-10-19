package com.oldhandgo.system.modules.quartz.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
@Table(name = "quartz_job", schema = "personnel_management_server")
public class QuartzJob {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String beanName;
    private String cronExpression;
    private Boolean isPause;
    private String jobName;
    private String methodName;
    private String params;
    private String remark;

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
    @Column(name = "bean_name")
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
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
    @Column(name = "is_pause")
    public Boolean getPause() {
        return isPause;
    }

    public void setPause(Boolean pause) {
        isPause = pause;
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
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuartzJob quartzJob = (QuartzJob) o;
        return Objects.equals(id, quartzJob.id) &&
                Objects.equals(createTime, quartzJob.createTime) &&
                Objects.equals(updateTime, quartzJob.updateTime) &&
                Objects.equals(beanName, quartzJob.beanName) &&
                Objects.equals(cronExpression, quartzJob.cronExpression) &&
                Objects.equals(isPause, quartzJob.isPause) &&
                Objects.equals(jobName, quartzJob.jobName) &&
                Objects.equals(methodName, quartzJob.methodName) &&
                Objects.equals(params, quartzJob.params) &&
                Objects.equals(remark, quartzJob.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, beanName, cronExpression, isPause, jobName, methodName, params, remark);
    }
}
