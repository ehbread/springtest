package com.example.test.search.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BookSearchTargetEnum {
	all("전체"),
	title("제목에서 검색"),
	isbn("ISBN에서 검색"),
	keyword("주제어에서 검색"),
	contents("목차에서 검색"),
	overview("책소개에서 검색"),
	publisher("출판사에서 검색");

	@Getter
	private String description;

}
