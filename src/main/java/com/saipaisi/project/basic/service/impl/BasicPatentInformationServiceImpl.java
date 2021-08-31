package com.saipaisi.project.basic.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import com.saipaisi.project.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicPatentInformationMapper;
import com.saipaisi.project.basic.domain.BasicPatentInformation;
import com.saipaisi.project.basic.service.BasicPatentInformationService;

/**
 * 专利信息Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicPatentInformationServiceImpl implements BasicPatentInformationService
{
    @Autowired
    private BasicPatentInformationMapper basicPatentInformationMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询专利信息
     * 
     * @param id 专利信息ID
     * @return 专利信息
     */
    @Override
    public BasicPatentInformation selectBasicPatentInformationById(Long id)
    {
        return basicPatentInformationMapper.selectBasicPatentInformationById(id);
    }

    /**
     * 查询专利信息列表
     * 
     * @param basicPatentInformation 专利信息
     * @return 专利信息
     */
    @Override
    public List<BasicPatentInformation> selectBasicPatentInformationList(BasicPatentInformation basicPatentInformation)
    {
        return basicPatentInformationMapper.selectBasicPatentInformationList(basicPatentInformation);
    }

    /**
     * 新增专利信息
     * 
     * @param basicPatentInformation 专利信息
     * @return 结果
     */
    @Override
    public int insertBasicPatentInformation(BasicPatentInformation basicPatentInformation)
    {
        basicPatentInformation.setCreateTime(DateUtils.getNowDate());
        return basicPatentInformationMapper.insertBasicPatentInformation(basicPatentInformation);
    }

    /**
     * 修改专利信息
     * 
     * @param basicPatentInformation 专利信息
     * @return 结果
     */
    @Override
    public int updateBasicPatentInformation(BasicPatentInformation basicPatentInformation)
    {
        basicPatentInformation.setUpdateTime(DateUtils.getNowDate());
        return basicPatentInformationMapper.updateBasicPatentInformation(basicPatentInformation);
    }

    /**
     * 批量删除专利信息
     * 
     * @param ids 需要删除的专利信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicPatentInformationByIds(Long[] ids)
    {
        return basicPatentInformationMapper.deleteBasicPatentInformationByIds(ids);
    }

    /**
     * 删除专利信息信息
     * 
     * @param id 专利信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicPatentInformationById(Long id)
    {
        return basicPatentInformationMapper.deleteBasicPatentInformationById(id);
    }

    @Override
    public int getPatentListByNameInfo(String keyword, String ordersn) {
        /*SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword(keyword);*/

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("keyword",keyword);
        stringMap.put("appkey", Constant.APPKEY);
        stringMap.put("secret_key",Constant.SECRET_KEY);
        Map<String,Object> map=qxbHttps.getPatentListByName(stringMap);
        if ("200".equals(map.get("status"))) {
            Map<String, Object> reslut = (Map<String, Object>) map.get("data");
            List<Map<String, Object>> lists = (List<Map<String, Object>>) reslut.get("items");
            List<BasicPatentInformation> list=new ArrayList<>();
            lists.stream().forEach(e->{
                BasicPatentInformation basicPatentInformation=new BasicPatentInformation();
                basicPatentInformation.setName((String) e.get("patent_name"));
                basicPatentInformation.setLicenceissuingAuth("--");
                basicPatentInformation.setOrderSn(ordersn);
                basicPatentInformation.setPubCode((String) e.get("outhor_num"));
                list.add(basicPatentInformation);
            });
            list.stream().forEach(e->{
                basicPatentInformationMapper.insertBasicPatentInformation(e);
            });
        }else {
            //throw new CustomException((String) map.get("message"));
            return 0;
        }
        return 1;
    }
}
