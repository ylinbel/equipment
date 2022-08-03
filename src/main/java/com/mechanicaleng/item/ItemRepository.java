package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
	@Transactional
    public void deleteByIdEquals(Long id);

    public List<ItemEntity> findItemEntitiesByNameLike(String name);

    public List<ItemEntity> findItemEntitiesBySerialLike(String serial);

    public Optional<ItemEntity> findItemEntityByIdEquals(Long id);

    public List<ItemEntity> findAllByStatusEnumEquals(StatusEnum statusEnum);

    public List<ItemEntity> findItemEntitiesBySetNameLike(String setNum);

    public List<ItemEntity> findItemEntitiesByLocationEquals(Long id);

}
