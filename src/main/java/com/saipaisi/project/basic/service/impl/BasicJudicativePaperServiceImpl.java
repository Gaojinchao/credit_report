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
import com.saipaisi.project.basic.mapper.BasicJudicativePaperMapper;
import com.saipaisi.project.basic.domain.BasicJudicativePaper;
import com.saipaisi.project.basic.service.BasicJudicativePaperService;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * 裁判文书Service业务层处理
 *
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicJudicativePaperServiceImpl implements BasicJudicativePaperService {
    @Autowired
    private BasicJudicativePaperMapper basicJudicativePaperMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询裁判文书
     *
     * @param id 裁判文书ID
     * @return 裁判文书
     */
    @Override
    public BasicJudicativePaper selectBasicJudicativePaperById(Long id) {
        return basicJudicativePaperMapper.selectBasicJudicativePaperById(id);
    }

    /**
     * 查询裁判文书列表
     *
     * @param basicJudicativePaper 裁判文书
     * @return 裁判文书
     */
    @Override
    public List<BasicJudicativePaper> selectBasicJudicativePaperList(BasicJudicativePaper basicJudicativePaper) {
        return basicJudicativePaperMapper.selectBasicJudicativePaperList(basicJudicativePaper);
    }

    /**
     * 新增裁判文书
     *
     * @param basicJudicativePaper 裁判文书
     * @return 结果
     */
    @Override
    public int insertBasicJudicativePaper(BasicJudicativePaper basicJudicativePaper) {
        basicJudicativePaper.setCreateTime(DateUtils.getNowDate());
        return basicJudicativePaperMapper.insertBasicJudicativePaper(basicJudicativePaper);
    }

    /**
     * 修改裁判文书
     *
     * @param basicJudicativePaper 裁判文书
     * @return 结果
     */
    @Override
    public int updateBasicJudicativePaper(BasicJudicativePaper basicJudicativePaper) {
        basicJudicativePaper.setUpdateTime(DateUtils.getNowDate());
        return basicJudicativePaperMapper.updateBasicJudicativePaper(basicJudicativePaper);
    }

    /**
     * 批量删除裁判文书
     *
     * @param ids 需要删除的裁判文书ID
     * @return 结果
     */
    @Override
    public int deleteBasicJudicativePaperByIds(Long[] ids) {
        return basicJudicativePaperMapper.deleteBasicJudicativePaperByIds(ids);
    }

    /**
     * 删除裁判文书信息
     *
     * @param id 裁判文书ID
     * @return 结果
     */
    @Override
    public int deleteBasicJudicativePaperById(Long id) {
        return basicJudicativePaperMapper.deleteBasicJudicativePaperById(id);
    }

    @Override
    public int getLawsuitListByName(String kerword, String ordersn) {
        /*SearchApiEntity searchApiEntity = new SearchApiEntity();
        searchApiEntity.setKeyword(kerword);*/

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("keyword",kerword);
        stringMap.put("appkey", Constant.APPKEY);
        stringMap.put("secret_key",Constant.SECRET_KEY);

        Map<String, Object> map = qxbHttps.getLawsuitListByName(stringMap);
        if ("200".equals(map.get("status"))) {
            Map<String, Object> reslut = (Map<String, Object>) map.get("data");
            List<Map<String, Object>> lists = (List<Map<String, Object>>) reslut.get("items");
            List<BasicJudicativePaper> list = new ArrayList<>();
            lists.stream().forEach(e->{
                BasicJudicativePaper basicJudicativePaper=new BasicJudicativePaper();
                basicJudicativePaper.setJudgmentDate((String) e.get("pub_date"));
                basicJudicativePaper.setOrderSn(ordersn);
                basicJudicativePaper.setCaseCode((String) e.get("case_no"));
                basicJudicativePaper.setTitle((String) e.get("title"));
                basicJudicativePaper.setCourt("--");
                basicJudicativePaper.setCause((String) e.get("case_cause"));
                list.add(basicJudicativePaper);
            });
           list.stream().forEach(e->{
               basicJudicativePaperMapper.insertBasicJudicativePaper(e);
           });
        } else {
            //throw new CustomException((String) map.get("message"));
            return 0;
        }

        return 1;
    }
}
