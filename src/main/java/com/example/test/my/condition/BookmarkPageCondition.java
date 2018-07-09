package com.example.test.my.condition;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.example.test.search.condition.BookSearchSortEnum;
import lombok.Data;

@Data
public class BookmarkPageCondition {

	private BookmarkSortEnum sort = BookmarkSortEnum.DATE_DESC;

	@Min(1)
	@Max(50)
	private int page = 1;

	@Min(1)
	@Max(50)
	private int size = 10;

}
