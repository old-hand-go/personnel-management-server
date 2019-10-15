package com.oldhandgo.system.modules.security.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * JWT权限类Impl
 *
 * @author dormirr
 */
@Service
@CacheConfig(cacheNames = "role")
public class JwtPermissionServiceImpl {

}
