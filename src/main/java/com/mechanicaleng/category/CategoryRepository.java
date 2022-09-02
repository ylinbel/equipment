package com.mechanicaleng.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    public List<CategoryEntity> findCategoryEntitiesByParentLayerEnumAndLayer1Like(ParentLayerEnum parentLayerEnum, String layer1);

    @Query("SELECT layer1 FROM category WHERE parentLayerEnum= ?1")
    public List<String> findAllLayer1(ParentLayerEnum layer);
}
