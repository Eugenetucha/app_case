package com.test_case.app.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
    String type;
    String name;
    String color;
    int price_l;
    boolean sort_num;
    boolean sort_alphabet;
    int price_h;
    TVModelDTO tvModel;
    SmartPhoneModelDTO smartPhoneModel;
    PCModelDTO pcModel;
    HooverModelDTO hooverModel;
    FridgeModelDTO fridgeModel;
}
