package com.test_case.app.model.dto;

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
    TVModelDTO tvModel;
    SmartPhoneModelDTO smartPhoneModel;
    PCModelDTO pcModel;
    HooverModelDTO hooverModel;
    FridgeModelDTO fridgeModel;
}
