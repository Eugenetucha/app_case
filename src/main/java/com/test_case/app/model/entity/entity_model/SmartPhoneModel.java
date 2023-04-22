package com.test_case.app.model.entity.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.SmartPhone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="smartphone_model")
public class SmartPhoneModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @Column
    String modelName;
    @Column
    int modelSerialNumber;
    @Column
    String modelColor;
    @Column
    int modelSize;
    @Column
    int modelPrice;
    @Column
    int modelMemory;
    @Column
    int modelNumberOfCameras;
    @Column
    boolean modelAvailability;
    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    private SmartPhone c_smartPhone;
}
