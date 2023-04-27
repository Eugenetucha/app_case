package com.test_case.app.model.dto;

import com.test_case.app.model.entity.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponseDTO {
    List<Model> modelList;
}
