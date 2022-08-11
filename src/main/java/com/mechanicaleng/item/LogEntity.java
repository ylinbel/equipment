package com.mechanicaleng.item;

import com.mechanicaleng.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch=FetchType.EAGER)
    private UserEntity user;

    @ManyToOne
    private ItemEntity item;


    public LogDto toDto() {
        return LogDto.builder().user(this.getUser().toDto()).item(this.getItem().toDto()).build();
    }

}
