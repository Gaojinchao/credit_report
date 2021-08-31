package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicShareholder;

/**
 * 股东组成Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicShareholderMapper 
{
    /**
     * 查询股东组成
     * 
     * @param id 股东组成ID
     * @return 股东组成
     */
    public BasicShareholder selectBasicShareholderById(Long id);

    /**
     * 查询股东组成列表
     * 
     * @param basicShareholder 股东组成
     * @return 股东组成集合
     */
    public List<BasicShareholder> selectBasicShareholderList(BasicShareholder basicShareholder);

    /**
     * 新增股东组成
     * 
     * @param basicShareholder 股东组成
     * @return 结果
     */
    public int insertBasicShareholder(BasicShareholder basicShareholder);

    /**
     * 修改股东组成
     * 
     * @param basicShareholder 股东组成
     * @return 结果
     */
    public int updateBasicShareholder(BasicShareholder basicShareholder);

    /**
     * 删除股东组成
     * 
     * @param id 股东组成ID
     * @return 结果
     */
    public int deleteBasicShareholderById(Long id);

    /**
     * 批量删除股东组成
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicShareholderByIds(Long[] ids);
}
