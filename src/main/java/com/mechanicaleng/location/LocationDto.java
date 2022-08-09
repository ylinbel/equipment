package com.mechanicaleng.location;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private Long id;

    private String name;

    private String serial;

    private Integer layer;

    private String cabinet;

}
