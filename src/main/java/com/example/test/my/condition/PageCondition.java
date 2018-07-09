package com.example.test.my.condition;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class PageCondition {
	@Min(1)
	@Max(50)
	private int page = 1;

	@Min(1)
	@Max(50)
	private int size = 10;

}
