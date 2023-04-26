package com.test_case.app.model.dto;

import com.test_case.app.model.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDTO {
    Line line;
    Model model;
    Parameters parameters;
}
