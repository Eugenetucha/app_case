package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import com.test_case.app.repository.LineRepository;
import com.test_case.app.repository.ModelRepository;
import com.test_case.app.repository.ParametersRepository;
import com.test_case.app.util.CustomSpec;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ModelService {
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    LineRepository lineRepository;
    @Autowired
    ParametersRepository parametersRepository;
    @Autowired
    CustomSpec<Model> modelCustomSpec;
    @Autowired
    CustomSpec<Line> lineCustomSpec;
    @Autowired
    CustomSpec<Parameters> parametersCustomSpec;

    public void addModel(Model model, AddDTO dto) {
        try {
            Optional<Line> line_current = lineRepository.findById(dto.getModelDTO().getLine_id());
            if (line_current.isPresent()) {
                model.setModelName(dto.getModelDTO().getModelName());
                model.setModelSerialNumber(dto.getModelDTO().getModelSerialNumber());
                model.setModelColor(dto.getModelDTO().getModelColor());
                model.setModelSize(dto.getModelDTO().getModelSize());
                model.setModelPrice(dto.getModelDTO().getModelPrice());
                model.setModelAvailability(dto.getModelDTO().isModelAvailability());
                if (line_current.get().getModelList() != null) {
                    model.setLine(line_current.get());
                    Line line = line_current.get();
                    line.getModelList().add(model);
                    modelRepository.save(model);
                    lineRepository.saveAndFlush(line);
                } else {
                    model.setLine(line_current.get());
                    List<Model> modelList = new ArrayList<>();
                    modelList.add(model);
                    Line line = line_current.get();
                    line.setModelList(modelList);
                    modelRepository.save(model);
                    lineRepository.saveAndFlush(line);
                }
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

    //key:value X key:value X key:value
    //price_l::price_h
    public List<Model> getListWithParam(String name,
                                        String type,
                                        String color,
                                        String price,
                                        String param) {
        if (name != null) {
            String line_search = name.toLowerCase().trim();
            Specification<Model> specification = findByLineName(line_search);
            return modelRepository.findAll(specification);
        } else {
            Specification<Parameters> specification_param = findParameters(param);
            Specification<Model> specification_model = findSpecModelWithParam(specification_param);
            setBaseParam(color, price, specification_model);
            for (Line line : lineRepository.findByName(type)) {
                specification_model.and(new Specification<Model>() {
                    @Override
                    public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.equal(
                                root.get("line_id"), line.getId());
                    }
                });
            }
            return modelRepository.findAll(specification_model);
        }
    }
    public Specification<Model> findByLineId(String line_search) {
        Specification<Model> specification = null;
        specification = specification.and(null);
        try {
            for (Line line : lineRepository.findByName(line_search)) {
                specification.and(
                        modelCustomSpec.findEq("line_id", line.getId())
                );
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return specification;
    }
    public Specification<Model> findByLineName(String line_search) {
        Specification<Model> specification = null;
        specification = specification.and(null);
        try {
            for (Line line : lineRepository.findByName(line_search)) {
                specification.and(
                        modelCustomSpec.findEq("line_id", line.getId())
                );
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return specification;
    }

    public void setBaseParam(String color,
                             String price, Specification<Model> specification) {
        if (color != null) {
            specification.and(
                    modelCustomSpec.findLike("modelColor", color)
            );
        }
        if (price != null) {
            specification.and(
                    modelCustomSpec.findBetween("modelColor", Integer.parseInt(price.split("::")[0]),
                            Integer.parseInt(price.split("::")[1]))
            );
        }
    }

    public Specification<Parameters> findParameters(String param) {
        Specification<Parameters> specification = null;
        specification = specification.and(null);
        for (String key_value : param.split("X")) {
            String key = key_value.split(":")[0];
            String value = key_value.split(":")[1];
            specification.and(
                    parametersCustomSpec.findLike(key, value)
            );
        }
        return specification;
    }

    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    public Specification<Model> findSpecModelWithParam(Specification<Parameters> specification) {
        Specification<Model> specification2 = null;
        specification2 = specification2.and(null);
        for (Parameters parameters : parametersRepository.findAll(specification)) {
            specification2.and(
                    modelCustomSpec.findEq("model_id", parameters.getModel().getId())
            );
        }
        return specification2;
    }
}
