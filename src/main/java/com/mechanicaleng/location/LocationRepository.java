package com.mechanicaleng.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    public List<LocationEntity> findLocationEntitiesByCabinetAndLayer(String Cabinet, Integer Layer);

    public List<LocationEntity> findLocationEntitiesByCabinetLike(String Cabinet);

    public List<LocationEntity> findLocationEntitiesByNameLike(String name);

    public List<LocationEntity> findLocationEntitiesBySerialLike(String serial);
}
