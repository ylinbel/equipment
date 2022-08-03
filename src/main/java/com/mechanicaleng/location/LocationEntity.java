package com.mechanicaleng.location;


import com.mechanicaleng.item.ItemDto;
import com.mechanicaleng.item.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String serial;

    private Integer layer;

    private String cabinet;

    @OneToMany(targetEntity = ItemEntity.class, mappedBy = "location")
    private List<ItemEntity> items;

    public static LocationEntity fromDto(LocationDto locationDto) {
        return LocationEntity.builder().name(locationDto.getName()).serial(locationDto.getSerial()).layer(locationDto.getLayer()).cabinet(locationDto.getCabinet()).items(locationDto.getItems()).build();
    }

    public LocationDto toDto() {
        return LocationDto.builder().name(this.getName()).serial(this.getSerial()).layer(this.getLayer()).cabinet(this.getCabinet()).items(this.getItems()).build();
    }

    public void updateFromDto(LocationDto locationDto) {
        this.setName(locationDto.getName());
        this.setSerial(locationDto.getSerial());
        this.setCabinet(locationDto.getCabinet());
        this.setLayer(locationDto.getLayer());
        this.setItems(locationDto.getItems());
    }
}
