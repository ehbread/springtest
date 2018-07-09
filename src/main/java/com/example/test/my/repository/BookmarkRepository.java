package com.example.test.my.repository;

import com.example.test.my.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {

	Page<Bookmark> findByMemberId(Long memberId, Pageable pageable);
}