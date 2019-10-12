package com.oldhandgo.system.modules.system.domain;

import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author dormir
 */
@ToString
@Entity
public class Menu {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte isFrame;
    private String menuName;
    private String component;
    private String compinentName;
    private Long pid;
    private String sort;
    private Byte isCache;
    private Byte isHidden;

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
    @Column(name = "is_frame")
    public Byte getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(Byte isFrame) {
        this.isFrame = isFrame;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "component")
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Basic
    @Column(name = "compinent_name")
    public String getCompinentName() {
        return compinentName;
    }

    public void setCompinentName(String compinentName) {
        this.compinentName = compinentName;
    }

    @Basic
    @Column(name = "pid")
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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
    @Column(name = "is_cache")
    public Byte getIsCache() {
        return isCache;
    }

    public void setIsCache(Byte isCache) {
        this.isCache = isCache;
    }

    @Basic
    @Column(name = "is_hidden")
    public Byte getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Byte isHidden) {
        this.isHidden = isHidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(createTime, menu.createTime) &&
                Objects.equals(updateTime, menu.updateTime) &&
                Objects.equals(isFrame, menu.isFrame) &&
                Objects.equals(menuName, menu.menuName) &&
                Objects.equals(component, menu.component) &&
                Objects.equals(compinentName, menu.compinentName) &&
                Objects.equals(pid, menu.pid) &&
                Objects.equals(sort, menu.sort) &&
                Objects.equals(isCache, menu.isCache) &&
                Objects.equals(isHidden, menu.isHidden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, isFrame, menuName, component, compinentName, pid, sort, isCache, isHidden);
    }
}
