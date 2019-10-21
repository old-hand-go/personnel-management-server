package com.oldhandgo.rest;

import com.oldhandgo.domain.VerificationCode;
import com.oldhandgo.domain.vo.EmailVo;
import com.oldhandgo.service.EmailService;
import com.oldhandgo.service.VerificationCodeService;
import com.oldhandgo.utils.ElAdminConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author dormirr
 */
@RestController
@RequestMapping("api")
public class VerificationCodeController {

    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;

    public VerificationCodeController(VerificationCodeService verificationCodeService, EmailService emailService) {
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/code/resetEmail")
    public ResponseEntity resetEmail(@RequestBody VerificationCode code) throws Exception {
        code.setScenes(ElAdminConstant.RESET_MAIL);
        EmailVo emailVo = verificationCodeService.sendEmail(code);
        emailService.send(emailVo,emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/code/email/resetPass")
    public ResponseEntity resetPass(@RequestParam String email) throws Exception {
        VerificationCode code = new VerificationCode();
        code.setType("email");
        code.setValue(email);
        code.setScenes(ElAdminConstant.RESET_MAIL);
        EmailVo emailVo = verificationCodeService.sendEmail(code);
        emailService.send(emailVo,emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/code/validated")
    public ResponseEntity validated(VerificationCode code){
        verificationCodeService.validated(code);
        return new ResponseEntity(HttpStatus.OK);
    }
}