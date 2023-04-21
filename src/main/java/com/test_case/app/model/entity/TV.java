package com.test_case.app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.entity_model.TVModel;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tv")
public class TV{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long tv_id;
    String name;
    @Column
    String country;
    @Column
    String company;
    @Column
    Boolean onlineTrade;
    @Column
    Boolean credit;
    @OneToMany(mappedBy="c_tv")
    List<TVModel> tvModelList;
}
