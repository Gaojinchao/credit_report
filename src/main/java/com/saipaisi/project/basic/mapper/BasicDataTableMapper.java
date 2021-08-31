package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicDataTable;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据Mapper接口
 * 
 * @author saaa
 * @date 2021-05-20
 */
public interface BasicDataTableMapper 
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
     * 删除数据
     * 
     * @param id 数据ID
     * @return 结果
     */
    public int deleteBasicDataTableById(Long id);

    /**
     * 批量删除数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicDataTableByIds(Long[] ids);

    /**
     * 删除数据根据订单号
     * @param ordersn
     * @return
     */
    public int deleteBasicDataTableByOrdersn(String ordersn);
}
