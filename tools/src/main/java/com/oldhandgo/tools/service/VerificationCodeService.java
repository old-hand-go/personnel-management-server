package com.oldhandgo.tools.service;

import com.oldhandgo.tools.domain.VerificationCode;
import com.oldhandgo.tools.domain.vo.EmailVo;

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