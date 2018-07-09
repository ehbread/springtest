package com.example.test.my.service;

import com.example.test.my.condition.BookmarkPageCondition;
import com.example.test.my.entity.Bookmark;
import com.example.test.my.repository.BookmarkRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.login.entity.Member;
import com.example.test.login.service.LoginService;
import com.example.test.my.entity.SearchHistory;
import com.example.test.my.repository.SearchHistoryRepository;
import com.example.test.search.condition.BookSearchCondition;
import com.example.test.my.condition.PageCondition;
import com.example.test.search.model.Book;
import com.google.common.base.Preconditions;

@Service
public class MyService {
	@Autowired
	private LoginService loginService;

	@Autowired
	private SearchHistoryRepository searchHistoryRepository;

	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Transactional(value = "transactionManager", readOnly = false)
	public SearchHistory addSearchHistory(final BookSearchCondition condition) {

		final Member member = loginService.getLoginInfo();

		if (null != member) {

			SearchHistory searchHistory = new SearchHistory();
			searchHistory.setMemberId(member.getMemberId());
			searchHistory.setQuery(condition.getQuery());
			return searchHistoryRepository.save(searchHistory);
		}
		return null;

	}

	public Page<SearchHistory> getPageSearchHistory(PageCondition condition) {

		Member member = loginService.getLoginInfo();

		if (null != member) {
			return searchHistoryRepository.findByMemberId(member.getMemberId(),
				new PageRequest(condition.getPage() - 1, condition.getSize(), new Sort(
					new Sort.Order(Sort.Direction.DESC, "createdDate"))));
		}
		return new PageImpl<>(Lists.newArrayList());

	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Bookmark addBookmark(final Book book) {

		final Member member = loginService.getLoginInfo();

		Preconditions.checkState(null != member, "로그인이 필요합니다.");

		Bookmark bookmark = new Bookmark();
		bookmark.setMemberId(member.getMemberId());
		bookmark.setTitle(book.getTitle());
		return bookmarkRepository.save(bookmark);

	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Bookmark removeBookmark(final Long bookmarkId) {

		final Member member = loginService.getLoginInfo();

		Preconditions.checkState(null != member, "로그인이 필요합니다.");

		Bookmark bookmark = bookmarkRepository.findOne(bookmarkId);

		Preconditions.checkState(null != bookmark, "존재하지 않는 데이터입니다.");

		Preconditions.checkState(member.getMemberId().longValue() == bookmark.getMemberId().longValue(), "정상적인 요청이 아닙니다.");

		bookmarkRepository.delete(bookmark);

		return bookmark;

	}

	public Page<Bookmark> getPageBookmark(BookmarkPageCondition condition) {

		Member member = loginService.getLoginInfo();

		if (null != member) {
			return bookmarkRepository.findByMemberId(member.getMemberId(),
				new PageRequest(condition.getPage() - 1, condition.getSize(), condition.getSort().getSort()));
		}
		return new PageImpl<>(Lists.newArrayList());

	}

}