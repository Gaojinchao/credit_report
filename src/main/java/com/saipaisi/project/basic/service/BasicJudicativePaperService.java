package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicJudicativePaper;

/**
 * 裁判文书Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicJudicativePaperService
{
    /**
     * 查询裁判文书
     * 
     * @param id 裁判文书ID
     * @return 裁判文书
     */
    public BasicJudicativePaper selectBasicJudicativePaperById(Long id);

    /**
     * 查询裁判文书列表
     * 
     * @param basicJudicativePaper 裁判文书
     * @return 裁判文书集合
     */
    public List<BasicJudicativePaper> selectBasicJudicativePaperList(BasicJudicativePaper basicJudicativePaper);

    /**
     * 新增裁判文书
     * 
     * @param basicJudicativePaper 裁判文书
     * @return 结果
     */
    public int insertBasicJudicativePaper(BasicJudicativePaper basicJudicativePaper);

    /**
     * 修改裁判文书
     * 
     * @param basicJudicativePaper 裁判文书
     * @return 结果
     */
    public int updateBasicJudicativePaper(BasicJudicativePaper basicJudicativePaper);

    /**
     * 批量删除裁判文书
     * 
     * @param ids 需要删除的裁判文书ID
     * @return 结果
     */
    public int deleteBasicJudicativePaperByIds(Long[] ids);

    /**
     * 删除裁判文书信息
     * 
     * @param id 裁判文书ID
     * @return 结果
     */
    public int deleteBasicJudicativePaperById(Long id);

    /**
     * 查询第三方裁判文书接口
     * @return
     */
    public int getLawsuitListByName(String kerword,String ordersn);
}
