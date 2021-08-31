package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicShareholder;

/**
 * 股东组成Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicShareholderService
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
     * 批量删除股东组成
     * 
     * @param ids 需要删除的股东组成ID
     * @return 结果
     */
    public int deleteBasicShareholderByIds(Long[] ids);

    /**
     * 删除股东组成信息
     * 
     * @param id 股东组成ID
     * @return 结果
     */
    public int deleteBasicShareholderById(Long id);

    /**
     *获取股东信息
     * @return
     */
    public int getPartnersInfo(String kerword,String ordersn);
}
