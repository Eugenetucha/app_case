package com.test_case.app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test_case.app.model.entity.entity_model.PCModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pc")
public class PC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long pc_id;
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
    @OneToMany(mappedBy = "c_pc")
    List<PCModel> pcModelList;
}
