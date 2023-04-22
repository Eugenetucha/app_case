package com.test_case.app.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SmartPhoneModelDTO {
    String modelName;
    int modelSerialNumber;
    String modelColor;
    int modelSize;
    int modelPrice;
    int modelMemory;
    int modelNumberOfCameras;
    boolean modelAvailability;
    private Long smartphone_id;
}
