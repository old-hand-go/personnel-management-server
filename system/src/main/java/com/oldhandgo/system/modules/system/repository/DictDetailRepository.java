package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author dormirr
 */
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, PagingAndSortingRepository<DictDetail, Long> {
}