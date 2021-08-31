package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicScholarshipPrize;

/**
 * 获奖情况Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicScholarshipPrizeMapper 
{
    /**
     * 查询获奖情况
     * 
     * @param id 获奖情况ID
     * @return 获奖情况
     */
    public BasicScholarshipPrize selectBasicScholarshipPrizeById(Long id);

    /**
     * 查询获奖情况列表
     * 
     * @param basicScholarshipPrize 获奖情况
     * @return 获奖情况集合
     */
    public List<BasicScholarshipPrize> selectBasicScholarshipPrizeList(BasicScholarshipPrize basicScholarshipPrize);

    /**
     * 新增获奖情况
     * 
     * @param basicScholarshipPrize 获奖情况
     * @return 结果
     */
    public int insertBasicScholarshipPrize(BasicScholarshipPrize basicScholarshipPrize);

    /**
     * 修改获奖情况
     * 
     * @param basicScholarshipPrize 获奖情况
     * @return 结果
     */
    public int updateBasicScholarshipPrize(BasicScholarshipPrize basicScholarshipPrize);

    /**
     * 删除获奖情况
     * 
     * @param id 获奖情况ID
     * @return 结果
     */
    public int deleteBasicScholarshipPrizeById(Long id);

    /**
     * 批量删除获奖情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicScholarshipPrizeByIds(Long[] ids);
}
