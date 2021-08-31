package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicWinBiddingMapper;
import com.saipaisi.project.basic.domain.BasicWinBidding;
import com.saipaisi.project.basic.service.BasicWinBiddingService;

/**
 * 中标履约Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicWinBiddingServiceImpl implements BasicWinBiddingService
{
    @Autowired
    private BasicWinBiddingMapper basicWinBiddingMapper;

    /**
     * 查询中标履约
     * 
     * @param id 中标履约ID
     * @return 中标履约
     */
    @Override
    public BasicWinBidding selectBasicWinBiddingById(Long id)
    {
        return basicWinBiddingMapper.selectBasicWinBiddingById(id);
    }

    @Override
    public List<BasicWinBidding> selectBasicWinBiddingByOrdersn(String ordersn) {
        return basicWinBiddingMapper.selectBasicWinBiddingByOrdersn(ordersn);
    }

    /**
     * 查询中标履约列表
     * 
     * @param basicWinBidding 中标履约
     * @return 中标履约
     */
    @Override
    public List<BasicWinBidding> selectBasicWinBiddingList(BasicWinBidding basicWinBidding)
    {
        return basicWinBiddingMapper.selectBasicWinBiddingList(basicWinBidding);
    }

    /**
     * 新增中标履约
     * 
     * @param basicWinBidding 中标履约
     * @return 结果
     */
    @Override
    public int insertBasicWinBidding(BasicWinBidding basicWinBidding)
    {
        basicWinBidding.setCreateTime(DateUtils.getNowDate());
        return basicWinBiddingMapper.insertBasicWinBidding(basicWinBidding);
    }

    /**
     * 修改中标履约
     * 
     * @param basicWinBidding 中标履约
     * @return 结果
     */
    @Override
    public int updateBasicWinBidding(BasicWinBidding basicWinBidding)
    {
        basicWinBidding.setUpdateTime(DateUtils.getNowDate());
        return basicWinBiddingMapper.updateBasicWinBidding(basicWinBidding);
    }

    /**
     * 批量删除中标履约
     * 
     * @param ids 需要删除的中标履约ID
     * @return 结果
     */
    @Override
    public int deleteBasicWinBiddingByIds(Long[] ids)
    {
        return basicWinBiddingMapper.deleteBasicWinBiddingByIds(ids);
    }

    /**
     * 删除中标履约信息
     * 
     * @param id 中标履约ID
     * @return 结果
     */
    @Override
    public int deleteBasicWinBiddingById(Long id)
    {
        return basicWinBiddingMapper.deleteBasicWinBiddingById(id);
    }

    @Override
    public int deleteBasicWinBiddingByOrdersn(String ordersn) {
        return basicWinBiddingMapper.deleteBasicWinBiddingByOrdersn(ordersn);
    }
}
