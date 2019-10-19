package com.oldhandgo.system.modules.system.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
public class User {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String email;
    private String userName;
    private String passWord;
    private String address;
    private Long deptId;
    private Long jobId;
    private Boolean isEnabled;

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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "pass_word")
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "dept_id")
    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "job_id")
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "is_enabled")
    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(updateTime, user.updateTime) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(passWord, user.passWord) &&
                Objects.equals(address, user.address) &&
                Objects.equals(deptId, user.deptId) &&
                Objects.equals(jobId, user.jobId) &&
                Objects.equals(isEnabled, user.isEnabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, email, userName, passWord, address, deptId, jobId, isEnabled);
    }
}
