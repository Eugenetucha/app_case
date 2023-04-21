package com.test_case.app.model.dto;

import com.test_case.app.model.entity.entity_model.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
    String type;
    String name;
    String color;
    Integer price_l;
    Boolean sort_num;
    Boolean sort_alphabet;
    Integer price_h;
    TVModel tvModel;
    SmartPhoneModel smartPhoneModel;
    PCModel pcModel;
    HooverModel hooverModel;
    FridgeModel fridgeModel;
}
