package com.test_case.app.model.entity.dto;

import lombok.Data;

import java.util.List;
@Data
public class ModelDTO {
    private String modelName;
    private int modelSerialNumber;
    private String modelColor;
    private int modelSize;
    private int modelPrice;
    private boolean modelAvailability;
    private List<Long> parametersList;
    private Long line_id;
}
