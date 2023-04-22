package com.test_case.app.model.entity.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.PC;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="pc_model")
public class PCModel {
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
    String modelCategory;
    @Column
    String modelTypeOfProcessor;
    @Column
    boolean modelAvailability;
    @ManyToOne
    @JoinColumn(name = "pc_id")
    private PC c_pc;
}
