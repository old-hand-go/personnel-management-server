package com.oldhandgo.system.modules.system.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
@Table(name = "dict_detail", schema = "personnel_management_server")
public class DictDetail {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String label;
    private String dictValue;
    private String sort;
    private Dict dictByDictId;

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
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "dict_value")
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    @Basic
    @Column(name = "sort")
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 字典id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dict_id")
    private Dict dict;

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
                Objects.equals(label, that.label) &&
                Objects.equals(dictValue, that.dictValue) &&
                Objects.equals(sort, that.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, label, dictValue, sort);
    }

    @ManyToOne
    @JoinColumn(name = "dict_id", referencedColumnName = "id")
    public Dict getDictByDictId() {
        return dictByDictId;
    }

    public void setDictByDictId(Dict dictByDictId) {
        this.dictByDictId = dictByDictId;
    }
}
