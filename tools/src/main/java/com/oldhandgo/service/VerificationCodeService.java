package com.oldhandgo.service;

import com.oldhandgo.domain.VerificationCode;
import com.oldhandgo.domain.vo.EmailVo;

/**
 * @author dormirr
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(VerificationCode code);

    /**
     * 验证
     * @param code
     */
    void validated(VerificationCode code);
}