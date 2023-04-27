package com.test_case.app.model.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
public class LineDTO {
    String name;
    String country;
    String company;
    Boolean onlineTrade;
    Boolean credit;
    List<Long> modelList;
}
