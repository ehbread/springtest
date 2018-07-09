package com.example.test.search.service;

import com.example.test.my.service.MyService;
import com.example.test.search.condition.BookSearchCondition;
import com.example.test.search.model.Book;
import com.example.test.search.model.SearchResult;
import com.example.test.search.repository.SearchBookRestTemplate;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BookSearchService {
	@Autowired
	private MyService myService;

	@Autowired
	private SearchBookRestTemplate searchBookRestTemplate;

	public Page<Book> getBookList(final BookSearchCondition condition) {

		myService.addSearchHistory(condition);

		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("query", condition.getQuery());
		param.add("page", (condition.getPage()) + "");
		param.add("page", (condition.getPage()) + "");
		param.add("sort", condition.getSort().name());

		ResponseEntity<SearchResult> responseEntity = searchBookRestTemplate.getSearch(param, SearchResult.class);

		Preconditions.checkNotNull(responseEntity, "데이터를 가져오지 못했습니다.");

		SearchResult searchResult = responseEntity.getBody();

		return new PageImpl<>(searchResult.getDocuments(), new PageRequest(condition.getPage() - 1, condition.getSize()),
			searchResult.getMeta().getTotal_count());
	}

}