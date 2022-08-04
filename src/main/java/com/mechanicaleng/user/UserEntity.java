package com.mechanicaleng.user;

import com.mechanicaleng.item.ItemDto;
import com.mechanicaleng.item.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserTypeEnum userTypeEnum;

    @Temporal(TemporalType.DATE)
    private Date utilDate;

    @OneToMany
    private List<ItemEntity> itemList;

    private String email;

    public static UserEntity fromDto(UserDto userDto) {
        return UserEntity.builder().name(userDto.getName()).password(userDto.getPassword()).userTypeEnum(userDto.getUserTypeEnum()).utilDate(userDto.getUtilDate()).itemList(userDto.getItemList()).email(userDto.getEmail()).build();
    }

    public UserDto toDto() {
        return UserDto.builder().name(this.getName()).password(this.getPassword()).userTypeEnum(this.getUserTypeEnum()).utilDate(this.getUtilDate()).itemList(this.getItemList()).email(this.getEmail()).build();
    }

    public void updateFromDto(UserDto userDto) {
        this.setPassword(userDto.getPassword());
        this.setUserTypeEnum(userDto.getUserTypeEnum());
        this.setName(userDto.getName());
        this.setUtilDate(userDto.getUtilDate());
        this.setItemList(userDto.getItemList());
        this.setEmail(userDto.getEmail());
    }

}
