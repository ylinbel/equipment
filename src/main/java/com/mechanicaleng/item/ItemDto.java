package com.mechanicaleng.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto{

    private String name;

    private String serial; //serial number for scanning QR code

    private String password;

    private Status status = Status.Available; //to specify if the item is in available, not available or damaged.

}
