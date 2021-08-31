package com.saipaisi.project.system.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author alfred.zong
 * @Date 2020/8/25 11:42
 * @Description 系統公告
 */
@Data
public class SysNoticeUser implements Serializable {

    private Long id;

    private Long userId;

    private Long noticeId;

    private String states;
}
