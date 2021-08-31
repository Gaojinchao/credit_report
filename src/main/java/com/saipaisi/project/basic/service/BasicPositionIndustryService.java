package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicPositionIndustry;

/**
 * 行业地位Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicPositionIndustryService
{
    /**
     * 查询行业地位
     * 
     * @param id 行业地位ID
     * @return 行业地位
     */
    public BasicPositionIndustry selectBasicPositionIndustryById(Long id);

    /**
     * 查询行业地位
     *
     * @param ordersn 行业地位ordersn
     * @return 行业地位
     */
    public List<BasicPositionIndustry> selectBasicPositionIndustryByOrdersn(String ordersn);

    /**
     * 查询行业地位列表
     * 
     * @param basicPositionIndustry 行业地位
     * @return 行业地位集合
     */
    public List<BasicPositionIndustry> selectBasicPositionIndustryList(BasicPositionIndustry basicPositionIndustry);

    /**
     * 新增行业地位
     * 
     * @param basicPositionIndustry 行业地位
     * @return 结果
     */
    public int insertBasicPositionIndustry(BasicPositionIndustry basicPositionIndustry);

    /**
     * 修改行业地位
     * 
     * @param basicPositionIndustry 行业地位
     * @return 结果
     */
    public int updateBasicPositionIndustry(BasicPositionIndustry basicPositionIndustry);

    /**
     * 批量删除行业地位
     * 
     * @param ids 需要删除的行业地位ID
     * @return 结果
     */
    public int deleteBasicPositionIndustryByIds(Long[] ids);

    /**
     * 删除行业地位信息
     * 
     * @param id 行业地位ID
     * @return 结果
     */
    public int deleteBasicPositionIndustryById(Long id);

    /**
     * 根据订单编号删除
     * @return
     */
    public int deleteBasicPositionIndustryByOrdersn(String ordersn);
}
