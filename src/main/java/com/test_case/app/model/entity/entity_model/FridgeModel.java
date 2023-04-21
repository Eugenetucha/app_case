package com.test_case.app.model.entity.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.Fridge;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="fridge_model")
public class FridgeModel{
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
    String modelNumberOfDoors;
    @Column
    String modelCompressorType;
    @Column
    boolean modelAvailability;

    @ManyToOne
    @JoinColumn(name = "fridge_id")
    private Fridge c_fridge;

}
