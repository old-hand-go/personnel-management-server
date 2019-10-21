package com.oldhandgo.mapper;

import java.util.List;

/**
 * 实体映射类
 *
 * @author dormirr
 */
public interface EntityMapper<D, E> {

    /**
     * DTO转Entity
     *
     * @param dto DTO类
     * @return 实体类
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     *
     * @param entity 实体类
     * @return DTO类
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     *
     * @param dtoList DTO集合
     * @return 实体类集合
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     *
     * @param entityList 实体类集合
     * @return DTO集合
     */
    List<D> toDto(List<E> entityList);
}
