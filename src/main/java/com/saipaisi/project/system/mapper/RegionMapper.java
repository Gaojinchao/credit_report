package com.saipaisi.project.system.mapper;

import com.saipaisi.project.system.domain.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author alfred.zong
 * @Date 2020/6/15 15:32
 * @Description
 */
public interface RegionMapper {

    List<Region> list(@Param("pid") Long pid);

    Long getIdByName(@Param("name") String name);

    List<Region> allList();
}
