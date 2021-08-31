package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicQualificationInformation;

/**
 * 资质信息Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicQualificationInformationService
{
    /**
     * 查询资质信息
     * 
     * @param id 资质信息ID
     * @return 资质信息
     */
    public BasicQualificationInformation selectBasicQualificationInformationById(Long id);

    /**
     * 查询资质信息列表
     * 
     * @param basicQualificationInformation 资质信息
     * @return 资质信息集合
     */
    public List<BasicQualificationInformation> selectBasicQualificationInformationList(BasicQualificationInformation basicQualificationInformation);

    /**
     * 新增资质信息
     * 
     * @param basicQualificationInformation 资质信息
     * @return 结果
     */
    public int insertBasicQualificationInformation(BasicQualificationInformation basicQualificationInformation);

    /**
     * 修改资质信息
     * 
     * @param basicQualificationInformation 资质信息
     * @return 结果
     */
    public int updateBasicQualificationInformation(BasicQualificationInformation basicQualificationInformation);

    /**
     * 批量删除资质信息
     * 
     * @param ids 需要删除的资质信息ID
     * @return 结果
     */
    public int deleteBasicQualificationInformationByIds(Long[] ids);

    /**
     * 删除资质信息信息
     * 
     * @param id 资质信息ID
     * @return 结果
     */
    public int deleteBasicQualificationInformationById(Long id);

    /**
     * 资质证书
     * @return
     */
    public int getCertificateByNameInfo(String keyword,String ordersn);
}
