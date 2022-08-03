package com.mechanicaleng.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    //add location

    @PostMapping
    public ResponseEntity<String> addLocation(@RequestBody LocationDto locationDto) {
        locationService.addLocation(locationDto);
        return ResponseEntity.ok("success");
    }

    //update location

    @PutMapping
    public ResponseEntity<String> updateLocation(@RequestBody LocationDto locationDto) {
        Boolean result = locationService.updateLocation(locationDto);
        if (result) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    //delete location

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemEntity(@PathVariable Long id) {
        locationService.deleteWithId(id);
        return ResponseEntity.ok("Deleted");
    }

    //find location

    @GetMapping("find-by-cabinet/{cabinet}")
    public ResponseEntity<List<LocationDto>> findWithCabinet(@PathVariable String cabinet) {
        List<LocationDto> locationByCabinet = locationService.findWithCabinet(cabinet);
        return ResponseEntity.ok(locationByCabinet);
    }

    @GetMapping("find-by-cabinet-and-layer/{cabinet}/{layer}")
    public ResponseEntity<List<LocationDto>> findWithCabinetAndLayer(@PathVariable String cabinet, @PathVariable Integer layer) {
        List<LocationDto> locationByCabinetAndLayer = locationService.findWithCabinetAndLayer(cabinet, layer);
        return ResponseEntity.ok(locationByCabinetAndLayer);
    }

    @GetMapping("find-by-serial/{serial}")
    public ResponseEntity<List<LocationDto>> findBySerial(@PathVariable String serial) {
        List<LocationDto> locationBySerial = locationService.findLocationWithSerial(serial);
        return ResponseEntity.ok(locationBySerial);
    }

    @GetMapping("find-by-name/{name}")
    public ResponseEntity<List<LocationDto>> findByName(@PathVariable String name) {
        List<LocationDto> locationByName = locationService.findLocationWithName(name);
        return ResponseEntity.ok(locationByName);
    }

}
