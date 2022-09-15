package com.mechanicaleng.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public List<UserEntity> findUserEntitiesByNameLike(String name);

	public Optional<UserEntity> findUserEntityByName(String name);

	public List<UserEntity> findUserEntitiesByUserTypeEnumEquals(UserTypeEnum type);

	public List<UserEntity> findAll();

	public UserEntity findUserEntityByEmailEquals(String email);
}
