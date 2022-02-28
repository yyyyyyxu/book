package com.yanxu.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail implements Serializable {
    private static final long serialVersionUID = -3548904816833423926L;
    /**
     * 邮件接收方，可多人
     */
    private String tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

}
