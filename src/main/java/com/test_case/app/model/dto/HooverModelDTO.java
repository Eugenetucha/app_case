package com.test_case.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.Hoover;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
