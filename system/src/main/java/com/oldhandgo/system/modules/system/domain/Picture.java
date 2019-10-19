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
public class Picture {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String filename;
    private String height;
    private String size;
    private String url;
    private String username;
    private String width;
    private String deleteUrl;

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
    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "height")
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    @Column(name = "width")
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Basic
    @Column(name = "delete_url")
    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id) &&
                Objects.equals(createTime, picture.createTime) &&
                Objects.equals(updateTime, picture.updateTime) &&
                Objects.equals(filename, picture.filename) &&
                Objects.equals(height, picture.height) &&
                Objects.equals(size, picture.size) &&
                Objects.equals(url, picture.url) &&
                Objects.equals(username, picture.username) &&
                Objects.equals(width, picture.width) &&
                Objects.equals(deleteUrl, picture.deleteUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, filename, height, size, url, username, width, deleteUrl);
    }
}
