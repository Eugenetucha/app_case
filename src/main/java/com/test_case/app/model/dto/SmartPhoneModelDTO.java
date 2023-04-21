package com.test_case.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.SmartPhone;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
