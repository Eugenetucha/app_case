package com.test_case.app.model.dto;

import com.test_case.app.model.entity.*;
import com.test_case.app.model.entity.entity_model.*;
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
    TVModel tvModel;
    SmartPhoneModel smartPhoneModel;
    PCModel pcModel;
    HooverModel hooverModel;
    FridgeModel fridgeModel;
}
