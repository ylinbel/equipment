package com.mechanicaleng.item;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BorrowTermEnum {
    DAILY("Daily"),
    WEEKLY("Weekly"),
    TWOWEEK("TwoWeek"),
    MONTHLY("Monthly"),
    THREEMONTH("ThreeMonth");

    @JsonValue
    @Getter
    private String value;
}
