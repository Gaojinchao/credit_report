package com.saipaisi.project.basic.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.project.basic.domain.BasicInformation;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import com.saipaisi.project.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicSoftwareCopyrightMapper;
import com.saipaisi.project.basic.domain.BasicSoftwareCopyright;
import com.saipaisi.project.basic.service.BasicSoftwareCopyrightService;

/**
 * 软件著作权Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicSoftwareCopyrightServiceImpl implements BasicSoftwareCopyrightService
{
    @Autowired
    private BasicSoftwareCopyrightMapper basicSoftwareCopyrightMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询软件著作权
     * 
     * @param id 软件著作权ID
     * @return 软件著作权
     */
    @Override
    public BasicSoftwareCopyright selectBasicSoftwareCopyrightById(Long id)
    {
        return basicSoftwareCopyrightMapper.selectBasicSoftwareCopyrightById(id);
    }

    /**
     * 查询软件著作权列表
     * 
     * @param basicSoftwareCopyright 软件著作权
     * @return 软件著作权
     */
    @Override
    public List<BasicSoftwareCopyright> selectBasicSoftwareCopyrightList(BasicSoftwareCopyright basicSoftwareCopyright)
    {
        return basicSoftwareCopyrightMapper.selectBasicSoftwareCopyrightList(basicSoftwareCopyright);
    }

    /**
     * 新增软件著作权
     * 
     * @param basicSoftwareCopyright 软件著作权
     * @return 结果
     */
    @Override
    public int insertBasicSoftwareCopyright(BasicSoftwareCopyright basicSoftwareCopyright)
    {
        basicSoftwareCopyright.setCreateTime(DateUtils.getNowDate());
        return basicSoftwareCopyrightMapper.insertBasicSoftwareCopyright(basicSoftwareCopyright);
    }

    /**
     * 修改软件著作权
     * 
     * @param basicSoftwareCopyright 软件著作权
     * @return 结果
     */
    @Override
    public int updateBasicSoftwareCopyright(BasicSoftwareCopyright basicSoftwareCopyright)
    {
        basicSoftwareCopyright.setUpdateTime(DateUtils.getNowDate());
        return basicSoftwareCopyrightMapper.updateBasicSoftwareCopyright(basicSoftwareCopyright);
    }

    /**
     * 批量删除软件著作权
     * 
     * @param ids 需要删除的软件著作权ID
     * @return 结果
     */
    @Override
    public int deleteBasicSoftwareCopyrightByIds(Long[] ids)
    {
        return basicSoftwareCopyrightMapper.deleteBasicSoftwareCopyrightByIds(ids);
    }

    /**
     * 删除软件著作权信息
     * 
     * @param id 软件著作权ID
     * @return 结果
     */
    @Override
    public int deleteBasicSoftwareCopyrightById(Long id)
    {
        return basicSoftwareCopyrightMapper.deleteBasicSoftwareCopyrightById(id);
    }

    @Override
    public int getCopyrightSoftByNameInfo(String keyword, String ordersn) {
        /*SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword(keyword);*/

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("keyword",keyword);
        stringMap.put("appkey", Constant.APPKEY);
        stringMap.put("secret_key",Constant.SECRET_KEY);
        Map<String,Object> map=qxbHttps.getCopyrightSoftByName(stringMap);
        if ("200".equals(map.get("status"))) {
            Map<String, Object> reslut = (Map<String, Object>) map.get("data");
            List<Map<String, Object>> lists = (List<Map<String, Object>>) reslut.get("items");
            List<BasicSoftwareCopyright> list=new ArrayList();
            lists.stream().forEach(e->{
                BasicSoftwareCopyright basicSoftwareCopyright=new BasicSoftwareCopyright();
                basicSoftwareCopyright.setClasslyCode((String) e.get("type_num"));
                basicSoftwareCopyright.setCopyrightName((String) e.get("company"));
                basicSoftwareCopyright.setOrderSn(ordersn);
                basicSoftwareCopyright.setRecordDate((String) e.get("first_date"));
                basicSoftwareCopyright.setSoftwareName((String) e.get("name"));
                basicSoftwareCopyright.setRegisterNum((String) e.get("number"));
                list.add(basicSoftwareCopyright);
            });
            list.stream().forEach(e->{
                basicSoftwareCopyrightMapper.insertBasicSoftwareCopyright(e);
            });
        }else {
            //throw new CustomException((String) map.get("message"));
            return 0;
        }
        return 1;
    }
}
