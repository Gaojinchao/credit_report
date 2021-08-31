package com.saipaisi.project.system.service.impl;

import java.util.List;

import com.saipaisi.framework.aspectj.lang.annotation.DataScope;
import com.saipaisi.project.system.domain.SysNoticeUser;
import com.saipaisi.project.system.domain.SysUser;
import com.saipaisi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.system.domain.SysNotice;
import com.saipaisi.project.system.mapper.SysNoticeMapper;
import com.saipaisi.project.system.service.ISysNoticeService;

/**
 * 公告 服务层实现
 * 
 * @author saipaisi
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper noticeMapper;

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    @DataScope(deptAlias = "sys_notice")
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 公告列表
     * @param id
     * @return
     */
    @Override
    public List<SysNotice> selectAllNoticeList(Long id) {

        return noticeMapper.allNoticeList(id);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        SysUser sysUser=new SysUser();
        sysUser.setStatus("0");
        noticeMapper.insertNotice(notice);
        List<SysUser> list=iSysUserService.selectUserList(sysUser);
        list.forEach(e->{
            SysNoticeUser sysNoticeUser=new SysNoticeUser();
            sysNoticeUser.setNoticeId(notice.getNoticeId());
            sysNoticeUser.setUserId(e.getUserId());
            noticeMapper.addNoticeUser(sysNoticeUser);
        });
        return 1;
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        for (Long l:noticeIds
             ) {
            noticeMapper.deleteNoticeUser(l);
        }
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

    /**
     * 修改用戶公告狀態
     * @param noticeId
     * @param userId
     * @return
     */
    @Override
    public int updateNoticeUser(Long noticeId, Long userId) {
        noticeMapper.updateNoticeUser(noticeId,userId);
        return 1;
    }
}
