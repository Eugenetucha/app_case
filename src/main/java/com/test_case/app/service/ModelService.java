package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import com.test_case.app.repository.LineRepository;
import com.test_case.app.repository.ModelRepository;
import com.test_case.app.repository.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    LineRepository lineRepository;
    @Autowired
    ParametersRepository parametersRepository;

    public void addModel(Model model, AddDTO dto) {
        model.setModelName(dto.getModelDTO().getModelName());
        model.setModelSerialNumber(dto.getModelDTO().getModelSerialNumber());
        model.setModelColor(dto.getModelDTO().getModelColor());
        model.setModelSize(dto.getModelDTO().getModelSize());
        model.setModelPrice(dto.getModelDTO().getModelPrice());
        model.setModelAvailability(dto.getModelDTO().isModelAvailability());
        if (lineRepository.findById(dto.getModelDTO().getLine_id()).isPresent()) {
            if (lineRepository.findById(dto.getModelDTO().getLine_id()).get().getModelList() != null) {
                model.setLine(lineRepository.findById(dto.getModelDTO().getLine_id()).get());
                Line line = lineRepository.findById(dto.getModelDTO().getLine_id()).get();
                line.getModelList().add(model);
                lineRepository.saveAndFlush(line);
            } else {
                model.setLine(lineRepository.findById(dto.getModelDTO().getLine_id()).get());
                List<Model> modelList = new ArrayList<>();
                modelList.add(model);
                Line line = lineRepository.findById(dto.getModelDTO().getLine_id()).get();
                line.setModelList(modelList);
                lineRepository.saveAndFlush(line);
            }
        }
        modelRepository.save(model);
    }

    //key:value X key:value X key:value
    //price_l::price_h
    public List<Model> getListWithParam(String type,
                                        String color,
                                        String price,
                                        String param) {
        Specification<Parameters> specification = findParameters(param);
        Specification<Model> specification2 = findSpecModelWithParam(specification);
        setBaseParam(color, price, specification2);
        for (Line line : lineRepository.findByName(type)) {
            specification2.and(new Specification<Model>() {
                @Override
                public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(
                            root.get("line_id"), line.getId());
                }
            });
        }
        return modelRepository.findAll(specification2);
    }

    public void setBaseParam(String color,
                             String price, Specification<Model> specification2) {
        if (color != null) {
            specification2.and(new Specification<Model>() {
                @Override
                public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(
                            root.get("modelColor"), color);
                }
            });
        }
        if (price != null) {
            specification2.and(new Specification<Model>() {
                @Override
                public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.between(
                            root.get("modelColor"), price.split("::")[0],
                            price.split("::")[1]);
                }
            });
        }
    }

    public Specification<Parameters> findParameters(String param) {
        Specification<Parameters> specification = new Specification<Parameters>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        for (String key_value : param.split("X")) {
            String key = key_value.split(":")[0];
            String value = key_value.split(":")[1];
            specification.and(new Specification<Parameters>() {
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.like(
                            root.get(key), "%" + value + "%");
                }
            });
        }
        return specification;
    }

    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    public Specification<Model> findSpecModelWithParam(Specification<Parameters> specification) {
        Specification<Model> specification2 = new Specification<Model>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        for (Parameters parameters : parametersRepository.findAll(specification)) {
            specification2.and(new Specification<Model>() {
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(
                            root.get("model_id"), parameters.getModel().getId());
                }
            });
        }
        return specification2;
    }
}
