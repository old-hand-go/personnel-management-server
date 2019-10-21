package com.oldhandgo.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author dormirr
 */
@Data
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String url;

    private String size;

    private String height;

    private String width;

    /**
     * delete URl
     */
    @Column(name = "delete_url")
    private String delete;

    private String username;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @Override
    public String toString() {
        return "Picture{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
