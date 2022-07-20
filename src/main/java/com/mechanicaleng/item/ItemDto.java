package com.mechanicaleng.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto extends ItemEntity{
    private String name;

    private String serial; //serial number for scanning QR code

    private Boolean status = Boolean.TRUE; // to specify if the item is still in lab or not

    private Boolean damaged = Boolean.FALSE; // to specify if the item is damaged or not

    //want to default status = True and damaged = False but no constructor

    //standard getter and setters

    public String getName() {
        return name;
    }


    public String getSerialNumber() {
        return serial;
    }

//    public String getPassword() {
//        return password;
//    }

    public Boolean getStatus() {
        return status;
    }

    public Boolean getDamagedStatus() {
        return damaged;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public void setSerial(String serial) {
        this.serial = serial;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setDamaged(Boolean damaged) {
        this.damaged = damaged;
    }

}
