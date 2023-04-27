package com.test_case.app.model.entity.dto;

import lombok.Data;

@Data
public class ParametersDTO {
    String key;
    String value;
    private Long model_id;
}
