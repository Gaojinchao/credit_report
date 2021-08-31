package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicScholarshipPrizeMapper;
import com.saipaisi.project.basic.domain.BasicScholarshipPrize;
import com.saipaisi.project.basic.service.BasicScholarshipPrizeService;

/**
 * 获奖情况Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicScholarshipPrizeServiceImpl implements BasicScholarshipPrizeService
{
    @Autowired
    private BasicScholarshipPrizeMapper basicScholarshipPrizeMapper;

    /**
     * 查询获奖情况
     * 
     * @param id 获奖情况ID
     * @return 获奖情况
     */
    @Override
    public BasicScholarshipPrize selectBasicScholarshipPrizeById(Long id)
    {
        return basicScholarshipPrizeMapper.selectBasicScholarshipPrizeById(id);
    }

    /**
     * 查询获奖情况列表
     * 
     * @param basicScholarshipPrize 获奖情况
     * @return 获奖情况
     */
    @Override
    public List<BasicScholarshipPrize> selectBasicScholarshipPrizeList(BasicScholarshipPrize basicScholarshipPrize)
    {
        return basicScholarshipPrizeMapper.selectBasicScholarshipPrizeList(basicScholarshipPrize);
    }

    /**
     * 新增获奖情况
     * 
     * @param basicScholarshipPrize 获奖情况
     * @return 结果
     */
    @Override
    public int insertBasicScholarshipPrize(BasicScholarshipPrize basicScholarshipPrize)
    {
        basicScholarshipPrize.setCreateTime(DateUtils.getNowDate());
        return basicScholarshipPrizeMapper.insertBasicScholarshipPrize(basicScholarshipPrize);
    }

    /**
     * 修改获奖情况
     * 
     * @param basicScholarshipPrize 获奖情况
     * @return 结果
     */
    @Override
    public int updateBasicScholarshipPrize(BasicScholarshipPrize basicScholarshipPrize)
    {
        basicScholarshipPrize.setUpdateTime(DateUtils.getNowDate());
        return basicScholarshipPrizeMapper.updateBasicScholarshipPrize(basicScholarshipPrize);
    }

    /**
     * 批量删除获奖情况
     * 
     * @param ids 需要删除的获奖情况ID
     * @return 结果
     */
    @Override
    public int deleteBasicScholarshipPrizeByIds(Long[] ids)
    {
        return basicScholarshipPrizeMapper.deleteBasicScholarshipPrizeByIds(ids);
    }

    /**
     * 删除获奖情况信息
     * 
     * @param id 获奖情况ID
     * @return 结果
     */
    @Override
    public int deleteBasicScholarshipPrizeById(Long id)
    {
        return basicScholarshipPrizeMapper.deleteBasicScholarshipPrizeById(id);
    }
}
