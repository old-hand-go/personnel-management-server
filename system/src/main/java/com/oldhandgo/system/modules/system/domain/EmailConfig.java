package com.oldhandgo.system.modules.system.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
@Table(name = "email_config", schema = "personnel_management_server")
public class EmailConfig {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String emailUser;
    private String emailPass;
    private String fromUser;
    private String emailHost;
    private String emailPort;

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
    @Column(name = "email_user")
    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    @Basic
    @Column(name = "email_pass")
    public String getEmailPass() {
        return emailPass;
    }

    public void setEmailPass(String emailPass) {
        this.emailPass = emailPass;
    }

    @Basic
    @Column(name = "from_user")
    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Basic
    @Column(name = "email_host")
    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    @Basic
    @Column(name = "email_port")
    public String getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmailConfig that = (EmailConfig) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(emailUser, that.emailUser) &&
                Objects.equals(emailPass, that.emailPass) &&
                Objects.equals(fromUser, that.fromUser) &&
                Objects.equals(emailHost, that.emailHost) &&
                Objects.equals(emailPort, that.emailPort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, emailUser, emailPass, fromUser, emailHost, emailPort);
    }
}
