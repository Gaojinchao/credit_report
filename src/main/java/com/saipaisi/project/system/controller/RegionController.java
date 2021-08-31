package com.saipaisi.project.system.controller;

import com.saipaisi.framework.web.controller.BaseController;

import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.project.system.domain.Region;
import com.saipaisi.project.system.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * @Author alfred.zong
 * @Date 2020/6/15 15:39
 * @Description
 */
@RestController
@RequestMapping("/region")
public class RegionController extends BaseController {


    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list(){
        List<Region> list=regionService.list();
        return new AjaxResult(1,"查询成功！",list);
    }
}
