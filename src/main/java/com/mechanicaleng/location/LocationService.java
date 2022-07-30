package com.mechanicaleng.location;

import com.mechanicaleng.item.ItemDto;
import com.mechanicaleng.item.ItemEntity;
import com.mechanicaleng.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    public LocationRepository locationRepository;

    //add item

    public void addLocation(LocationDto locationDto) {
        LocationEntity location = LocationEntity.fromDto(locationDto);
        locationRepository.save(location);
    }
}
