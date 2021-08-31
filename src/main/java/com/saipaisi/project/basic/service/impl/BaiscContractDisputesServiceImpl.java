package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BaiscContractDisputesMapper;
import com.saipaisi.project.basic.domain.BaiscContractDisputes;
import com.saipaisi.project.basic.service.BaiscContractDisputesService;

/**
 * 合同纠纷Service业务层处理
 * 
 * @author sss
 * @date 2021-05-20
 */
@Service
public class BaiscContractDisputesServiceImpl implements BaiscContractDisputesService
{
    @Autowired
    private BaiscContractDisputesMapper baiscContractDisputesMapper;

    /**
     * 查询合同纠纷
     * 
     * @param id 合同纠纷ID
     * @return 合同纠纷
     */
    @Override
    public BaiscContractDisputes selectBaiscContractDisputesById(Long id)
    {
        return baiscContractDisputesMapper.selectBaiscContractDisputesById(id);
    }

    /**
     * 查询合同纠纷列表
     * 
     * @param baiscContractDisputes 合同纠纷
     * @return 合同纠纷
     */
    @Override
    public List<BaiscContractDisputes> selectBaiscContractDisputesList(BaiscContractDisputes baiscContractDisputes)
    {
        return baiscContractDisputesMapper.selectBaiscContractDisputesList(baiscContractDisputes);
    }

    /**
     * 新增合同纠纷
     * 
     * @param baiscContractDisputes 合同纠纷
     * @return 结果
     */
    @Override
    public int insertBaiscContractDisputes(BaiscContractDisputes baiscContractDisputes)
    {
        baiscContractDisputes.setCreateTime(DateUtils.getNowDate());
        return baiscContractDisputesMapper.insertBaiscContractDisputes(baiscContractDisputes);
    }

    /**
     * 修改合同纠纷
     * 
     * @param baiscContractDisputes 合同纠纷
     * @return 结果
     */
    @Override
    public int updateBaiscContractDisputes(BaiscContractDisputes baiscContractDisputes)
    {
        baiscContractDisputes.setUpdateTime(DateUtils.getNowDate());
        return baiscContractDisputesMapper.updateBaiscContractDisputes(baiscContractDisputes);
    }

    /**
     * 批量删除合同纠纷
     * 
     * @param ids 需要删除的合同纠纷ID
     * @return 结果
     */
    @Override
    public int deleteBaiscContractDisputesByIds(Long[] ids)
    {
        return baiscContractDisputesMapper.deleteBaiscContractDisputesByIds(ids);
    }

    /**
     * 删除合同纠纷信息
     * 
     * @param id 合同纠纷ID
     * @return 结果
     */
    @Override
    public int deleteBaiscContractDisputesById(Long id)
    {
        return baiscContractDisputesMapper.deleteBaiscContractDisputesById(id);
    }
}
