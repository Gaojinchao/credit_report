package com.saipaisi.project.system.service.impl;

import com.saipaisi.project.system.domain.Region;
import com.saipaisi.project.system.mapper.RegionMapper;
import com.saipaisi.project.system.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author alfred.zong
 * @Date 2020/6/15 15:37
 * @Description
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> list() {
        List<Region> list=regionMapper.allList();
        return lists(list,1L);
    }

    public  List<Region> lists(List<Region> list,Long pid){
       List<Region> regions=new ArrayList<>();
       list.forEach(e->{
         if (e.getPid().equals(pid)){
             List<Region> regionList=lists(list,e.getId());
             e.setChildNode(regionList);
             regions.add(e);
         }
       });
       return regions;

    }
}
