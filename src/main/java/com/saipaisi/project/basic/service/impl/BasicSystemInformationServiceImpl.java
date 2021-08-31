package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicSystemInformationMapper;
import com.saipaisi.project.basic.domain.BasicSystemInformation;
import com.saipaisi.project.basic.service.BasicSystemInformationService;

/**
 * 体系信息Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicSystemInformationServiceImpl implements BasicSystemInformationService
{
    @Autowired
    private BasicSystemInformationMapper basicSystemInformationMapper;

    /**
     * 查询体系信息
     * 
     * @param id 体系信息ID
     * @return 体系信息
     */
    @Override
    public BasicSystemInformation selectBasicSystemInformationById(Long id)
    {
        return basicSystemInformationMapper.selectBasicSystemInformationById(id);
    }

    @Override
    public List<BasicSystemInformation> selectBasicSystemInformationByOrdersn(String ordersn) {
        return basicSystemInformationMapper.selectBasicSystemInformationByOrdersn(ordersn);
    }

    /**
     * 查询体系信息列表
     * 
     * @param basicSystemInformation 体系信息
     * @return 体系信息
     */
    @Override
    public List<BasicSystemInformation> selectBasicSystemInformationList(BasicSystemInformation basicSystemInformation)
    {
        return basicSystemInformationMapper.selectBasicSystemInformationList(basicSystemInformation);
    }

    /**
     * 新增体系信息
     * 
     * @param basicSystemInformation 体系信息
     * @return 结果
     */
    @Override
    public int insertBasicSystemInformation(BasicSystemInformation basicSystemInformation)
    {
        basicSystemInformation.setCreateTime(DateUtils.getNowDate());
        return basicSystemInformationMapper.insertBasicSystemInformation(basicSystemInformation);
    }

    /**
     * 修改体系信息
     * 
     * @param basicSystemInformation 体系信息
     * @return 结果
     */
    @Override
    public int updateBasicSystemInformation(BasicSystemInformation basicSystemInformation)
    {
        basicSystemInformation.setUpdateTime(DateUtils.getNowDate());
        return basicSystemInformationMapper.updateBasicSystemInformation(basicSystemInformation);
    }

    /**
     * 批量删除体系信息
     * 
     * @param ids 需要删除的体系信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicSystemInformationByIds(Long[] ids)
    {
        return basicSystemInformationMapper.deleteBasicSystemInformationByIds(ids);
    }

    /**
     * 删除体系信息信息
     * 
     * @param id 体系信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicSystemInformationById(Long id)
    {
        return basicSystemInformationMapper.deleteBasicSystemInformationById(id);
    }

    @Override
    public int deleteBasicSystemInformationByOrdersn(String ordersn) {
        return basicSystemInformationMapper.deleteBasicSystemInformationByOrdersn(ordersn);
    }
}
