package com.saipaisi.framework.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 定时任务调度测试
 *
 * @author saipaisi
 */
@Component("ryTask")
@Slf4j
public class RyTask {


    @Resource
    private Job job;


    /**
     * 公告推送
     */
    public void noticeTask(){
        job.notice();
    }


}
