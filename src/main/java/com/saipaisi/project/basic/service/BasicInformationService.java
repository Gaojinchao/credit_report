package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicInformation;

/**
 * 基本信息Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicInformationService
{
    /**
     * 查询基本信息
     * 
     * @param id 基本信息ID
     * @return 基本信息
     */
    public BasicInformation selectBasicInformationById(Long id);

    /**
     * 查询基本信息列表
     * 
     * @param basicInformation 基本信息
     * @return 基本信息集合
     */
    public List<BasicInformation> selectBasicInformationList(BasicInformation basicInformation);

    /**
     * 新增基本信息
     * 
     * @param basicInformation 基本信息
     * @return 结果
     */
    public int insertBasicInformation(BasicInformation basicInformation);

    /**
     * 修改基本信息
     * 
     * @param basicInformation 基本信息
     * @return 结果
     */
    public int updateBasicInformation(BasicInformation basicInformation);

    /**
     * 批量删除基本信息
     * 
     * @param ids 需要删除的基本信息ID
     * @return 结果
     */
    public int deleteBasicInformationByIds(Long[] ids);

    /**
     * 删除基本信息信息
     * 
     * @param id 基本信息ID
     * @return 结果
     */
    public int deleteBasicInformationById(Long id);


    public int qxbInfoSearch(String keyword,String ordersn);

    /**
     * 查询基本信息根据订单编号查询
     *
     * @param ordersn 基本信息ID
     * @return 基本信息
     */
    public BasicInformation selectBasicInformationByordersn(String ordersn);
}
