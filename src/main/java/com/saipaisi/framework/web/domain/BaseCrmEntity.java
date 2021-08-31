package com.saipaisi.framework.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author alfred.zong
 * @Date 2020/8/12 20:08
 * @Description
 */
@Data
public class BaseCrmEntity implements Serializable {

    private Long companyId;

    private String dataScope;

    private Date createTime;

    private Date updateTime;
}
