package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicSystemInformation;

/**
 * 体系信息Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicSystemInformationMapper 
{
    /**
     * 查询体系信息
     * 
     * @param id 体系信息ID
     * @return 体系信息
     */
    public BasicSystemInformation selectBasicSystemInformationById(Long id);

    /**
     * 查询体系信息
     *
     * @param ordersn 体系信息ID
     * @return 体系信息
     */
    public List<BasicSystemInformation> selectBasicSystemInformationByOrdersn(String ordersn);

    /**
     * 查询体系信息列表
     * 
     * @param basicSystemInformation 体系信息
     * @return 体系信息集合
     */
    public List<BasicSystemInformation> selectBasicSystemInformationList(BasicSystemInformation basicSystemInformation);

    /**
     * 新增体系信息
     * 
     * @param basicSystemInformation 体系信息
     * @return 结果
     */
    public int insertBasicSystemInformation(BasicSystemInformation basicSystemInformation);

    /**
     * 修改体系信息
     * 
     * @param basicSystemInformation 体系信息
     * @return 结果
     */
    public int updateBasicSystemInformation(BasicSystemInformation basicSystemInformation);

    /**
     * 删除体系信息
     * 
     * @param id 体系信息ID
     * @return 结果
     */
    public int deleteBasicSystemInformationById(Long id);

    /**
     * 批量删除体系信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicSystemInformationByIds(Long[] ids);

    /**
     * 根据订单编号删除订单
     * @return
     */
    public int deleteBasicSystemInformationByOrdersn(String ordersn);
}
