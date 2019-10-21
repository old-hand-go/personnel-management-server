package com.oldhandgo.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 实体类不存在异常
 *
 * @author dormir
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class clazz, Object... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " 不存在 " +
                searchParams;
    }

    private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        int mod = 2;
        if (entries.length % mod == 1) {
            throw new IllegalArgumentException("无效");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}