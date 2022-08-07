package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationEntity;
import com.mechanicaleng.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
	@Autowired
	public ItemRepository itemRepository;

	@Autowired
	public LocationRepository locationRepository;

	//add item

	public void addItem(ItemDto itemDto) {
		ItemEntity itemEntity = ItemEntity.fromDto(itemDto);
		itemRepository.save(itemEntity);
	}

    //delete item

	public void deleteWithId(Long id) {
		itemRepository.deleteById(id);
	}

	//search item
	public ItemDto findItemsWithId(long id) {
		Optional<ItemEntity> item = itemRepository.findItemEntityByIdEquals(id);
		return item.isPresent() ? item.get().toDto() : null;
	}

	public List<ItemDto> findItemsWithSerial(String serial) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialLike(serial);
		return getItemDtos(entities);
	}

	public List<ItemDto> findItemsWithName(String name) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesByNameLike(name);
		return getItemDtos(entities);
    }

	private List<ItemDto> getItemDtos(List<ItemEntity> entities) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		entities.forEach(itemEntity -> {
			itemDtoList.add(itemEntity.toDto());
		});
		return itemDtoList;
    }

	public List<ItemDto> findItemsBySetName(String set) {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySetNameLike(set);
		return getItemDtos(entities);
	}

	//find all items in the same location
	public List<ItemDto> findByLocation(long locationId) {
		LocationEntity location = locationRepository.findLocationEntityByIdEquals(locationId).get();
		List<ItemEntity> entities = itemRepository.findItemEntitiesByLocationEquals(location);
		return getItemDtos(entities);
	}


	//find all damaged items
	public List<ItemDto> findItemsByStatus(StatusEnum status) {
		List<ItemEntity> entities = itemRepository.findAllByStatusEnumEquals(status);
		return getItemDtos(entities);
	}


	public Boolean updateItem(ItemDto itemDto) {
		Optional<ItemEntity> optItemEntity = itemRepository.findById(itemDto.getId());
		if (optItemEntity.isEmpty()) return false;
		ItemEntity itemEntity = optItemEntity.get();
		itemEntity.updateFromDto(itemDto);
        itemRepository.save(itemEntity);
		return true;
    }

	//find all electronics
	public List<ItemDto> findAllElectronics() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("E");
		return getItemDtos(entities);
	}

	//find all electronics acquisition
	public List<ItemDto> findAllElectronicsAcquisition() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQ");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Amp & conditioning
	public List<ItemDto> findAllElectronicsAcquisitionAC() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQA");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Amp & conditioning Amp
	public List<ItemDto> findAllElectronicsAcquisitionACAmp() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQAA");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Amp & conditioning Amp
	public List<ItemDto> findAllElectronicsAcquisitionACConditioning() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQAC");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition DAQ
	public List<ItemDto> findAllElectronicsAcquisitionDAQ() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQD");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition DAQ Chassis
	public List<ItemDto> findAllElectronicsAcquisitionDAQChassis() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQDC");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition DAQ Module
	public List<ItemDto> findAllElectronicsAcquisitionDAQModule() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQDM");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors
	public List<ItemDto> findAllElectronicsAcquisitionSensors() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQS");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors Displacement
	public List<ItemDto> findAllElectronicsAcquisitionSensorsDisplacement() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQSD");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors Velocity
	public List<ItemDto> findAllElectronicsAcquisitionSensorsVelocity() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQSV");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors Acceleration
	public List<ItemDto> findAllElectronicsAcquisitionSensorsAcceleration() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQSA");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors Force
	public List<ItemDto> findAllElectronicsAcquisitionSensorsForce() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQSF");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors Impedance Head
	public List<ItemDto> findAllElectronicsAcquisitionSensorsIH() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQSI");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors Strain Gauge
	public List<ItemDto> findAllElectronicsAcquisitionSensorsSG() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQSS");
		return getItemDtos(entities);
	}

	//find all Electronics Acquisition Sensors Capacitance Sensor
	public List<ItemDto> findAllElectronicsAcquisitionSensorsCS() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EQSC");
		return getItemDtos(entities);
	}


	//find all Electronics Actuation
	public List<ItemDto> findAllElectronicsActuation() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ET");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation AMP
	public List<ItemDto> findAllElectronicsActuationAMP() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETA");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation AMP Motor Drive
	public List<ItemDto> findAllElectronicsActuationAMPMD() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETAM");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation AMP Piezo Amp
	public List<ItemDto> findAllElectronicsActuationAMPPA() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETAP");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation AMP Shaker Amp
	public List<ItemDto> findAllElectronicsActuationAMPSA() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETAS");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation AMP Magnetic Bearing Amp
	public List<ItemDto> findAllElectronicsActuationAMPMBA() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETAB");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation AMP Capacitance Amp
	public List<ItemDto> findAllElectronicsActuationAMPCA() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETAC");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation Actuator
	public List<ItemDto> findAllElectronicsActuationActuator() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETT");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation Actuator Motor
	public List<ItemDto> findAllElectronicsActuationActuatorMotor() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETTM");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation Actuator Piezo
	public List<ItemDto> findAllElectronicsActuationActuatorPiezo() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETTP");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation Actuator Shaker
	public List<ItemDto> findAllElectronicsActuationActuatorShaker() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETTS");
		return getItemDtos(entities);
	}

	//find all Electronics Actuation Actuator Hammer
	public List<ItemDto> findAllElectronicsActuationActuatorHammer() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("ETTH");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop
	public List<ItemDto> findAllElectronicsWorkshop() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EW");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments
	public List<ItemDto> findAllElectronicsWorkshopInstruments() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWI");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Oscilloscope
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsOscilloscope() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIO");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Signal Generator
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsSG() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIS");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Gain Phase Analyser
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsGPA() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIG");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Multimeter
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsMultimeter() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIM");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Power Supply
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsPS() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIP");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Current Probe
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsCP() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIC");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Tachometer
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsTachometer() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIR");
		return getItemDtos(entities);
	}

	//find all Electronics Workshop Instruments Thermometer
	public List<ItemDto> findAllElectronicsWorkshopInstrumentsThermometer() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EWIK");
		return getItemDtos(entities);
	}

	// find all Electronics Computer
	public List<ItemDto> findAllElectronicsComputer() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EC");
		return getItemDtos(entities);
	}

	// find all Electronics Other
	public List<ItemDto> findAllElectronicsOther() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("EO");
		return getItemDtos(entities);
	}

	// find all Mechanical
	public List<ItemDto> findAllMechanical() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("M");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Rig
	public List<ItemDto> findAllMechanicalTR() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MR");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Rig - Permanent Test Rig
	public List<ItemDto> findAllMechanicalTRPTR() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MRP");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Rig - Temporary Test Rig
	public List<ItemDto> findAllMechanicalTRTTR() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MRT");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item
	public List<ItemDto> findAllMechanicalTI() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MI");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item Casing
	public List<ItemDto> findAllMechanicalTICasing() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MIC");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item Blade/Disc
	public List<ItemDto> findAllMechanicalTIBladeDisk() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MIB");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item Rod
	public List<ItemDto> findAllMechanicalTIRod() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MIR");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item Shaft
	public List<ItemDto> findAllMechanicalTIShaft() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MIS");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item Beam
	public List<ItemDto> findAllMechanicalTIBeam() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MIM");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item Bearing
	public List<ItemDto> findAllMechanicalTIBearing() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MIG");
		return getItemDtos(entities);
	}

	// find all Mechanical Test Item Other
	public List<ItemDto> findAllMechanicalTIOther() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MIO");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory
	public List<ItemDto> findAllMechanicalAccessory() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MA");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory Tripod
	public List<ItemDto> findAllMechanicalAccessoryTripod() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MAT");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory Camera Clamp
	public List<ItemDto> findAllMechanicalAccessoryCC() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MAC");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory Mass
	public List<ItemDto> findAllMechanicalAccessoryMass() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MAM");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory Frame
	public List<ItemDto> findAllMechanicalAccessoryFrame() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MAF");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory Piece of Metal
	public List<ItemDto> findAllMechanicalAccessoryPOM() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MAL");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory Mounting Plate
	public List<ItemDto> findAllMechanicalAccessoryMountingPlate() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MAP");
		return getItemDtos(entities);
	}

	// find all Mechanical Accessory Other
	public List<ItemDto> findAllMechanicalAccessoryOther() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("MAO");
		return getItemDtos(entities);
	}

	// find all Tool
	public List<ItemDto> findAllTool() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("T");
		return getItemDtos(entities);
	}

	// find all Tool Soldering Station
	public List<ItemDto> findAllToolSolderingStation() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("TS");
		return getItemDtos(entities);
	}

	// find all Tool Power Tool
	public List<ItemDto> findAllToolPowerTool() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("TP");
		return getItemDtos(entities);
	}

	// find all Tool Toolset
	public List<ItemDto> findAllToolToolset() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("TT");
		return getItemDtos(entities);
	}

	// find all Tool Others
	public List<ItemDto> findAllToolOthers() {
		List<ItemEntity> entities = itemRepository.findItemEntitiesBySerialStartingWith("TO");
		return getItemDtos(entities);
	}























}
