package com.mechanicaleng.location;


import com.mechanicaleng.item.ItemDto;
import com.mechanicaleng.item.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String serial;

    private Integer layer;

    private String cabinet;

    public static LocationEntity fromDto(LocationDto locationDto) {
        return LocationEntity.builder().name(locationDto.getName()).serial(locationDto.getSerial()).layer(locationDto.getLayer()).cabinet(locationDto.getCabinet()).build();
    }
}
