package com.test_case.app.model.dto;

import com.test_case.app.model.entity.dto.LineDTO;
import com.test_case.app.model.entity.dto.ModelDTO;
import com.test_case.app.model.entity.dto.ParametersDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDTO {
    LineDTO lineDTO;
    ModelDTO modelDTO;
    ParametersDTO parametersDTO;
}
