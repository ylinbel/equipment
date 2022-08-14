package com.mechanicaleng.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


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

    private LocalDate utilDate;

    private String email;


    public static UserEntity fromDto(UserDto userDto) {
        return UserEntity.builder().name(userDto.getName()).password(userDto.getPassword()).userTypeEnum(userDto.getUserTypeEnum()).utilDate(userDto.getUtilDate()).email(userDto.getEmail()).build();
    }

    public UserDisplayDto toDto() {
        return UserDisplayDto.builder().name(this.getName()).userTypeEnum(this.getUserTypeEnum()).email(this.getEmail()).utilDate(this.getUtilDate()).build();
    }

    public void updateFromDto(UserDto userDto) {
        this.setPassword(userDto.getPassword());
        this.setUserTypeEnum(userDto.getUserTypeEnum());
        this.setName(userDto.getName());
        this.setUtilDate(userDto.getUtilDate());
        this.setEmail(userDto.getEmail());
    }

}
