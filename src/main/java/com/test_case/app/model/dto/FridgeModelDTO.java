package com.test_case.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.Fridge;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
