package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicCreditRecordMapper;
import com.saipaisi.project.basic.domain.BasicCreditRecord;
import com.saipaisi.project.basic.service.BasicCreditRecordService;

/**
 * 信用记录Service业务层处理
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
@Service
public class BasicCreditRecordServiceImpl implements BasicCreditRecordService
{
    @Autowired
    private BasicCreditRecordMapper basicCreditRecordMapper;

    /**
     * 查询信用记录
     * 
     * @param id 信用记录ID
     * @return 信用记录
     */
    @Override
    public BasicCreditRecord selectBasicCreditRecordById(Long id)
    {
        return basicCreditRecordMapper.selectBasicCreditRecordById(id);
    }

    /**
     * 查询信用记录列表
     * 
     * @param basicCreditRecord 信用记录
     * @return 信用记录
     */
    @Override
    public List<BasicCreditRecord> selectBasicCreditRecordList(BasicCreditRecord basicCreditRecord)
    {
        return basicCreditRecordMapper.selectBasicCreditRecordList(basicCreditRecord);
    }

    /**
     * 新增信用记录
     * 
     * @param basicCreditRecord 信用记录
     * @return 结果
     */
    @Override
    public int insertBasicCreditRecord(BasicCreditRecord basicCreditRecord)
    {
        basicCreditRecord.setCreateTime(DateUtils.getNowDate());
        return basicCreditRecordMapper.insertBasicCreditRecord(basicCreditRecord);
    }

    /**
     * 修改信用记录
     * 
     * @param basicCreditRecord 信用记录
     * @return 结果
     */
    @Override
    public int updateBasicCreditRecord(BasicCreditRecord basicCreditRecord)
    {
        basicCreditRecord.setUpdateTime(DateUtils.getNowDate());
        return basicCreditRecordMapper.updateBasicCreditRecord(basicCreditRecord);
    }

    /**
     * 批量删除信用记录
     * 
     * @param ids 需要删除的信用记录ID
     * @return 结果
     */
    @Override
    public int deleteBasicCreditRecordByIds(Long[] ids)
    {
        return basicCreditRecordMapper.deleteBasicCreditRecordByIds(ids);
    }

    /**
     * 删除信用记录信息
     * 
     * @param id 信用记录ID
     * @return 结果
     */
    @Override
    public int deleteBasicCreditRecordById(Long id)
    {
        return basicCreditRecordMapper.deleteBasicCreditRecordById(id);
    }
}
