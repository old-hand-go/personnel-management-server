package com.oldhandgo.repository;

import com.oldhandgo.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dormirr
 */
public interface EmailRepository extends JpaRepository<EmailConfig, Long> {
}
