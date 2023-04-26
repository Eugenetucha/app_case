package com.test_case.app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "model")
public class Model {
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
    boolean modelAvailability;
    @OneToMany
    @JoinColumn(name = "model_id")
    List<Parameters> parametersList;

}
