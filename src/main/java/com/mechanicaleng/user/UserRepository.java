package com.mechanicaleng.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findUserEntitiesByNameLike(String name);

    public List<UserEntity> findUserEntitiesByUserTypeEnumEquals(UserTypeEnum type);

    public List<UserEntity> findAll();
}
