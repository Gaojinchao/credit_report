package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicCopyright;

/**
 * 著作权Service接口
 * 
 * @author alfredzong
 * @date 2021-05-26
 */
public interface BasicCopyrightService
{
    /**
     * 查询著作权
     * 
     * @param id 著作权ID
     * @return 著作权
     */
    public BasicCopyright selectBasicCopyrightById(Long id);

    /**
     * 查询著作权列表
     * 
     * @param basicCopyright 著作权
     * @return 著作权集合
     */
    public List<BasicCopyright> selectBasicCopyrightList(BasicCopyright basicCopyright);

    /**
     * 新增著作权
     * 
     * @param basicCopyright 著作权
     * @return 结果
     */
    public int insertBasicCopyright(BasicCopyright basicCopyright);

    /**
     * 修改著作权
     * 
     * @param basicCopyright 著作权
     * @return 结果
     */
    public int updateBasicCopyright(BasicCopyright basicCopyright);

    /**
     * 批量删除著作权
     * 
     * @param ids 需要删除的著作权ID
     * @return 结果
     */
    public int deleteBasicCopyrightByIds(Long[] ids);

    /**
     * 删除著作权信息
     * 
     * @param id 著作权ID
     * @return 结果
     */
    public int deleteBasicCopyrightById(Long id);

    /**
     * 著作权
     * @return
     */
    public int getCopyrightByName(String keyword,String ordersn);
}
