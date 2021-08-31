package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicHonor;

/**
 * 荣誉Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicHonorService
{
    /**
     * 查询荣誉
     * 
     * @param id 荣誉ID
     * @return 荣誉
     */
    public BasicHonor selectBasicHonorById(Long id);

    /**
     * 查询荣誉
     *
     * @param ordersn 荣誉ordersn
     * @return 荣誉
     */
    public List<BasicHonor> selectBasicHonorByordersn(String ordersn);


    /**
     * 查询荣誉列表
     * 
     * @param basicHonor 荣誉
     * @return 荣誉集合
     */
    public List<BasicHonor> selectBasicHonorList(BasicHonor basicHonor);

    /**
     * 新增荣誉
     * 
     * @param basicHonor 荣誉
     * @return 结果
     */
    public int insertBasicHonor(BasicHonor basicHonor);

    /**
     * 修改荣誉
     * 
     * @param basicHonor 荣誉
     * @return 结果
     */
    public int updateBasicHonor(BasicHonor basicHonor);

    /**
     * 批量删除荣誉
     * 
     * @param ids 需要删除的荣誉ID
     * @return 结果
     */
    public int deleteBasicHonorByIds(Long[] ids);

    /**
     * 删除荣誉信息
     * 
     * @param id 荣誉ID
     * @return 结果
     */
    public int deleteBasicHonorById(Long id);

    /**
     * 根据订单编号删除
     * @param ordersn
     * @return
     */
    public int deleteBasicHonorByOrdersn(String ordersn);
}
