package com.example.test.search.model;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

@Data
public class Book {

	private List<String> authors;
	private String barcode;
	private String category;
	private String contents;
	private Date datetime;
	private String ebook_barcode;
	private String isbn;
	private Long price;
	private String publisher;
	private Long sale_price;
	private String sale_yn;
	private String status;
	private String thumbnail;
	private String title;
	private List<String> translators;
	private String url;

	private String getAuthorsView(){
		return StringUtils.join(CollectionUtils.emptyIfNull(authors),", ");
	}

}
