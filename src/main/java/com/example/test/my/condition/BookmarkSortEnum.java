package com.example.test.my.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
public enum BookmarkSortEnum {
	DATE_DESC("등록일자-냬림차순", new Sort(new Sort.Order(Sort.Direction.DESC, "createdDate"))),
	DATE_ASC("등록일자-오름차순", new Sort(new Sort.Order(Sort.Direction.ASC, "createdDate"))),
	TITLE_DESC("Tilte-냬림차순", new Sort(new Sort.Order(Sort.Direction.DESC, "title"))),
	TITLE_ASC("Tilte-오름차순", new Sort(new Sort.Order(Sort.Direction.ASC, "title")));

	@Getter
	private String description;

	@Getter
	private Sort sort;

}
