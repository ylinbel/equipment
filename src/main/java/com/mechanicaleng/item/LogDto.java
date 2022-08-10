package com.mechanicaleng.item;

import com.mechanicaleng.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {
    private long id;

    private UserEntity user;

    private ItemEntity item;

    private Boolean isCurrent;
}
