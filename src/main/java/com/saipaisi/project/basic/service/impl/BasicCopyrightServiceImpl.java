package com.saipaisi.project.basic.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.project.basic.domain.BasicInformation;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicCopyrightMapper;
import com.saipaisi.project.basic.domain.BasicCopyright;
import com.saipaisi.project.basic.service.BasicCopyrightService;

/**
 * 著作权Service业务层处理
 * 
 * @author alfredzong
 * @date 2021-05-26
 */
@Service
public class BasicCopyrightServiceImpl implements BasicCopyrightService
{
    @Autowired
    private BasicCopyrightMapper basicCopyrightMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询著作权
     * 
     * @param id 著作权ID
     * @return 著作权
     */
    @Override
    public BasicCopyright selectBasicCopyrightById(Long id)
    {
        return basicCopyrightMapper.selectBasicCopyrightById(id);
    }

    /**
     * 查询著作权列表
     * 
     * @param basicCopyright 著作权
     * @return 著作权
     */
    @Override
    public List<BasicCopyright> selectBasicCopyrightList(BasicCopyright basicCopyright)
    {
        return basicCopyrightMapper.selectBasicCopyrightList(basicCopyright);
    }

    /**
     * 新增著作权
     * 
     * @param basicCopyright 著作权
     * @return 结果
     */
    @Override
    public int insertBasicCopyright(BasicCopyright basicCopyright)
    {
        basicCopyright.setCreateTime(DateUtils.getNowDate());
        return basicCopyrightMapper.insertBasicCopyright(basicCopyright);
    }

    /**
     * 修改著作权
     * 
     * @param basicCopyright 著作权
     * @return 结果
     */
    @Override
    public int updateBasicCopyright(BasicCopyright basicCopyright)
    {
        basicCopyright.setUpdateTime(DateUtils.getNowDate());
        return basicCopyrightMapper.updateBasicCopyright(basicCopyright);
    }

    /**
     * 批量删除著作权
     * 
     * @param ids 需要删除的著作权ID
     * @return 结果
     */
    @Override
    public int deleteBasicCopyrightByIds(Long[] ids)
    {
        return basicCopyrightMapper.deleteBasicCopyrightByIds(ids);
    }

    /**
     * 删除著作权信息
     * 
     * @param id 著作权ID
     * @return 结果
     */
    @Override
    public int deleteBasicCopyrightById(Long id)
    {
        return basicCopyrightMapper.deleteBasicCopyrightById(id);
    }

    @Override
    public int getCopyrightByName(String keyword, String ordersn) {
        SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setName(keyword);
        Map<String,Object> map=qxbHttps.getCopyrightByName(searchApiEntity);
        if ("200".equals(map.get("status"))) {
            Map<String, Object> reslut = (Map<String, Object>) map.get("data");
            List<Map<String, Object>> lists = (List<Map<String, Object>>) reslut.get("items");
            List<BasicCopyright> list=new ArrayList<>();
            lists.stream().forEach(e->{
                BasicCopyright basicCopyright=new BasicCopyright();
                basicCopyright.setApprovalDate((String) e.get("approval_date"));
                basicCopyright.setCompany((String) e.get("company"));
                basicCopyright.setFirstDate((String) e.get("first_date"));
                basicCopyright.setOrderSn(ordersn);
                basicCopyright.setNumber((String) e.get("number"));
                basicCopyright.setName((String) e.get("name"));
                list.add(basicCopyright);
            });
            list.forEach(e->{
                basicCopyrightMapper.insertBasicCopyright(e);
            });
        }else {
            throw new CustomException((String) map.get("message"));
        }
        return 1;
    }
}
