package com.example.test.search.condition;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BookSearchCondition {
	@NotEmpty
	private String query;

	private BookSearchSortEnum sort = BookSearchSortEnum.accuracy;

	@Min(1)
	@Max(50)
	private int page = 1;

	@Min(1)
	@Max(50)
	private int size = 10;

	@NotNull
	private BookSearchTargetEnum target = BookSearchTargetEnum.all;

	private String category;

}
