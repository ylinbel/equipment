package com.mechanicaleng.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {

    public void deleteByNameEquals(String prefix);

    public void deleteBySerialEquals(String serial);

    public void deleteByIdEquals(Long id);

    public List<ItemEntity> findItemEntitiesByNameLike(String name);

    public List<ItemEntity> findItemEntitiesBySerialLike(String serial);

    public ItemEntity findItemEntityByIdEquals(Long id);

    public List<ItemEntity> findAllByStatusEquals(StatusEnum statusEnum);

    public List<ItemEntity> findItemEntitiesBySetLike(String set);


}
