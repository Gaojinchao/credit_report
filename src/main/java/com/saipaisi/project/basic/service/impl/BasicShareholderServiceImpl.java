package com.saipaisi.project.basic.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.common.utils.StringUtils;
import com.saipaisi.project.basic.domain.BasicInformation;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import com.saipaisi.project.basic.mapper.BasicInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicShareholderMapper;
import com.saipaisi.project.basic.domain.BasicShareholder;
import com.saipaisi.project.basic.service.BasicShareholderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 股东组成Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicShareholderServiceImpl implements BasicShareholderService
{
    @Autowired
    private BasicShareholderMapper basicShareholderMapper;

    @Autowired
    private BasicInformationMapper basicInformationMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询股东组成
     * 
     * @param id 股东组成ID
     * @return 股东组成
     */
    @Override
    public BasicShareholder selectBasicShareholderById(Long id)
    {
        return basicShareholderMapper.selectBasicShareholderById(id);
    }

    /**
     * 查询股东组成列表
     * 
     * @param basicShareholder 股东组成
     * @return 股东组成
     */
    @Override
    public List<BasicShareholder> selectBasicShareholderList(BasicShareholder basicShareholder)
    {
        return basicShareholderMapper.selectBasicShareholderList(basicShareholder);
    }

    /**
     * 新增股东组成
     * 
     * @param basicShareholder 股东组成
     * @return 结果
     */
    @Override
    public int insertBasicShareholder(BasicShareholder basicShareholder)
    {
        basicShareholder.setCreateTime(DateUtils.getNowDate());
        return basicShareholderMapper.insertBasicShareholder(basicShareholder);
    }

    /**
     * 修改股东组成
     * 
     * @param basicShareholder 股东组成
     * @return 结果
     */
    @Override
    public int updateBasicShareholder(BasicShareholder basicShareholder)
    {
        basicShareholder.setUpdateTime(DateUtils.getNowDate());
        return basicShareholderMapper.updateBasicShareholder(basicShareholder);
    }

    /**
     * 批量删除股东组成
     * 
     * @param ids 需要删除的股东组成ID
     * @return 结果
     */
    @Override
    public int deleteBasicShareholderByIds(Long[] ids)
    {
        return basicShareholderMapper.deleteBasicShareholderByIds(ids);
    }

    /**
     * 删除股东组成信息
     * 
     * @param id 股东组成ID
     * @return 结果
     */
    @Override
    public int deleteBasicShareholderById(Long id)
    {
        return basicShareholderMapper.deleteBasicShareholderById(id);
    }

    @Override
    @Transactional
    public int getPartnersInfo(String kerword,String ordersn) {
        SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword(kerword);
        Map<String,Object> map=qxbHttps.getPartners(searchApiEntity);
        BasicInformation basicInformation =basicInformationMapper.selectBasicInformationByordersn(ordersn);
        BigDecimal regCapiDesc=basicInformation.getRegCapiDesc();
        if ("200".equals(map.get("status"))){
            Map<String,Object> reslut=( Map<String, Object>) map.get("data");
            List<Map<String,Object>> lists= (List<Map<String, Object>>) reslut.get("items");
            List<BasicShareholder> shareholders=new ArrayList<>();
            lists.stream().forEach(e->{
                BasicShareholder basicShareholder=new BasicShareholder();
                BigDecimal shouldCapi=new BigDecimal(StringUtils.digitExtract((String) e.get("totalShouldCapi")));
                BigDecimal realCapil=new BigDecimal(StringUtils.digitExtract((String) e.get("totalRealCapi")));
                basicShareholder.setName((String) e.get("name"));
                basicShareholder.setOrderSn(ordersn);
                basicShareholder.setShouldCapi(shouldCapi);
                basicShareholder.setRealCapil(realCapil);
                basicShareholder.setRealScale(realCapil.divide(regCapiDesc, 2,BigDecimal.ROUND_HALF_UP));
                basicShareholder.setShouldScale(shouldCapi.divide(regCapiDesc, 2,BigDecimal.ROUND_HALF_UP));
                shareholders.add(basicShareholder);
            });
            shareholders.stream().forEach(e->{
                basicShareholderMapper.insertBasicShareholder(e);
            });
        }else {
            throw new CustomException((String) map.get("message"));
        }
        return 0;
    }
}
