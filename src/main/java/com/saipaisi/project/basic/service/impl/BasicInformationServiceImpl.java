package com.saipaisi.project.basic.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.common.utils.StringUtils;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicInformationMapper;
import com.saipaisi.project.basic.domain.BasicInformation;
import com.saipaisi.project.basic.service.BasicInformationService;

/**
 * 基本信息Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicInformationServiceImpl implements BasicInformationService
{
    @Autowired
    private BasicInformationMapper basicInformationMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询基本信息
     * 
     * @param id 基本信息ID
     * @return 基本信息
     */
    @Override
    public BasicInformation selectBasicInformationById(Long id)
    {
        return basicInformationMapper.selectBasicInformationById(id);
    }

    /**
     * 查询基本信息列表
     * 
     * @param basicInformation 基本信息
     * @return 基本信息
     */
    @Override
    public List<BasicInformation> selectBasicInformationList(BasicInformation basicInformation)
    {
        return basicInformationMapper.selectBasicInformationList(basicInformation);
    }

    /**
     * 新增基本信息
     * 
     * @param basicInformation 基本信息
     * @return 结果
     */
    @Override
    public int insertBasicInformation(BasicInformation basicInformation)
    {
        basicInformation.setCreateTime(DateUtils.getNowDate());
        return basicInformationMapper.insertBasicInformation(basicInformation);
    }

    /**
     * 修改基本信息
     * 
     * @param basicInformation 基本信息
     * @return 结果
     */
    @Override
    public int updateBasicInformation(BasicInformation basicInformation)
    {
        basicInformation.setUpdateTime(DateUtils.getNowDate());
        return basicInformationMapper.updateBasicInformation(basicInformation);
    }

    /**
     * 批量删除基本信息
     * 
     * @param ids 需要删除的基本信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicInformationByIds(Long[] ids)
    {
        return basicInformationMapper.deleteBasicInformationByIds(ids);
    }

    /**
     * 删除基本信息信息
     * 
     * @param id 基本信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicInformationById(Long id)
    {
        return basicInformationMapper.deleteBasicInformationById(id);
    }

    @Override
    public int qxbInfoSearch(String keyword,String ordersn) {
        SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword(keyword);
        Map<String,Object> map=qxbHttps.getBasicInfo(searchApiEntity);
        if ("200".equals(map.get("status"))){
            Map<String,Object> reslut= (Map<String, Object>) map.get("data");
            BasicInformation basicInformation=new BasicInformation();
            basicInformation.setName((String) reslut.get("name"));
            basicInformation.setAddress((String) reslut.get("address"));
            basicInformation.setStartDate((String) reslut.get("startDate"));
            basicInformation.setStatus((String) reslut.get("status"));
            basicInformation.setCreditNo((String) reslut.get("creditNo"));
            basicInformation.setScope((String) reslut.get("scope"));
            basicInformation.setRegCapiDesc(new BigDecimal(StringUtils.digitExtract((String) reslut.get("registCapi"))));
            basicInformation.setActualCapi(new BigDecimal(StringUtils.digitExtract((String)reslut.get("actualCapi"))));
            basicInformation.setEconKind((String) reslut.get("econKind"));
            basicInformation.setOperName((String) reslut.get("operName"));
            basicInformation.setIndusty((String) reslut.get("domain"));
            basicInformation.setOrderSn(ordersn);
            basicInformationMapper.insertBasicInformation(basicInformation);
        }else {
            throw new CustomException((String) map.get("message"));
        }
        return 0;
    }

    @Override
    public BasicInformation selectBasicInformationByordersn(String ordersn) {
        return basicInformationMapper.selectBasicInformationByordersn(ordersn);
    }


}
