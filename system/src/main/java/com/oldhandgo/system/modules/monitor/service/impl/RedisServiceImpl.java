package com.oldhandgo.system.modules.monitor.service.impl;

import com.oldhandgo.common.utils.PageUtils;
import com.oldhandgo.system.modules.monitor.domain.vo.RedisVo;
import com.oldhandgo.system.modules.monitor.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author dormirr
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate redisTemplate;

    @Value("${loginCode.expiration}")
    private Long expiration;

    public RedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<RedisVo> findByKey(String key, Pageable pageable) {
        List<RedisVo> redisVos = new ArrayList<>();
        if (!"*".equals(key)) {
            key = "*" + key + "*";
        }
        for (Object s : Objects.requireNonNull(redisTemplate.keys(key))) {
            // 过滤掉权限的缓存
            if (s.toString().contains("role::loadPermissionByUser") || s.toString().contains("user::loadUserByUsername")) {
                continue;
            }
            RedisVo redisVo = new RedisVo(s.toString(), Objects.requireNonNull(redisTemplate.opsForValue().get(s.toString())).toString());
            redisVos.add(redisVo);
        }
        return new PageImpl<RedisVo>(
                PageUtils.toPage(pageable.getPageNumber(), pageable.getPageSize(), redisVos),
                pageable,
                redisVos.size());
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void flushdb() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().flushDb();
    }

    @Override
    public String getCodeVal(String key) {
        try {
            return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void saveCode(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
        redisTemplate.expire(key, expiration, TimeUnit.MINUTES);
    }
}