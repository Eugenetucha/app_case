package com.test_case.app.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PCModelDTO {
    String modelName;
    int modelSerialNumber;
    String modelColor;
    int modelSize;
    int modelPrice;
    String modelCategory;
    String modelTypeOfProcessor;
    boolean modelAvailability;
    private Long pc_id;
}
