package com.mechanicaleng.item;

import com.mechanicaleng.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrow_log", indexes = {
        @Index(name = "borrowKey", columnList = "overDue,isReturn")
})
public class BorrowLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch=FetchType.EAGER)
    private UserEntity user;

    @ManyToOne
    private ItemEntity item;

    private LocalDateTime borrowTime;

    private LocalDateTime returnTime;

    private LocalDateTime overDueTime;

    private Boolean overDue = Boolean.FALSE;

    private Boolean isReturn = Boolean.FALSE;


    public BorrowLogDto toDto() {
        return BorrowLogDto.builder().id(this.getId()).user(this.getUser().toDto()).item(this.getItem().toDto()).startTime(this.getBorrowTime()).overDue(this.getOverDue()).build();
    }

}
