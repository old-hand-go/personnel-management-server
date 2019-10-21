package com.oldhandgo.tools.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormirr
 */
@NoArgsConstructor
@Entity
@Table(name = "verification_code", schema = "personnel_management_server")
public class VerificationCode {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String codeValue;
    private String isType;
    private String codeUser;
    private String scenes;
    private Boolean isStatus;

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
    @Column(name = "code_value")
    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    @Basic
    @Column(name = "is_type")
    public String getType() {
        return isType;
    }

    public void setType(String type) {
        isType = type;
    }

    @Basic
    @Column(name = "code_user")
    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    @Basic
    @Column(name = "scenes")
    public String getScenes() {
        return scenes;
    }

    public void setScenes(String scenes) {
        this.scenes = scenes;
    }

    @Basic
    @Column(name = "is_status")
    public Boolean getStatus() {
        return isStatus;
    }

    public void setStatus(Boolean status) {
        isStatus = status;
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
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(codeValue, that.codeValue) &&
                Objects.equals(isType, that.isType) &&
                Objects.equals(codeUser, that.codeUser) &&
                Objects.equals(scenes, that.scenes) &&
                Objects.equals(isStatus, that.isStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, codeValue, isType, codeUser, scenes, isStatus);
    }

    public VerificationCode(String code, String scenes, @NotBlank String type, @NotBlank String value) {
        this.codeValue = code;
        this.scenes = scenes;
        this.isType = type;
        this.codeUser = value;
    }
}
