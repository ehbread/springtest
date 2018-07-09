package com.example.test.search.condition;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum BookSearchSortEnum {
	accuracy("정확도순"),
	recency("최신순"),
	sales("판매량순");

	@Getter
	private String description;

}
