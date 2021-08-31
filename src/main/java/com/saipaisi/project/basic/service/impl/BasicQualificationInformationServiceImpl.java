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
import com.saipaisi.project.basic.mapper.BasicQualificationInformationMapper;
import com.saipaisi.project.basic.domain.BasicQualificationInformation;
import com.saipaisi.project.basic.service.BasicQualificationInformationService;

/**
 * 资质信息Service业务层处理
 *
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicQualificationInformationServiceImpl implements BasicQualificationInformationService {
    @Autowired
    private BasicQualificationInformationMapper basicQualificationInformationMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询资质信息
     *
     * @param id 资质信息ID
     * @return 资质信息
     */
    @Override
    public BasicQualificationInformation selectBasicQualificationInformationById(Long id) {
        return basicQualificationInformationMapper.selectBasicQualificationInformationById(id);
    }

    /**
     * 查询资质信息列表
     *
     * @param basicQualificationInformation 资质信息
     * @return 资质信息
     */
    @Override
    public List<BasicQualificationInformation> selectBasicQualificationInformationList(BasicQualificationInformation basicQualificationInformation) {
        return basicQualificationInformationMapper.selectBasicQualificationInformationList(basicQualificationInformation);
    }

    /**
     * 新增资质信息
     *
     * @param basicQualificationInformation 资质信息
     * @return 结果
     */
    @Override
    public int insertBasicQualificationInformation(BasicQualificationInformation basicQualificationInformation) {
        basicQualificationInformation.setCreateTime(DateUtils.getNowDate());
        return basicQualificationInformationMapper.insertBasicQualificationInformation(basicQualificationInformation);
    }

    /**
     * 修改资质信息
     *
     * @param basicQualificationInformation 资质信息
     * @return 结果
     */
    @Override
    public int updateBasicQualificationInformation(BasicQualificationInformation basicQualificationInformation) {
        basicQualificationInformation.setUpdateTime(DateUtils.getNowDate());
        return basicQualificationInformationMapper.updateBasicQualificationInformation(basicQualificationInformation);
    }

    /**
     * 批量删除资质信息
     *
     * @param ids 需要删除的资质信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicQualificationInformationByIds(Long[] ids) {
        return basicQualificationInformationMapper.deleteBasicQualificationInformationByIds(ids);
    }

    /**
     * 删除资质信息信息
     *
     * @param id 资质信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicQualificationInformationById(Long id) {
        return basicQualificationInformationMapper.deleteBasicQualificationInformationById(id);
    }

    @Override
    public int getCertificateByNameInfo(String keyword, String ordersn) {
        /*SearchApiEntity searchApiEntity = new SearchApiEntity();
        searchApiEntity.setName(keyword);*/

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("name",keyword);
        stringMap.put("appkey", Constant.APPKEY);
        stringMap.put("secret_key",Constant.SECRET_KEY);
        Map<String, Object> map = qxbHttps.getCertificateByName(stringMap);
        if ("200".equals(map.get("status"))) {
            Map<String, Object> reslut = (Map<String, Object>) map.get("data");
            List<Map<String, Object>> lists = (List<Map<String, Object>>) reslut.get("items");
            List<BasicQualificationInformation> list=new ArrayList<>();
            lists.stream().forEach(e -> {
                BasicQualificationInformation basicQualificationInformation=new BasicQualificationInformation();
                basicQualificationInformation.setGrade("--");
                basicQualificationInformation.setLicenceissuingAuth("--");
                basicQualificationInformation.setName((String) e.get("type"));
                basicQualificationInformation.setPeriodValidity((String) e.get("validity_end"));
                basicQualificationInformation.setSerialNumber((String) e.get("num"));
                basicQualificationInformation.setOrderSn(ordersn);
                list.add(basicQualificationInformation);
            });
            list.stream().forEach(e->{
                basicQualificationInformationMapper.insertBasicQualificationInformation(e);
            });
        } else {
            //throw new CustomException((String) map.get("message"));
            return 0;
        }
        return 1;
    }
}
