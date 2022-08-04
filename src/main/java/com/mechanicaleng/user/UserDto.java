package com.mechanicaleng.user;

import com.mechanicaleng.item.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    private List<ItemEntity> itemList;

    private String email;
}
