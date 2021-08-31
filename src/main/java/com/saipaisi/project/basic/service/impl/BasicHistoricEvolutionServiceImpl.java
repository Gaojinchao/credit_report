package com.saipaisi.project.basic.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicHistoricEvolutionMapper;
import com.saipaisi.project.basic.domain.BasicHistoricEvolution;
import com.saipaisi.project.basic.service.BasicHistoricEvolutionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 历史沿革Service业务层处理
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
@Service
public class BasicHistoricEvolutionServiceImpl implements BasicHistoricEvolutionService
{
    @Autowired
    private BasicHistoricEvolutionMapper basicHistoricEvolutionMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询历史沿革
     * 
     * @param id 历史沿革ID
     * @return 历史沿革
     */
    @Override
    public BasicHistoricEvolution selectBasicHistoricEvolutionById(Long id)
    {
        return basicHistoricEvolutionMapper.selectBasicHistoricEvolutionById(id);
    }

    /**
     * 查询历史沿革列表
     * 
     * @param basicHistoricEvolution 历史沿革
     * @return 历史沿革
     */
    @Override
    public List<BasicHistoricEvolution> selectBasicHistoricEvolutionList(BasicHistoricEvolution basicHistoricEvolution)
    {
        return basicHistoricEvolutionMapper.selectBasicHistoricEvolutionList(basicHistoricEvolution);
    }

    /**
     * 新增历史沿革
     * 
     * @param basicHistoricEvolution 历史沿革
     * @return 结果
     */
    @Override
    public int insertBasicHistoricEvolution(BasicHistoricEvolution basicHistoricEvolution)
    {
        basicHistoricEvolution.setCreateTime(DateUtils.getNowDate());
        return basicHistoricEvolutionMapper.insertBasicHistoricEvolution(basicHistoricEvolution);
    }

    /**
     * 修改历史沿革
     * 
     * @param basicHistoricEvolution 历史沿革
     * @return 结果
     */
    @Override
    public int updateBasicHistoricEvolution(BasicHistoricEvolution basicHistoricEvolution)
    {
        basicHistoricEvolution.setUpdateTime(DateUtils.getNowDate());
        return basicHistoricEvolutionMapper.updateBasicHistoricEvolution(basicHistoricEvolution);
    }

    /**
     * 批量删除历史沿革
     * 
     * @param ids 需要删除的历史沿革ID
     * @return 结果
     */
    @Override
    public int deleteBasicHistoricEvolutionByIds(Long[] ids)
    {
        return basicHistoricEvolutionMapper.deleteBasicHistoricEvolutionByIds(ids);
    }

    /**
     * 删除历史沿革信息
     * 
     * @param id 历史沿革ID
     * @return 结果
     */
    @Override
    public int deleteBasicHistoricEvolutionById(Long id)
    {
        return basicHistoricEvolutionMapper.deleteBasicHistoricEvolutionById(id);
    }

    @Override
    @Transactional
    public int getChangeRecordsInfo(String keyword,String ordersn) {
        SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword(keyword);
        Map<String,Object> map=qxbHttps.getChangeRecords(searchApiEntity);
        if ("200".equals(map.get("status"))){
            Map<String,Object> reslut=( Map<String, Object>) map.get("data");
            List<Map<String,Object>> lists= (List<Map<String, Object>>) reslut.get("items");
            List<BasicHistoricEvolution> list=new ArrayList<>();
            lists.stream().forEach(e->{
                BasicHistoricEvolution basicHistoricEvolution=new BasicHistoricEvolution();
                basicHistoricEvolution.setAfterContent((String) e.get("afterContent"));
                basicHistoricEvolution.setChangeDate((Date) e.get("changeDate"));
                basicHistoricEvolution.setOrderSn(ordersn);
                list.add(basicHistoricEvolution);
            });
             list.stream().forEach(e->{
                 basicHistoricEvolutionMapper.insertBasicHistoricEvolution(e);
             });
        }else {
            throw new CustomException((String) map.get("message"));
        }
        return 1;
    }
}
