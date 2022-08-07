package com.mechanicaleng.log;

import com.mechanicaleng.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mechanicaleng.item.ItemEntity;
import com.mechanicaleng.user.UserEntity;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "log")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ItemEntity item;

    /*
    IsCurrent represents if the record is most updated,
    if this is a history log that IsCurrent will be false,
    if this log means the item is currently borrowed by the
    user then IsCurrent will be true.
    */
    private Boolean isCurrent;



    public static LogEntity fromDto(LogDto logDto) {
        return LogEntity.builder().user(logDto.getUser()).item(logDto.getItem()).isCurrent(logDto.getIsCurrent()).build();
    }

    public LogDto toDto() {
        return LogDto.builder().user(this.getUser()).item(this.getItem()).isCurrent(this.getIsCurrent()).build();
    }

    public void updateFromDto(LogDto logDto) {
        this.setItem(logDto.getItem());
        this.setUser(logDto.getUser());
        this.setIsCurrent(logDto.getIsCurrent());
    }







}
