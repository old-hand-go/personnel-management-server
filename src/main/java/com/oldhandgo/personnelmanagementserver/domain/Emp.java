package com.oldhandgo.personnelmanagementserver.domain;

/**
 * @author shiro
 */

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Data
@Entity
public class Emp {
    @Id
    private int id;

    private String ename;//姓名

    private String epwd;//密码

    private String ex;//性别

    private String edata;//出生日期

    private String education;//学历

    private String ephone;//手机

    private String email;//邮箱

    private String eadr;//住址

    private String edepartment;//部门

    private String eposition;//职位

    private String erdata;//入职日期

    private double salary;//薪水

    private String hobby;//爱好
}
