package com.mechanicaleng.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {

    public void deleteByNameAfter(String prefix);

    public void deleteBySerial(String serial);

    public void deleteById(Long id);

    public List<ItemEntity> findItemEntitiesByName(String name);

    public List<ItemEntity> findItemEntitiesBySerial(String serial);

    public ItemEntity findItemEntityById(Long id);

    public List<ItemEntity> findAllByStatusEquals(Status status);


}
