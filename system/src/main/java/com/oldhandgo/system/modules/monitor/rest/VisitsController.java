package com.oldhandgo.system.modules.monitor.rest;

import com.oldhandgo.common.utils.RequestHolder;
import com.oldhandgo.system.modules.monitor.service.VisitsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dormirr
 */
@RestController
@RequestMapping("api")
public class VisitsController {

    private final VisitsService visitsService;

    public VisitsController(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    @PostMapping(value = "/visits")
    public ResponseEntity create() {
        visitsService.count(RequestHolder.getHttpServletRequest());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/visits")
    public ResponseEntity get() {
        return new ResponseEntity(visitsService.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/visits/chartData")
    public ResponseEntity getChartData() {
        return new ResponseEntity(visitsService.getChartData(), HttpStatus.OK);
    }
}