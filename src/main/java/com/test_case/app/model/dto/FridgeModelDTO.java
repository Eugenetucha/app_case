package com.test_case.app.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class FridgeModelDTO {
    String modelName;
    int modelSerialNumber;
    String modelColor;
    int modelSize;
    int modelPrice;
    String modelNumberOfDoors;
    String modelCompressorType;
    boolean modelAvailability;
    private Long fridge_id;

}
