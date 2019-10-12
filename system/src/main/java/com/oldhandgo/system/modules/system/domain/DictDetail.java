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
@Table(name = "dict_detail", schema = "personnel-management-server")
public class DictDetail {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dictDetailValue;
    private String label;
    private String sort;
    private Long dictId;

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
    @Column(name = "dict_detail_value")
    public String getDictDetailValue() {
        return dictDetailValue;
    }

    public void setDictDetailValue(String dictDetailValue) {
        this.dictDetailValue = dictDetailValue;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "sort")
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "dict_id")
    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DictDetail that = (DictDetail) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(dictDetailValue, that.dictDetailValue) &&
                Objects.equals(label, that.label) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(dictId, that.dictId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, dictDetailValue, label, sort, dictId);
    }
}
