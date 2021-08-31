package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicJudicativePaper;

/**
 * 裁判文书Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicJudicativePaperMapper 
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
     * 删除裁判文书
     * 
     * @param id 裁判文书ID
     * @return 结果
     */
    public int deleteBasicJudicativePaperById(Long id);

    /**
     * 批量删除裁判文书
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicJudicativePaperByIds(Long[] ids);
}
