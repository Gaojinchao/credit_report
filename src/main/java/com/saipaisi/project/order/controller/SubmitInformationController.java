package com.saipaisi.project.order.controller;

import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.project.order.domain.AllMainInfoReq;
import com.saipaisi.project.order.service.SubmitInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

/**
 * @Author alfred.zong
 * @Date 2021/5/26 19:24
 * @Description
 */
@RestController
@RequestMapping("/submit/info")
public class SubmitInformationController {


    @Autowired
    private SubmitInformationService submitInformationService;



    /**
     * 提交
     * @param allMainInfoReq
     * @return
     */
    @PostMapping("/submit")
    public AjaxResult submitInfo(@RequestBody AllMainInfoReq allMainInfoReq) throws FileNotFoundException {
        submitInformationService.submitInfoAll(allMainInfoReq);
        return new AjaxResult(200,"");
    }


    @RequestMapping(value = "/getinfo",method = RequestMethod.GET)
    public AjaxResult getall(@RequestParam("ordersn")String ordersn){
        AllMainInfoReq allMainInfoReq=submitInformationService.selectAll(ordersn);
        return new AjaxResult(200,"",allMainInfoReq);
    }
}
