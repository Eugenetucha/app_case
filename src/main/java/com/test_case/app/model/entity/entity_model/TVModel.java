package com.test_case.app.model.entity.entity_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.TV;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="tv_model")
public class TVModel {
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
    String modelTechnology;
    @Column
    boolean modelAvailability;
    @ManyToOne
    @JoinColumn(name = "tv_id")
    private TV c_tv;
}
