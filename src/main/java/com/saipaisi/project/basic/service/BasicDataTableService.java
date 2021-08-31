package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicDataTable;

/**
 * 数据Service接口
 * 
 * @author saaa
 * @date 2021-05-20
 */
public interface BasicDataTableService
{
    /**
     * 查询数据
     * 
     * @param id 数据ID
     * @return 数据
     */
    public BasicDataTable selectBasicDataTableById(Long id);

    /**
     * 查询数据列表
     * 
     * @param basicDataTable 数据
     * @return 数据集合
     */
    public List<BasicDataTable> selectBasicDataTableList(BasicDataTable basicDataTable);

    /**
     * 新增数据
     * 
     * @param basicDataTable 数据
     * @return 结果
     */
    public int insertBasicDataTable(BasicDataTable basicDataTable);

    /**
     * 修改数据
     * 
     * @param basicDataTable 数据
     * @return 结果
     */
    public int updateBasicDataTable(BasicDataTable basicDataTable);

    /**
     * 批量删除数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicDataTableByIds(Long[] ids);

    /**
     * 删除数据信息
     * 
     * @param id 数据ID
     * @return 结果
     */
    public int deleteBasicDataTableById(Long id);
}
