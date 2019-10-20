package com.oldhandgo.system.modules.quartz.rest;

import com.oldhandgo.common.exception.BadRequestException;
import com.oldhandgo.system.modules.quartz.domain.QuartzJob;
import com.oldhandgo.system.modules.quartz.service.QuartzJobService;
import com.oldhandgo.system.modules.quartz.service.dto.JobQueryCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author dormirr
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";
    private final QuartzJobService quartzJobService;

    public QuartzJobController(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    @GetMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(quartzJobService.queryAll(criteria,pageable), HttpStatus.OK);
    }
    @GetMapping(value = "/jobLogs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobLogs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(quartzJobService.queryAllLog(criteria,pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_CREATE')")
    public ResponseEntity create(@Validated @RequestBody QuartzJob resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(quartzJobService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity update(@Validated(QuartzJob.Update.class) @RequestBody QuartzJob resources){
        quartzJobService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity updateIsPause(@PathVariable Long id){
        quartzJobService.updateIsPause(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/jobs/exec/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity execution(@PathVariable Long id){
        quartzJobService.execution(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        quartzJobService.delete(quartzJobService.findById(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}