package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	ItemService itemService;

	@Autowired
	LocationService locationService;

	@PostMapping
	public ResponseEntity<String> addItem(@RequestBody ItemDto itemDto) {
		itemService.addItem(itemDto);
		return ResponseEntity.ok("Success");
	}

	//update item information

	@PutMapping
	public ResponseEntity<String> updateItem(@RequestBody ItemDto itemDto) {
		Boolean result = itemService.updateItem(itemDto);
		if (result) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
		}
	}

	// delete item
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemEntity(@PathVariable Long id) {
		itemService.deleteWithId(id);
		return ResponseEntity.ok("Deleted");
	}

	//search item
	@GetMapping("/{id}")
	public ResponseEntity<ItemDto> findItemWithId(@PathVariable Long id) {
		ItemDto itemDto = itemService.findItemsWithId(id);
		return itemDto != null ? ResponseEntity.ok(itemDto) : ResponseEntity.notFound().build();
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<List<ItemDto>> findItemWithName(@PathVariable String name) {
		List<ItemDto> itemsWithName = itemService.findItemsWithName(name);
		return ResponseEntity.ok(itemsWithName);
	}

	@GetMapping("/find-by-serial/{serial}")
	public ResponseEntity<List<ItemDto>> findItemWithSerial(@PathVariable String serial) {
		List<ItemDto> itemsWithSerial = itemService.findItemsWithSerial(serial);
		return ResponseEntity.ok(itemsWithSerial);
	}

	@GetMapping("/find-by-set/{set}")
	public ResponseEntity<List<ItemDto>> findItemsBySetName(@PathVariable String set) {
		List<ItemDto> itemsBySetName = itemService.findItemsBySetName(set);
		return ResponseEntity.ok(itemsBySetName);
	}

	// find all damaged items
	@GetMapping("/damaged")
	public ResponseEntity<List<ItemDto>> findAllDamagedItems() {
		List<ItemDto> itemsByStatus = itemService.findAllDamagedItems();
		return ResponseEntity.ok(itemsByStatus);
	}

	//find all items in the same location
	@GetMapping("/find-by-location/{location-id}")
	public ResponseEntity<List<ItemDto>> findByLocation(@PathVariable(value = "location-id") long location) {
		List<ItemDto> itemsByLocation = itemService.findByLocation(location);
		return itemsByLocation != null ? ResponseEntity.ok(itemsByLocation) : ResponseEntity.notFound().build();
	}

	// find item by category

	//electronics
	@GetMapping("/electronics")
	public ResponseEntity<List<ItemDto>> findAllElectronics() {
		List<ItemDto> items = itemService.findAllElectronics();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//electronics acquisition
	@GetMapping("/electronics/acquisition")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisition() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisition();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//electronics acquisition amp-and-conditioning
	@GetMapping("/electronics/acquisition/amp-and-conditioning")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionAC() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionAC();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//electronics acquisition amp-and-conditioning Amp
	@GetMapping("/electronics/acquisition/amp-and-conditioning/amp")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionACAmp() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionACAmp();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//electronics acquisition amp-and-conditioning Conditioning
	@GetMapping("/electronics/acquisition/amp-and-conditioning/conditioning")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionACConditioning() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionACConditioning();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//electronics acquisition DAQ
	@GetMapping("/electronics/acquisition/daq")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionDAQ() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionDAQ();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition DAQ Chassis
	@GetMapping("/electronics/acquisition/daq/chassis")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionDAQChassis() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionDAQChassis();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition DAQ Module
	@GetMapping("/electronics/acquisition/daq/module")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionDAQModule() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionDAQModule();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors
	@GetMapping("/electronics/acquisition/sensors")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensors() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensors();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors Displacement
	@GetMapping("/electronics/acquisition/sensors/displacement")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensorsDisplacement() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensorsDisplacement();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors Velocity
	@GetMapping("/electronics/acquisition/sensors/velocity")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensorsVelocity() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensorsVelocity();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors Acceleration
	@GetMapping("/electronics/acquisition/sensors/acceleration")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensorsAcceleration() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensorsAcceleration();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors Force
	@GetMapping("/electronics/acquisition/sensors/force")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensorsForce() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensorsForce();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors Impedance Head
	@GetMapping("/electronics/acquisition/sensors/impedance-head")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensorsIH() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensorsIH();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors Strain Gauge
	@GetMapping("/electronics/acquisition/sensors/strain-gauge")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensorsSG() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensorsSG();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}

	//find all Electronics Acquisition Sensors Capacitance Sensor
	@GetMapping("/electronics/acquisition/sensors/capacitance-sensor")
	public ResponseEntity<List<ItemDto>> findAllElectronicsAcquisitionSensorsCS() {
		List<ItemDto> items = itemService.findAllElectronicsAcquisitionSensorsCS();
		return items != null ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
	}
























}
