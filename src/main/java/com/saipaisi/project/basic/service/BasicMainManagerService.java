package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicMainManager;

/**
 * 主要管理者信息Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicMainManagerService
{
    /**
     * 查询主要管理者信息
     * 
     * @param id 主要管理者信息ID
     * @return 主要管理者信息
     */
    public BasicMainManager selectBasicMainManagerById(Long id);

    /**
     * 查询主要管理者信息
     *
     * @param Ordersn 主要管理者信息Ordersn
     * @return 主要管理者信息
     */
    public List<BasicMainManager> selectBasicMainManagerByOrdersn(String Ordersn);


    /**
     * 查询主要管理者信息列表
     * 
     * @param basicMainManager 主要管理者信息
     * @return 主要管理者信息集合
     */
    public List<BasicMainManager> selectBasicMainManagerList(BasicMainManager basicMainManager);

    /**
     * 新增主要管理者信息
     * 
     * @param basicMainManager 主要管理者信息
     * @return 结果
     */
    public int insertBasicMainManager(BasicMainManager basicMainManager);

    /**
     * 修改主要管理者信息
     * 
     * @param basicMainManager 主要管理者信息
     * @return 结果
     */
    public int updateBasicMainManager(BasicMainManager basicMainManager);

    /**
     * 批量删除主要管理者信息
     * 
     * @param ids 需要删除的主要管理者信息ID
     * @return 结果
     */
    public int deleteBasicMainManagerByIds(Long[] ids);

    /**
     * 删除主要管理者信息信息
     * 
     * @param id 主要管理者信息ID
     * @return 结果
     */
    public int deleteBasicMainManagerById(Long id);

    /**
     * 根据订单编号删除主要经营者
     * @return
     */
    public int deleteBasicMainManageByOrdersn(String ordersn);
}
