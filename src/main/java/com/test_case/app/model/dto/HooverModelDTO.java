package com.test_case.app.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class HooverModelDTO {

    String modelName;
    int modelSerialNumber;
    String modelColor;
    int modelSize;
    int modelPrice;
    int modelVolume;
    int modelNumberOfModes;
    boolean modelAvailability;
    private Long hoover_id;
}
