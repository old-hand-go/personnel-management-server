package com.oldhandgo.system.modules.system.domain;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormir
 */
@ToString
@Entity
@Table(name = "verification_code", schema = "personnel-management-server")
public class VerificationCode {
    private Long id;
    private String codeValue;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte isStatus;
    private String type;
    private String userValue;
    private String scenes;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code_value")
    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
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
    @Column(name = "is_status")
    public Byte getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Byte isStatus) {
        this.isStatus = isStatus;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "user_value")
    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    @Basic
    @Column(name = "scenes")
    public String getScenes() {
        return scenes;
    }

    public void setScenes(String scenes) {
        this.scenes = scenes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VerificationCode that = (VerificationCode) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(codeValue, that.codeValue) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(isStatus, that.isStatus) &&
                Objects.equals(type, that.type) &&
                Objects.equals(userValue, that.userValue) &&
                Objects.equals(scenes, that.scenes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeValue, createTime, updateTime, isStatus, type, userValue, scenes);
    }
}
