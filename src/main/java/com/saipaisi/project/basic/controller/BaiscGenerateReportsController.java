package com.saipaisi.project.basic.controller;


import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.project.basic.service.BaiscGenerateReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报告生成控制层
 */
@RestController
@RequestMapping("/basic/generate")
public class BaiscGenerateReportsController extends BaseController {


    @Autowired
    private BaiscGenerateReportsService baiscGenerateReportsService;


    @GetMapping("/generate")
    public AjaxResult generateReports(@RequestParam("ordersn") String ordersn){
        baiscGenerateReportsService.generateReports(ordersn);
        return new AjaxResult(200,"");
    }


    @GetMapping("/score")
    public AjaxResult score(@RequestParam("ordersn") String ordersn){
        baiscGenerateReportsService.score(ordersn);
        return new AjaxResult(200,"");
    }

}
