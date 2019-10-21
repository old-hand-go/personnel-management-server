package com.oldhandgo.modules.quartz.service.impl;

import com.oldhandgo.exception.BadRequestException;
import com.oldhandgo.modules.quartz.domain.QuartzJob;
import com.oldhandgo.modules.quartz.repository.QuartzJobRepository;
import com.oldhandgo.modules.quartz.repository.QuartzLogRepository;
import com.oldhandgo.modules.quartz.service.QuartzJobService;
import com.oldhandgo.modules.quartz.service.dto.JobQueryCriteria;
import com.oldhandgo.modules.quartz.utils.QuartzManage;
import com.oldhandgo.utils.PageUtils;
import com.oldhandgo.utils.QueryHelp;
import com.oldhandgo.utils.ValidationUtils;
import org.quartz.CronExpression;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author dormirr
 */
@Service(value = "quartzJobService")
@CacheConfig(cacheNames = "quartzJob")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QuartzJobServiceImpl implements QuartzJobService {

    private final QuartzJobRepository quartzJobRepository;

    private final QuartzLogRepository quartzLogRepository;

    private final QuartzManage quartzManage;

    public QuartzJobServiceImpl(QuartzJobRepository quartzJobRepository, QuartzLogRepository quartzLogRepository, QuartzManage quartzManage) {
        this.quartzJobRepository = quartzJobRepository;
        this.quartzLogRepository = quartzLogRepository;
        this.quartzManage = quartzManage;
    }

    @Override
    public Object queryAll(JobQueryCriteria criteria, Pageable pageable) {
        return PageUtils.toPage(quartzJobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable));
    }

    @Override
    public Object queryAllLog(JobQueryCriteria criteria, Pageable pageable) {
        return PageUtils.toPage(quartzLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable));
    }

    @Override
    public QuartzJob findById(Long id) {
        Optional<QuartzJob> quartzJob = quartzJobRepository.findById(id);
        ValidationUtils.isNull(quartzJob, "QuartzJob", "id", id);
        return quartzJob.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuartzJob create(QuartzJob resources) {
        if (!CronExpression.isValidExpression(resources.getCronExpression())) {
            throw new BadRequestException("cron表达式格式错误");
        }
        resources = quartzJobRepository.save(resources);
        quartzManage.addJob(resources);
        return resources;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuartzJob resources) {
        if (resources.getId().equals(1L)) {
            throw new BadRequestException("该任务不可操作");
        }
        if (!CronExpression.isValidExpression(resources.getCronExpression())) {
            throw new BadRequestException("cron表达式格式错误");
        }
        resources = quartzJobRepository.save(resources);
        quartzManage.updateJobCron(resources);
    }

    @Override
    public void updateIsPause(QuartzJob quartzJob) {
        if (quartzJob.getId().equals(1L)) {
            throw new BadRequestException("该任务不可操作");
        }
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        quartzJobRepository.save(quartzJob);
    }

    @Override
    public void execution(QuartzJob quartzJob) {
        if (quartzJob.getId().equals(1L)) {
            throw new BadRequestException("该任务不可操作");
        }
        quartzManage.runAJobNow(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(QuartzJob quartzJob) {
        if (quartzJob.getId().equals(1L)) {
            throw new BadRequestException("该任务不可操作");
        }
        quartzManage.deleteJob(quartzJob);
        quartzJobRepository.delete(quartzJob);
    }
}