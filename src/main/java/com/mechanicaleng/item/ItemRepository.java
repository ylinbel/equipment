package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    public List<ItemEntity> findItemEntitiesByNameLike(String name);

    public List<ItemEntity> findItemEntitiesBySerialLike(String serial);

    public Optional<ItemEntity> findItemEntityByIdEquals(Long id);

    public List<ItemEntity> findAllByStatusEnumEquals(StatusEnum statusEnum);

    public List<ItemEntity> findItemEntitiesBySetNameLike(String setNum);

    public List<ItemEntity> findItemEntitiesByLocationEquals(LocationEntity location);

    public List<ItemEntity> findItemEntitiesByCategoryContaining(String category);

    public List<ItemEntity> findItemEntitiesByCategoryLike(String category);

    public List<ItemEntity> findItemEntitiesBySerialStartingWith(String letters);
}
