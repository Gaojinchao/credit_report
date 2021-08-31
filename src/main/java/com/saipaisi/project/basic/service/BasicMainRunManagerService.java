package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicMainRunManager;

/**
 * 主要经营者管理者信息Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicMainRunManagerService
{
    /**
     * 查询主要经营者管理者信息
     * 
     * @param id 主要经营者管理者信息ID
     * @return 主要经营者管理者信息
     */
    public BasicMainRunManager selectBasicMainRunManagerById(Long id);

    /**
     * 查询主要经营者管理者信息
     *
     * @param ordersn 主要经营者管理者信息ordersn
     * @return 主要经营者管理者信息
     */
    public List<BasicMainRunManager> selectBasicMainRunManagerByOrdersn(String ordersn);

    /**
     * 查询主要经营者管理者信息列表
     * 
     * @param basicMainRunManager 主要经营者管理者信息
     * @return 主要经营者管理者信息集合
     */
    public List<BasicMainRunManager> selectBasicMainRunManagerList(BasicMainRunManager basicMainRunManager);

    /**
     * 新增主要经营者管理者信息
     * 
     * @param basicMainRunManager 主要经营者管理者信息
     * @return 结果
     */
    public int insertBasicMainRunManager(BasicMainRunManager basicMainRunManager);

    /**
     * 修改主要经营者管理者信息
     * 
     * @param basicMainRunManager 主要经营者管理者信息
     * @return 结果
     */
    public int updateBasicMainRunManager(BasicMainRunManager basicMainRunManager);

    /**
     * 批量删除主要经营者管理者信息
     * 
     * @param ids 需要删除的主要经营者管理者信息ID
     * @return 结果
     */
    public int deleteBasicMainRunManagerByIds(Long[] ids);

    /**
     * 删除主要经营者管理者信息信息
     * 
     * @param id 主要经营者管理者信息ID
     * @return 结果
     */
    public int deleteBasicMainRunManagerById(Long id);

    /**
     * 根据订单编号删除
     * @return
     */
    public int deleteBasicMainRunManagerByOrdersn(String ordersn);
}
