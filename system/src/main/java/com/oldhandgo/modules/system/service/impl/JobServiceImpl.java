package com.oldhandgo.modules.system.service.impl;

import com.oldhandgo.modules.system.domain.Job;
import com.oldhandgo.modules.system.repository.DeptRepository;
import com.oldhandgo.modules.system.repository.JobRepository;
import com.oldhandgo.modules.system.service.JobService;
import com.oldhandgo.modules.system.service.dto.JobDTO;
import com.oldhandgo.modules.system.service.dto.JobQueryCriteria;
import com.oldhandgo.modules.system.service.mapper.JobMapper;
import com.oldhandgo.utils.PageUtils;
import com.oldhandgo.utils.QueryHelp;
import com.oldhandgo.utils.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author dormirr
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;

    private final DeptRepository deptRepository;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, DeptRepository deptRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.deptRepository = deptRepository;
    }

    @Override
    public Object queryAll(JobQueryCriteria criteria, Pageable pageable) {
        Page<Job> page = jobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        List<JobDTO> jobs = new ArrayList<>();
        for (Job job : page.getContent()) {
            jobs.add(jobMapper.toDto(job, deptRepository.findNameById(job.getDept().getPid())));
        }
        return PageUtils.toPage(jobs, page.getTotalElements());
    }

    @Override
    public JobDTO findById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        ValidationUtils.isNull(job, "Job", "id", id);
        return jobMapper.toDto(job.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JobDTO create(Job resources) {
        return jobMapper.toDto(jobRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Job resources) {
        Optional<Job> optionalJob = jobRepository.findById(resources.getId());
        ValidationUtils.isNull(optionalJob, "Job", "id", resources.getId());

        Job job = optionalJob.get();
        resources.setId(job.getId());
        jobRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        jobRepository.deleteById(id);
    }
}