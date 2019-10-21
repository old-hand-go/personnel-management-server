package com.oldhandgo.modules.quartz.task;

import com.oldhandgo.modules.monitor.service.VisitsService;
import org.springframework.stereotype.Component;

/**
 * @author dormirr
 */
@Component
public class VisitsTask {

    private final VisitsService visitsService;

    public VisitsTask(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    public void run() {
        visitsService.save();
    }
}