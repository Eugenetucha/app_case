package com.test_case.app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.entity_model.SmartPhoneModel;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="smartphone")
public class SmartPhone{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long smartphone_id;
    @Column
    String name;
    @Column
    String country;
    @Column
    String company;
    @Column
    Boolean onlineTrade;
    @Column
    Boolean credit;
    @OneToMany(mappedBy ="c_smartPhone")
    List<SmartPhoneModel> smartPhoneModelList;
}
