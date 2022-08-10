package com.mechanicaleng.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    public LocationRepository locationRepository;

    //add location

    public void addLocation(LocationDto locationDto) {
        LocationEntity location = LocationEntity.fromDto(locationDto);
        locationRepository.save(location);
    }


    //delete location

    public void deleteWithId(long id) {
		locationRepository.deleteById(id);
    }


    //update location

    public Boolean updateLocation(LocationDto locationDto) {
		Optional<LocationEntity> opLocationEntity = locationRepository.findById(locationDto.getId());
        if (opLocationEntity.isEmpty()) return false;
        LocationEntity locationEntity = opLocationEntity.get();
        locationEntity.updateFromDto(locationDto);
        locationRepository.save(locationEntity);
        return true;
    }

    //find by id
    public LocationEntity findById(Long id) {
		Optional<LocationEntity> location = locationRepository.findById(id);
        return location.isPresent() ? location.get() : null;
    }

    // find by cabinet

    public List<LocationDto> findWithCabinet(String cabinet) {
        List<LocationEntity> locations = locationRepository.findLocationEntitiesByCabinetLike(cabinet);
        return getLocationDtos(locations);
    }


    //find by cabinet and layer

    public List<LocationDto> findWithCabinetAndLayer(String cabinet, Integer layer) {
        List<LocationEntity> locations = locationRepository.findLocationEntitiesByCabinetAndLayer(cabinet, layer);
        return getLocationDtos(locations);
    }

    // find by name

    public List<LocationDto> findLocationWithName(String name) {
        List<LocationEntity> locations = locationRepository.findLocationEntitiesByNameLike(name);
        return getLocationDtos(locations);
    }

    // find by serial number

    public List<LocationDto> findLocationWithSerial(String serial) {
        List<LocationEntity> locations = locationRepository.findLocationEntitiesBySerialLike(serial);
        return getLocationDtos(locations);
    }



    private List<LocationDto> getLocationDtos(List<LocationEntity> entities) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        entities.forEach(LocationEntity -> {
            locationDtoList.add(LocationEntity.toDto());
        });
        return locationDtoList;
    }





}
