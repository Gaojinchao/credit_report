package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicSoftwareCopyright;

/**
 * 软件著作权Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicSoftwareCopyrightMapper 
{
    /**
     * 查询软件著作权
     * 
     * @param id 软件著作权ID
     * @return 软件著作权
     */
    public BasicSoftwareCopyright selectBasicSoftwareCopyrightById(Long id);

    /**
     * 查询软件著作权列表
     * 
     * @param basicSoftwareCopyright 软件著作权
     * @return 软件著作权集合
     */
    public List<BasicSoftwareCopyright> selectBasicSoftwareCopyrightList(BasicSoftwareCopyright basicSoftwareCopyright);

    /**
     * 新增软件著作权
     * 
     * @param basicSoftwareCopyright 软件著作权
     * @return 结果
     */
    public int insertBasicSoftwareCopyright(BasicSoftwareCopyright basicSoftwareCopyright);

    /**
     * 修改软件著作权
     * 
     * @param basicSoftwareCopyright 软件著作权
     * @return 结果
     */
    public int updateBasicSoftwareCopyright(BasicSoftwareCopyright basicSoftwareCopyright);

    /**
     * 删除软件著作权
     * 
     * @param id 软件著作权ID
     * @return 结果
     */
    public int deleteBasicSoftwareCopyrightById(Long id);

    /**
     * 批量删除软件著作权
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicSoftwareCopyrightByIds(Long[] ids);
}
