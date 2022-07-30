package com.mechanicaleng.location;

import com.mechanicaleng.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, String> {

    public void deleteByIdEquals(Long id);

    public LocationEntity findLocationEntityByIdEquals(Long id);

}
