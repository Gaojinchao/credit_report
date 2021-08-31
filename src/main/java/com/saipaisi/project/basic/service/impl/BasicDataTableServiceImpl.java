package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicDataTableMapper;
import com.saipaisi.project.basic.domain.BasicDataTable;
import com.saipaisi.project.basic.service.BasicDataTableService;

/**
 * 数据Service业务层处理
 * 
 * @author saaa
 * @date 2021-05-20
 */
@Service
public class BasicDataTableServiceImpl implements BasicDataTableService
{
    @Autowired
    private BasicDataTableMapper basicDataTableMapper;

    /**
     * 查询数据
     * 
     * @param id 数据ID
     * @return 数据
     */
    @Override
    public BasicDataTable selectBasicDataTableById(Long id)
    {
        return basicDataTableMapper.selectBasicDataTableById(id);
    }

    /**
     * 查询数据列表
     * 
     * @param basicDataTable 数据
     * @return 数据
     */
    @Override
    public List<BasicDataTable> selectBasicDataTableList(BasicDataTable basicDataTable)
    {
        return basicDataTableMapper.selectBasicDataTableList(basicDataTable);
    }

    /**
     * 新增数据
     * 
     * @param basicDataTable 数据
     * @return 结果
     */
    @Override
    public int insertBasicDataTable(BasicDataTable basicDataTable)
    {
        basicDataTable.setCreateTime(DateUtils.getNowDate());
        return basicDataTableMapper.insertBasicDataTable(basicDataTable);
    }

    /**
     * 修改数据
     * 
     * @param basicDataTable 数据
     * @return 结果
     */
    @Override
    public int updateBasicDataTable(BasicDataTable basicDataTable)
    {
        basicDataTable.setUpdateTime(DateUtils.getNowDate());
        return basicDataTableMapper.updateBasicDataTable(basicDataTable);
    }

    /**
     * 批量删除数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBasicDataTableByIds(Long[] ids)
    {
        return basicDataTableMapper.deleteBasicDataTableByIds(ids);
    }

    /**
     * 删除数据信息
     * 
     * @param id 数据ID
     * @return 结果
     */
    @Override
    public int deleteBasicDataTableById(Long id)
    {
        return basicDataTableMapper.deleteBasicDataTableById(id);
    }
}
