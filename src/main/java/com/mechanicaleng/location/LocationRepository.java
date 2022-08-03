package com.mechanicaleng.location;

import com.mechanicaleng.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, String> {
    @Transactional
    public void deleteByIdEquals(Long id);

    public Optional<LocationEntity> findLocationEntityByIdEquals(Long id);

    public List<LocationEntity> findLocationEntitiesByCabinetAndLayer(String Cabinet, Integer Layer);

    public List<LocationEntity> findLocationEntitiesByCabinetLike(String Cabinet);

    public List<LocationEntity> findLocationEntitiesByNameLike(String name);

    public List<LocationEntity> findLocationEntitiesBySerialLike(String serial);
}
