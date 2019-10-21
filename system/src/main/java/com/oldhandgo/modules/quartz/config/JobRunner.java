package com.oldhandgo.modules.quartz.config;

import com.oldhandgo.modules.quartz.domain.QuartzJob;
import com.oldhandgo.modules.quartz.repository.QuartzJobRepository;
import com.oldhandgo.modules.quartz.utils.QuartzManage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dormirr
 */
@Component
public class JobRunner implements ApplicationRunner {

    private final QuartzJobRepository quartzJobRepository;
    private final QuartzManage quartzManage;

    public JobRunner(QuartzManage quartzManage, QuartzJobRepository quartzJobRepository) {
        this.quartzManage = quartzManage;
        this.quartzJobRepository = quartzJobRepository;
    }

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments 应用参数
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobs = quartzJobRepository.findByIsPauseIsFalse();
        quartzJobs.forEach(quartzManage::addJob);
        System.out.println("--------------------定时任务注入完成---------------------");
    }
}