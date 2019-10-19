package com.oldhandgo.system.modules.monitor.domain;

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
public class Visits {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long ipCounts;
    private Long pvCounts;
    private String weekDay;

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
    @Column(name = "ip_counts")
    public Long getIpCounts() {
        return ipCounts;
    }

    public void setIpCounts(Long ipCounts) {
        this.ipCounts = ipCounts;
    }

    @Basic
    @Column(name = "pv_counts")
    public Long getPvCounts() {
        return pvCounts;
    }

    public void setPvCounts(Long pvCounts) {
        this.pvCounts = pvCounts;
    }

    @Basic
    @Column(name = "week_day")
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Visits visits = (Visits) o;
        return Objects.equals(id, visits.id) &&
                Objects.equals(createTime, visits.createTime) &&
                Objects.equals(updateTime, visits.updateTime) &&
                Objects.equals(ipCounts, visits.ipCounts) &&
                Objects.equals(pvCounts, visits.pvCounts) &&
                Objects.equals(weekDay, visits.weekDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, ipCounts, pvCounts, weekDay);
    }
}
