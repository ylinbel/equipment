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

    private String serial; //serial number - QR code

    private String password;

    private Status status; //to specify if the item is in available, not available or damaged.


    public static ItemEntity fromDto(ItemDto itemDto){
        return ItemEntity.builder().name(itemDto.getName()).build();
    }



    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serial;
    }

    public String getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
