package com.test_case.app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.entity_model.FridgeModel;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fridge")
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long fridge_id;
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
    @OneToMany(mappedBy = "c_fridge")
    List<FridgeModel> fridgeModelList;
}
