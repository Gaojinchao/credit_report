package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicScholarshipPrize;

/**
 * 获奖情况Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicScholarshipPrizeService
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
     * 批量删除获奖情况
     * 
     * @param ids 需要删除的获奖情况ID
     * @return 结果
     */
    public int deleteBasicScholarshipPrizeByIds(Long[] ids);

    /**
     * 删除获奖情况信息
     * 
     * @param id 获奖情况ID
     * @return 结果
     */
    public int deleteBasicScholarshipPrizeById(Long id);
}
