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

    private Long id;

    private String name;

    private String serial; //serial number - QR code

    private StatusEnum statusEnum = StatusEnum.AVAILABLE; //to specify if the item is in available, not available or damaged.

    private String set;

//    private String Location;
//
//    private String Category;
}
