package com.test_case.app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "line")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
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
    @OneToMany
    @JoinColumn(name = "line_id")
    List<Model> modelList;
}
