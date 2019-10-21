package com.oldhandgo.rest;

import com.oldhandgo.domain.EmailConfig;
import com.oldhandgo.domain.vo.EmailVo;
import com.oldhandgo.log.Log;
import com.oldhandgo.service.EmailService;
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

    @Log("配置邮件")
    @PutMapping(value = "/email")
    public ResponseEntity emailConfig(@Validated @RequestBody EmailConfig emailConfig) {
        emailService.update(emailConfig, emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("发送邮件")
    @PostMapping(value = "/email")
    public ResponseEntity send(@Validated @RequestBody EmailVo emailVo) throws Exception {
        log.warn("REST request to send Email : {}" + emailVo);
        emailService.send(emailVo, emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }
}