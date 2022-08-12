package com.mechanicaleng.item;

import com.mechanicaleng.user.UserDisplayDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BorrowLogDto {
    private long id;

    private UserDisplayDto user;

    private ItemDto item;

    private LocalDateTime startTime;

    private Boolean overDue;

}
