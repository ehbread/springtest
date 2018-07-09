package com.example.test.search.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
	private List<Book> documents;
	private SearchResultMeta meta;
}

