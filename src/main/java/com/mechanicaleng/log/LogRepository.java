package com.mechanicaleng.log;

import com.mechanicaleng.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
    public List<LogEntity> findLogEntitiesByUserEqualsAndIsCurrentEquals(UserEntity user, Boolean isCurrent);


}