package com.saipaisi.project.basic.service;



/**
 * 生成报告Service接口
 *
 * @author sss
 * @date 2021-05-20
 */
public interface BaiscGenerateReportsService {


    /**
     * 生成word
     */
    public void generateReports(String ordersn);

    /**
     * 评分
     */
    public void score(String ordersn);
}
