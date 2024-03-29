package com.oldhandgo.modules.system.repository;

import com.oldhandgo.modules.system.domain.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author dormirr
 */
public interface UserAvatarRepository extends JpaRepository<UserAvatar, Long>, JpaSpecificationExecutor {

}