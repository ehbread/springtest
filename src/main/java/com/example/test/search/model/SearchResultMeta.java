package com.example.test.search.model;

import lombok.Data;

@Data
public class SearchResultMeta {
	private boolean is_end;
	private long pageable_count;
	private long total_count;

}