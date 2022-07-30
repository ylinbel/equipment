package com.mechanicaleng.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String serial; //serial number - QR code

    private StatusEnum statusEnum; //to specify if the item is in available, not available or damaged.

    private String set; // name of the set

//    private String Location;
//
//    private String Category;


    public static ItemEntity fromDto(ItemDto itemDto) {
        return ItemEntity.builder().name(itemDto.getName()).serial(itemDto.getSerial()).statusEnum(itemDto.getStatusEnum()).set(itemDto.getSet()).build();
    }
}
