package com.mechanicaleng.user;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserTypeEnum {
    MANAGER("Manager"),
    SUPER_USER("SuperUser"),
    STANDARD_USER("StandardUser");

    @JsonValue
    @Getter
    private String value;
}
