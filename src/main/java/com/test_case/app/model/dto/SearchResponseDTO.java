package com.test_case.app.model.dto;

import com.test_case.app.model.entity.entity_model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponseDTO {
    List<FridgeModel> fridgeModelList;
    List<HooverModel> hooverModelList;
    List<PCModel> pcModelList;
    List<SmartPhoneModel> smartPhoneModelList;
    List<TVModel> tvModelList;
}
