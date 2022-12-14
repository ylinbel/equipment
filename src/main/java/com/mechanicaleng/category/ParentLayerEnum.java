package com.mechanicaleng.category;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ParentLayerEnum {
    ELECTRONICS_ACQUISITION("ElectronicsAcquisition"),
    ELECTRONICS_ACTUATION("ElectronicsActuation"),
    ELECTRONICS_WORKSHOP("ElectronicsWorkshop"),
    ELECTRONICS_COMPUTER_AND_OTHER("ElectronicComputerAndOther"),
    MECHANICAL("Mechanical"),
    TOOL("Tool");

    @JsonValue
    @Getter
    private String value;
}
