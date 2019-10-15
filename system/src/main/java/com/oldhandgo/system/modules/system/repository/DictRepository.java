package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author dormirr
 */
public interface DictRepository extends JpaRepository<Dict, Long>, PagingAndSortingRepository<Dict, Long> {
}