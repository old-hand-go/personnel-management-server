package com.oldhandgo.system.modules.system.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author dormirr
 */
@Entity
@Table(name = "user_avatar", schema = "personnel_management_server")
public class UserAvatar {
    private Long id;
    private String realName;
    private String avatarPath;
    private String size;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "avatar_path")
    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAvatar that = (UserAvatar) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(realName, that.realName) &&
                Objects.equals(avatarPath, that.avatarPath) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, realName, avatarPath, size);
    }
}
