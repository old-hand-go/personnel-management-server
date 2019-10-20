package com.oldhandgo.system.modules.system.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
public class Dict {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dictName;
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
    @Column(name = "dict_name")
    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(mappedBy = "dict", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<DictDetail> dictDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dict dict = (Dict) o;
        return Objects.equals(id, dict.id) &&
                Objects.equals(createTime, dict.createTime) &&
                Objects.equals(updateTime, dict.updateTime) &&
                Objects.equals(dictName, dict.dictName) &&
                Objects.equals(remark, dict.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, dictName, remark);
    }
}
