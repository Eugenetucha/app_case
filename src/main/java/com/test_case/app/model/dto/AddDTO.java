package com.test_case.app.model.dto;

import com.test_case.app.model.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDTO {
    TV tv;
    SmartPhone smartPhone;
    PC pc;
    Hoover hoover;
    Fridge fridge;
    TVModelDTO tvModel;
    SmartPhoneModelDTO smartPhoneModel;
    PCModelDTO pcModel;
    HooverModelDTO hooverModel;
    FridgeModelDTO fridgeModel;
}
