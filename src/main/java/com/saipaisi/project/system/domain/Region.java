package com.saipaisi.project.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author alfred.zong
 * @Date 2020/6/15 15:24
 * @Description 城市
 */
@Data
public class Region implements Serializable {

    //城市名字
    private String cityName;

    //id
    private Long id;

    //父id
    private Long pid;

    /**
     * 子节点
     */
    private List<Region> childNode;
}
