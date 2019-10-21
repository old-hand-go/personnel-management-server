package com.oldhandgo.system.modules.monitor.rest;

import com.oldhandgo.logging.log.Log;
import com.oldhandgo.system.modules.monitor.domain.vo.RedisVo;
import com.oldhandgo.system.modules.monitor.service.RedisService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author dormirr
 */
@RestController
@RequestMapping("api")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Log("查询Redis缓存")
    @GetMapping(value = "/redis")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_SELECT')")
    public ResponseEntity getRedis(String key, Pageable pageable) {
        return new ResponseEntity(redisService.findByKey(key, pageable), HttpStatus.OK);
    }

    @Log("删除Redis缓存")
    @DeleteMapping(value = "/redis")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_DELETE')")
    public ResponseEntity delete(@RequestBody RedisVo resources) {
        redisService.delete(resources.getKey());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("清空Redis缓存")
    @DeleteMapping(value = "/redis/all")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_DELETE')")
    public ResponseEntity deleteAll() {
        redisService.flushdb();
        return new ResponseEntity(HttpStatus.OK);
    }
}