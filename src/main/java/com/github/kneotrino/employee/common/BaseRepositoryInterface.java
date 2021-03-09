package com.github.kneotrino.employee.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepositoryInterface<E extends BaseModel, N> extends PagingAndSortingRepository<E, N> {
  List<E> findAllByEnabledTrue();
  Page<E> findAllByEnabledTrue(Pageable pageable);
  Optional<E> findByEnabledTrueAndId(Long id);
  Optional<E> findByEnabledFalseAndId(Long id);
}
