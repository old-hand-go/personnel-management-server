package com.oldhandgo.system.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author dormirr
 */
@Data
@Entity
public class Role {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String roleName;
    private Short roleLevel;
    private String dataScope;
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
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_level")
    public Short getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Short roleLevel) {
        this.roleLevel = roleLevel;
    }

    @Basic
    @Column(name = "data_scope")
    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "roles_permissions", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private Set<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "roles_menus", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
    private Set<Menu> menus;

    @ManyToMany
    @JoinTable(name = "roles_departments", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "id")})
    private Set<Department> departments;
}
