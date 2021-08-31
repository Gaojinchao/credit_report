package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BaiscContractDisputes;

/**
 * 合同纠纷Service接口
 * 
 * @author sss
 * @date 2021-05-20
 */
public interface BaiscContractDisputesService
{
    /**
     * 查询合同纠纷
     * 
     * @param id 合同纠纷ID
     * @return 合同纠纷
     */
    public BaiscContractDisputes selectBaiscContractDisputesById(Long id);

    /**
     * 查询合同纠纷列表
     * 
     * @param baiscContractDisputes 合同纠纷
     * @return 合同纠纷集合
     */
    public List<BaiscContractDisputes> selectBaiscContractDisputesList(BaiscContractDisputes baiscContractDisputes);

    /**
     * 新增合同纠纷
     * 
     * @param baiscContractDisputes 合同纠纷
     * @return 结果
     */
    public int insertBaiscContractDisputes(BaiscContractDisputes baiscContractDisputes);

    /**
     * 修改合同纠纷
     * 
     * @param baiscContractDisputes 合同纠纷
     * @return 结果
     */
    public int updateBaiscContractDisputes(BaiscContractDisputes baiscContractDisputes);

    /**
     * 批量删除合同纠纷
     * 
     * @param ids 需要删除的合同纠纷ID
     * @return 结果
     */
    public int deleteBaiscContractDisputesByIds(Long[] ids);

    /**
     * 删除合同纠纷信息
     * 
     * @param id 合同纠纷ID
     * @return 结果
     */
    public int deleteBaiscContractDisputesById(Long id);
}
