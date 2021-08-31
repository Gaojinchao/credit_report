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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicEmbranchmentOrgMapper;
import com.saipaisi.project.basic.domain.BasicEmbranchmentOrg;
import com.saipaisi.project.basic.service.BasicEmbranchmentOrgService;

/**
 * 对外投资Service业务层处理
 *
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicEmbranchmentOrgServiceImpl implements BasicEmbranchmentOrgService {
    @Autowired
    private BasicEmbranchmentOrgMapper basicEmbranchmentOrgMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询对外投资
     *
     * @param id 分支机构ID
     * @return 分支机构
     */
    @Override
    public BasicEmbranchmentOrg selectBasicEmbranchmentOrgById(Long id) {
        return basicEmbranchmentOrgMapper.selectBasicEmbranchmentOrgById(id);
    }

    /**
     * 查询对外投资列表
     *
     * @param basicEmbranchmentOrg 对外投资
     * @return 对外投资
     */
    @Override
    public List<BasicEmbranchmentOrg> selectBasicEmbranchmentOrgList(BasicEmbranchmentOrg basicEmbranchmentOrg) {
        return basicEmbranchmentOrgMapper.selectBasicEmbranchmentOrgList(basicEmbranchmentOrg);
    }

    /**
     * 新增对外投资
     *
     * @param basicEmbranchmentOrg 对外投资
     * @return 结果
     */
    @Override
    public int insertBasicEmbranchmentOrg(BasicEmbranchmentOrg basicEmbranchmentOrg) {
        basicEmbranchmentOrg.setCreateTime(DateUtils.getNowDate());
        return basicEmbranchmentOrgMapper.insertBasicEmbranchmentOrg(basicEmbranchmentOrg);
    }

    /**
     * 修改对外投资
     *
     * @param basicEmbranchmentOrg 对外投资
     * @return 结果
     */
    @Override
    public int updateBasicEmbranchmentOrg(BasicEmbranchmentOrg basicEmbranchmentOrg) {
        basicEmbranchmentOrg.setUpdateTime(DateUtils.getNowDate());
        return basicEmbranchmentOrgMapper.updateBasicEmbranchmentOrg(basicEmbranchmentOrg);
    }

    /**
     * 批量删除分支机构
     *
     * @param ids 需要删除的分支机构ID
     * @return 结果
     */
    @Override
    public int deleteBasicEmbranchmentOrgByIds(Long[] ids) {
        return basicEmbranchmentOrgMapper.deleteBasicEmbranchmentOrgByIds(ids);
    }

    /**
     * 删除分支机构信息
     *
     * @param id 分支机构ID
     * @return 结果
     */
    @Override
    public int deleteBasicEmbranchmentOrgById(Long id) {
        return basicEmbranchmentOrgMapper.deleteBasicEmbranchmentOrgById(id);
    }

    @Override
    public int getInvestmentByNameInfo(String keyword, String ordersn) {
        //SearchApiEntity searchApiEntity = new SearchApiEntity();
        /*searchApiEntity.setAppkey(Constant.APPKEY);
        searchApiEntity.setSecret_key(Constant.SECRET_KEY);
        searchApiEntity.setKeyword(keyword);*/
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("keyword",keyword);
        stringMap.put("appkey",Constant.APPKEY);
        stringMap.put("secret_key",Constant.SECRET_KEY);

        Map<String, Object> map = qxbHttps.getInvestmentByName(stringMap);
        if ("200".equals(map.get("status"))) {
            List<Map<String, Object>> lists = (List<Map<String, Object>>) map.get("data");
            List<BasicEmbranchmentOrg> basicEmbranchmentOrgs = new ArrayList<>();
            lists.stream().forEach(e -> {
                BasicEmbranchmentOrg basicEmbranchmentOrg = new BasicEmbranchmentOrg();
                basicEmbranchmentOrg.setOrderSn(ordersn);
                basicEmbranchmentOrg.setRealCapil((BigDecimal) e.get("amount"));
                basicEmbranchmentOrg.setRealCapilScale((String) e.get("percent"));
                basicEmbranchmentOrg.setOrgName((String) e.get("enterprise"));
                basicEmbranchmentOrgs.add(basicEmbranchmentOrg);
            });
            basicEmbranchmentOrgs.stream().forEach(e -> {
                basicEmbranchmentOrgMapper.insertBasicEmbranchmentOrg(e);
            });
            return 1;
        } else {
            //throw new CustomException((String) map.get("message"));
            return 0;
        }
    }
}
