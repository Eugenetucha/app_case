package com.test_case.app.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TVModelDTO {
    String modelName;
    int modelSerialNumber;
    String modelColor;
    int modelSize;
    int modelPrice;
    String modelCategory;
    String modelTechnology;
    boolean modelAvailability;
    private Long tv_id;
}
