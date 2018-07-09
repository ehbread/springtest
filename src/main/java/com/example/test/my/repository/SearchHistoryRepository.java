package com.example.test.my.repository;

import com.example.test.my.entity.SearchHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryRepository extends CrudRepository<SearchHistory, Long> {

	Page<SearchHistory> findByMemberId(Long memberId, Pageable pageable);
}