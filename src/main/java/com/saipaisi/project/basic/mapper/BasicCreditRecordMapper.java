package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicCreditRecord;

/**
 * 信用记录Mapper接口
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
public interface BasicCreditRecordMapper 
{
    /**
     * 查询信用记录
     * 
     * @param id 信用记录ID
     * @return 信用记录
     */
    public BasicCreditRecord selectBasicCreditRecordById(Long id);

    /**
     * 查询信用记录列表
     * 
     * @param basicCreditRecord 信用记录
     * @return 信用记录集合
     */
    public List<BasicCreditRecord> selectBasicCreditRecordList(BasicCreditRecord basicCreditRecord);

    /**
     * 新增信用记录
     * 
     * @param basicCreditRecord 信用记录
     * @return 结果
     */
    public int insertBasicCreditRecord(BasicCreditRecord basicCreditRecord);

    /**
     * 修改信用记录
     * 
     * @param basicCreditRecord 信用记录
     * @return 结果
     */
    public int updateBasicCreditRecord(BasicCreditRecord basicCreditRecord);

    /**
     * 删除信用记录
     * 
     * @param id 信用记录ID
     * @return 结果
     */
    public int deleteBasicCreditRecordById(Long id);

    /**
     * 批量删除信用记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicCreditRecordByIds(Long[] ids);
}
