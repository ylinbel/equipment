package com.mechanicaleng.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    private String name;

    private String password;

    private UserTypeEnum userTypeEnum;

    private Date utilDate;

    private String email;
}
