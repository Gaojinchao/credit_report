package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicMainRunManagerMapper;
import com.saipaisi.project.basic.domain.BasicMainRunManager;
import com.saipaisi.project.basic.service.BasicMainRunManagerService;

/**
 * 主要经营者管理者信息Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicMainRunManagerServiceImpl implements BasicMainRunManagerService
{
    @Autowired
    private BasicMainRunManagerMapper basicMainRunManagerMapper;

    /**
     * 查询主要经营者管理者信息
     * 
     * @param id 主要经营者管理者信息ID
     * @return 主要经营者管理者信息
     */
    @Override
    public BasicMainRunManager selectBasicMainRunManagerById(Long id)
    {
        return basicMainRunManagerMapper.selectBasicMainRunManagerById(id);
    }

    @Override
    public List<BasicMainRunManager> selectBasicMainRunManagerByOrdersn(String ordersn) {
        return basicMainRunManagerMapper.selectBasicMainRunManagerByOrdersn(ordersn);
    }

    /**
     * 查询主要经营者管理者信息列表
     * 
     * @param basicMainRunManager 主要经营者管理者信息
     * @return 主要经营者管理者信息
     */
    @Override
    public List<BasicMainRunManager> selectBasicMainRunManagerList(BasicMainRunManager basicMainRunManager)
    {
        return basicMainRunManagerMapper.selectBasicMainRunManagerList(basicMainRunManager);
    }

    /**
     * 新增主要经营者管理者信息
     * 
     * @param basicMainRunManager 主要经营者管理者信息
     * @return 结果
     */
    @Override
    public int insertBasicMainRunManager(BasicMainRunManager basicMainRunManager)
    {
        basicMainRunManager.setCreateTime(DateUtils.getNowDate());
        return basicMainRunManagerMapper.insertBasicMainRunManager(basicMainRunManager);
    }

    /**
     * 修改主要经营者管理者信息
     * 
     * @param basicMainRunManager 主要经营者管理者信息
     * @return 结果
     */
    @Override
    public int updateBasicMainRunManager(BasicMainRunManager basicMainRunManager)
    {
        basicMainRunManager.setUpdateTime(DateUtils.getNowDate());
        return basicMainRunManagerMapper.updateBasicMainRunManager(basicMainRunManager);
    }

    /**
     * 批量删除主要经营者管理者信息
     * 
     * @param ids 需要删除的主要经营者管理者信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicMainRunManagerByIds(Long[] ids)
    {
        return basicMainRunManagerMapper.deleteBasicMainRunManagerByIds(ids);
    }

    /**
     * 删除主要经营者管理者信息信息
     * 
     * @param id 主要经营者管理者信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicMainRunManagerById(Long id)
    {
        return basicMainRunManagerMapper.deleteBasicMainRunManagerById(id);
    }

    @Override
    public int deleteBasicMainRunManagerByOrdersn(String ordersn) {
        return basicMainRunManagerMapper.deleteBasicMainRunManagerByOrdersn(ordersn);
    }
}
