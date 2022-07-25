package com.mechanicaleng.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    public void deleteByNameAfter(String prefix);

    public List<ItemEntity> findItemEntitiesByName(String name);

    public ItemEntity findItemEntityById(Long id);
}
