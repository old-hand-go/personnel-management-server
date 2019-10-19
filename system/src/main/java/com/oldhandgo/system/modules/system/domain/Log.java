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
public class Log {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateName;
    private String logDescription;
    private String logType;
    private String method;
    private String params;
    private String exceptionDetail;
    private String username;
    private String requestIp;
    private Long requestTime;
    private String address;

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
    @Column(name = "update_name")
    public Timestamp getUpdateName() {
        return updateName;
    }

    public void setUpdateName(Timestamp updateName) {
        this.updateName = updateName;
    }

    @Basic
    @Column(name = "log_description")
    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    @Basic
    @Column(name = "log_type")
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
    @Column(name = "exception_detail")
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "request_ip")
    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    @Basic
    @Column(name = "request_time")
    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Log log = (Log) o;
        return Objects.equals(id, log.id) &&
                Objects.equals(createTime, log.createTime) &&
                Objects.equals(updateName, log.updateName) &&
                Objects.equals(logDescription, log.logDescription) &&
                Objects.equals(logType, log.logType) &&
                Objects.equals(method, log.method) &&
                Objects.equals(params, log.params) &&
                Objects.equals(exceptionDetail, log.exceptionDetail) &&
                Objects.equals(username, log.username) &&
                Objects.equals(requestIp, log.requestIp) &&
                Objects.equals(requestTime, log.requestTime) &&
                Objects.equals(address, log.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateName, logDescription, logType, method, params, exceptionDetail, username, requestIp, requestTime, address);
    }
}
