package com.mechanicaleng.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDisplayDto {

	private long id;

	private String name;

	private UserTypeEnum userTypeEnum;

	private LocalDate utilDate;

	private String email;
}
