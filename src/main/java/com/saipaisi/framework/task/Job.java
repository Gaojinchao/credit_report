package com.saipaisi.framework.task;

import com.saipaisi.common.utils.date.DateUtil;
import com.saipaisi.framework.websocket.WebSocketServer;
import com.saipaisi.framework.websocket.WsRespInfo;

import com.saipaisi.project.system.domain.SysDictData;
import com.saipaisi.project.system.domain.SysNotice;
import com.saipaisi.project.system.domain.SysNoticeUser;
import com.saipaisi.project.system.domain.SysUser;
import com.saipaisi.project.system.mapper.SysDictDataMapper;
import com.saipaisi.project.system.mapper.SysNoticeMapper;
import com.saipaisi.project.system.mapper.SysUserMapper;
import com.saipaisi.project.system.service.impl.SysDictDataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: lin.shi
 * @Date: 2020/8/17 6:18 下午
 * @Describe: 具体业务实现类
 */
@Service
@Slf4j
public class Job implements IJob{

    @Resource
    private SysUserMapper userMapper;

//    @Autowired
//    private CompanyMapper companyMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private SysNoticeMapper sysNoticeMapper;


    //公告推送
    @Override
    public void notice(){
        List<SysNoticeUser> list=sysNoticeMapper.listNoticeUser();
        if (!list.isEmpty()&&list!=null){
            list.forEach(e->{
                SysNotice sysNotice =sysNoticeMapper.selectNoticeById(e.getNoticeId());
                try {
                    WebSocketServer.sendInfo(WsRespInfo.builder()
                            .code(200)
                            .message("success")
                            .type(CommonConstans.WS_TYPE_NOTICE)
                            .Data(sysNotice)
                            .build(), e.getUserId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }



}
