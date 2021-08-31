package com.saipaisi.project.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author alfred.zong
 * @Date 2020/10/26 14:06
 * @Description
 */
@Data
public class ReslutVO implements Serializable {
    private Integer dialTheNumber;

    private Integer numberOfActiveCallsNum;
}
