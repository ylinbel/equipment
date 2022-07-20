package com.mechanicaleng.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private String serial; //serial number for scanning QR code

    private String password;

    private Boolean status; // to specify if the item is still in lab or not

    private Boolean damaged; // to specify if the item is damaged or not


    public static ItemEntity fromDto(ItemDto itemDto){
        return ItemEntity.builder().name(itemDto.getName()).build();
    }
}
