package com.example.test.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.test.search.condition.BookSearchCondition;
import com.example.test.search.model.Book;
import com.example.test.search.service.BookSearchService;
import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookSearchController {

	@Autowired
	private BookSearchService bookSearchService;

	@RequestMapping("/booksearch/list")
	@GetMapping
	@ResponseBody
	public Page<Book> getBookList(@RequestBody BookSearchCondition condition) {

		Preconditions.checkArgument(StringUtils.isNotEmpty(condition.getQuery()), "검색어를 넣어주세요");
		return bookSearchService.getBookList(condition);
	}

}