package com.oldhandgo.tools.repository;

import com.oldhandgo.tools.domain.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dormirr
 */
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    /**
     * 获取有效的验证码
     *
     * @param scenes    业务场景，如重置密码，重置邮箱
     * @param isType    验证码类型 1验证码 2邮件验证
     * @param codeValue 验证码
     * @param isStatus  1有效 0过期
     * @return 验证码
     */
    VerificationCode findByScenesAndIsTypeAndCodeValueAndIsStatus(String scenes, Byte isType, String codeValue, Byte isStatus);
}
