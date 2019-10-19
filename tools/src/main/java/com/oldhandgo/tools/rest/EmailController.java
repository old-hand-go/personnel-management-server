package com.oldhandgo.tools.rest;

import com.oldhandgo.tools.domain.EmailConfig;
import com.oldhandgo.tools.domain.vo.EmailVo;
import com.oldhandgo.tools.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 发送邮件
 *
 * @author dormirr
 */
@Slf4j
@RestController
@RequestMapping("api")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(value = "/email")
    public ResponseEntity get() {
        return new ResponseEntity(emailService.find(), HttpStatus.OK);
    }

    @PutMapping(value = "/email")
    public ResponseEntity emailConfig(@Validated @RequestBody EmailConfig emailConfig) {
        emailService.update(emailConfig, emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/email")
    public ResponseEntity send(@Validated @RequestBody EmailVo emailVo) throws Exception {
        log.warn("REST request to send Email : {}" + emailVo);
        emailService.send(emailVo, emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }
}