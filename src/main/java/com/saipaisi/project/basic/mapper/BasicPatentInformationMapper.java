package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicPatentInformation;

/**
 * 专利信息Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicPatentInformationMapper 
{
    /**
     * 查询专利信息
     * 
     * @param id 专利信息ID
     * @return 专利信息
     */
    public BasicPatentInformation selectBasicPatentInformationById(Long id);

    /**
     * 查询专利信息列表
     * 
     * @param basicPatentInformation 专利信息
     * @return 专利信息集合
     */
    public List<BasicPatentInformation> selectBasicPatentInformationList(BasicPatentInformation basicPatentInformation);

    /**
     * 新增专利信息
     * 
     * @param basicPatentInformation 专利信息
     * @return 结果
     */
    public int insertBasicPatentInformation(BasicPatentInformation basicPatentInformation);

    /**
     * 修改专利信息
     * 
     * @param basicPatentInformation 专利信息
     * @return 结果
     */
    public int updateBasicPatentInformation(BasicPatentInformation basicPatentInformation);

    /**
     * 删除专利信息
     * 
     * @param id 专利信息ID
     * @return 结果
     */
    public int deleteBasicPatentInformationById(Long id);

    /**
     * 批量删除专利信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicPatentInformationByIds(Long[] ids);
}
