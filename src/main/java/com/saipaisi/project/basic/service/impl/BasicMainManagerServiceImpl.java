package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicMainManagerMapper;
import com.saipaisi.project.basic.domain.BasicMainManager;
import com.saipaisi.project.basic.service.BasicMainManagerService;

/**
 * 主要管理者信息Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicMainManagerServiceImpl implements BasicMainManagerService
{
    @Autowired
    private BasicMainManagerMapper basicMainManagerMapper;

    /**
     * 查询主要管理者信息
     * 
     * @param id 主要管理者信息ID
     * @return 主要管理者信息
     */
    @Override
    public BasicMainManager selectBasicMainManagerById(Long id)
    {
        return basicMainManagerMapper.selectBasicMainManagerById(id);
    }

    @Override
    public List<BasicMainManager> selectBasicMainManagerByOrdersn(String Ordersn) {
        return basicMainManagerMapper.selectBasicMainManagerByOrdersn(Ordersn);
    }

    /**
     * 查询主要管理者信息列表
     * 
     * @param basicMainManager 主要管理者信息
     * @return 主要管理者信息
     */
    @Override
    public List<BasicMainManager> selectBasicMainManagerList(BasicMainManager basicMainManager)
    {
        return basicMainManagerMapper.selectBasicMainManagerList(basicMainManager);
    }

    /**
     * 新增主要管理者信息
     * 
     * @param basicMainManager 主要管理者信息
     * @return 结果
     */
    @Override
    public int insertBasicMainManager(BasicMainManager basicMainManager)
    {
        basicMainManager.setCreateTime(DateUtils.getNowDate());
        return basicMainManagerMapper.insertBasicMainManager(basicMainManager);
    }

    /**
     * 修改主要管理者信息
     * 
     * @param basicMainManager 主要管理者信息
     * @return 结果
     */
    @Override
    public int updateBasicMainManager(BasicMainManager basicMainManager)
    {
        basicMainManager.setUpdateTime(DateUtils.getNowDate());
        return basicMainManagerMapper.updateBasicMainManager(basicMainManager);
    }

    /**
     * 批量删除主要管理者信息
     * 
     * @param ids 需要删除的主要管理者信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicMainManagerByIds(Long[] ids)
    {
        return basicMainManagerMapper.deleteBasicMainManagerByIds(ids);
    }

    /**
     * 删除主要管理者信息信息
     * 
     * @param id 主要管理者信息ID
     * @return 结果
     */
    @Override
    public int deleteBasicMainManagerById(Long id)
    {
        return basicMainManagerMapper.deleteBasicMainManagerById(id);
    }

    @Override
    public int deleteBasicMainManageByOrdersn(String ordersn) {
        return basicMainManagerMapper.deleteBasicMainManageByOrdersn(ordersn);
    }
}
