package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicMainRunManager;

/**
 * 主要经营者管理者信息Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicMainRunManagerMapper 
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
     * @param ordersn 主要经营者管理者信息ID
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
     * 删除主要经营者管理者信息
     * 
     * @param id 主要经营者管理者信息ID
     * @return 结果
     */
    public int deleteBasicMainRunManagerById(Long id);

    /**
     * 批量删除主要经营者管理者信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicMainRunManagerByIds(Long[] ids);

    public int deleteBasicMainRunManagerByOrdersn(String ordersn);
}
