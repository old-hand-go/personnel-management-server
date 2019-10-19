package com.oldhandgo.tools.repository;

import com.oldhandgo.tools.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dormirr
 */
public interface EmailRepository extends JpaRepository<EmailConfig, Long> {
}
