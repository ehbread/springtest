package com.example.test.my.controller;

import com.example.test.my.condition.BookmarkPageCondition;
import com.example.test.my.condition.BookmarkSortEnum;
import com.example.test.my.entity.Bookmark;
import com.example.test.search.condition.BookSearchSortEnum;
import com.example.test.search.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.my.entity.SearchHistory;
import com.example.test.my.service.MyService;
import com.example.test.my.condition.PageCondition;

@Controller
@RequestMapping("/my")
public class MyController {

	@Autowired
	private MyService myService;

	@GetMapping(path = "/searchhistory")
	public ModelAndView indexSearchhistory(ModelAndView mav) {
		mav.setViewName("my/search-history");
		return mav;
	}

	@ResponseBody
	@RequestMapping(path = "/searchhistory/list")
	public Page<SearchHistory> searchHistoryList(@RequestBody PageCondition condition) {
		return myService.getPageSearchHistory(condition);
	}

	@GetMapping(path = "/bookmark")
	public ModelAndView indexBookmark(ModelAndView mav) {
		mav.setViewName("my/bookmark");
		mav.addObject("searchSorts", BookmarkSortEnum.values());
		return mav;
	}

	@ResponseBody
	@RequestMapping(path = "/bookmark/add")
	public Bookmark addBookmark(@RequestBody Book book) {
		return myService.addBookmark(book);

	}

	@ResponseBody
	@RequestMapping(path = "/bookmark/remove")
	public Bookmark addBookmark(@RequestParam(value = "bookmarkId") Long bookmarkId) {
		return myService.removeBookmark(bookmarkId);

	}

	@ResponseBody
	@RequestMapping(path = "/bookmark/list")
	public Page<Bookmark> bookmarkList(@RequestBody BookmarkPageCondition condition) {
		return myService.getPageBookmark(condition);
	}

}