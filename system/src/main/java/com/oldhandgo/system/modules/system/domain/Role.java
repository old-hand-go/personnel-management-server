package com.oldhandgo.system.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

/**
 * @author dormirr
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(createTime, role.createTime) &&
                Objects.equals(updateTime, role.updateTime) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(roleLevel, role.roleLevel) &&
                Objects.equals(dataScope, role.dataScope) &&
                Objects.equals(remark, role.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, roleName, roleLevel, dataScope, remark);
    }
}
