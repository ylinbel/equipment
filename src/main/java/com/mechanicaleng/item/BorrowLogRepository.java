package com.mechanicaleng.item;

import com.mechanicaleng.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowLogRepository extends JpaRepository<BorrowLogEntity, Long> {
	public List<BorrowLogEntity> findLogEntitiesByUserEquals(UserEntity user);

	public Optional<BorrowLogEntity> findFirstByItemAndIsReturn(ItemEntity item, Boolean isReturn);

	public List<BorrowLogEntity> findAllByOverDueAndIsReturn(Boolean overDue, Boolean isReturn);
}
