package com.test_case.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.PC;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
