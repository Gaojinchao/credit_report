package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicWinBidding;

/**
 * 中标履约Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicWinBiddingMapper 
{
    /**
     * 查询中标履约
     * 
     * @param id 中标履约ID
     * @return 中标履约
     */
    public BasicWinBidding selectBasicWinBiddingById(Long id);

    /**
     * 查询中标履约
     *
     * @param ordersn 中标履约ID
     * @return 中标履约
     */
    public List<BasicWinBidding> selectBasicWinBiddingByOrdersn(String ordersn);

    /**
     * 查询中标履约列表
     * 
     * @param basicWinBidding 中标履约
     * @return 中标履约集合
     */
    public List<BasicWinBidding> selectBasicWinBiddingList(BasicWinBidding basicWinBidding);

    /**
     * 新增中标履约
     * 
     * @param basicWinBidding 中标履约
     * @return 结果
     */
    public int insertBasicWinBidding(BasicWinBidding basicWinBidding);

    /**
     * 修改中标履约
     * 
     * @param basicWinBidding 中标履约
     * @return 结果
     */
    public int updateBasicWinBidding(BasicWinBidding basicWinBidding);

    /**
     * 删除中标履约
     * 
     * @param id 中标履约ID
     * @return 结果
     */
    public int deleteBasicWinBiddingById(Long id);

    /**
     * 批量删除中标履约
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicWinBiddingByIds(Long[] ids);

    /**
     * 删除中标履约根据订单号
     * @param ordersn
     * @return
     */
    public int deleteBasicWinBiddingByOrdersn(String ordersn);
}
