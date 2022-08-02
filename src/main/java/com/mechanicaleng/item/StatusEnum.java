package com.mechanicaleng.item;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusEnum {
	AVAILABLE("Available"),
	DAMAGED("Damaged"),
	NOT_AVAILABLE("NotAvailable");

	@JsonValue
	@Getter
	private String value;
}

