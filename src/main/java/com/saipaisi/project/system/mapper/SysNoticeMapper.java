package com.saipaisi.project.system.mapper;

import java.util.List;
import com.saipaisi.project.system.domain.SysNotice;
import com.saipaisi.project.system.domain.SysNoticeUser;
import org.apache.ibatis.annotations.Param;

/**
 * 通知公告表 数据层
 * 
 * @author saipaisi
 */
public interface SysNoticeMapper
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     *查詢所有的公告
     * @return
     */
    public List<SysNotice> allNoticeList(@Param("userId") Long userId);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);

    public int addNoticeUser(SysNoticeUser sysNoticeUser);

    /**
     * 删除公告绑定
     * @return
     */
    public int deleteNoticeUser(@Param("noticeId") Long noticeId);

    //查詢公告綁定關係
    public List<SysNoticeUser> listNoticeUser();

    /**
     * 修改公告用戶
     * @param id
     * @param userId
     * @return
     */
    int updateNoticeUser(@Param("id")Long id,@Param("userId") Long userId);
}