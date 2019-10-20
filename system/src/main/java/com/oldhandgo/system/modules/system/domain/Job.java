package com.oldhandgo.system.modules.system.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
public class Job {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String jobName;
    private Boolean isEnabled;
    private String sort;
    private Department departmentByDeptId;

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
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "is_enabled")
    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Basic
    @Column(name = "sort")
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        return Objects.equals(id, job.id) &&
                Objects.equals(createTime, job.createTime) &&
                Objects.equals(updateTime, job.updateTime) &&
                Objects.equals(jobName, job.jobName) &&
                Objects.equals(isEnabled, job.isEnabled) &&
                Objects.equals(sort, job.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, jobName, isEnabled, sort);
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public Department getDepartmentByDeptId() {
        return departmentByDeptId;
    }

    public void setDepartmentByDeptId(Department departmentByDeptId) {
        this.departmentByDeptId = departmentByDeptId;
    }
}
