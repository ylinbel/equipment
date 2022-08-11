package com.mechanicaleng.item;

import com.mechanicaleng.user.UserDisplayDto;
import com.mechanicaleng.user.UserDto;
import com.mechanicaleng.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {
    private long id;

    private UserDisplayDto user;

    private ItemDto item;

    private LocalDateTime startTime;

    private Boolean overDue;

}
