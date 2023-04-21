package com.test_case.app.model.entity.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.Fridge;

import javax.persistence.*;

import com.test_case.app.model.entity.Hoover;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="hoover_model")
public class HooverModel {
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
    int modelVolume;
    @Column
    int modelNumberOfModes;
    @Column
    boolean modelAvailability;
    @ManyToOne
    @JoinColumn(name = "hoover_id")
    private Hoover c_hoover;
}
