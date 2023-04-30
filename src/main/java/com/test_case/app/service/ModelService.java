package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.dto.ModelDTO;
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
            Optional<Line> lineCurrent = lineRepository.findById(dto.getModelDTO().getLine_id());
            if (lineCurrent.isPresent()) {
                model.setModelName(dto.getModelDTO().getModelName());
                model.setModelSerialNumber(dto.getModelDTO().getModelSerialNumber());
                model.setModelColor(dto.getModelDTO().getModelColor());
                model.setModelSize(dto.getModelDTO().getModelSize());
                model.setModelPrice(dto.getModelDTO().getModelPrice());
                model.setModelAvailability(dto.getModelDTO().isModelAvailability());
                if (lineCurrent.get().getModelList() != null) {
                    model.setLine(lineCurrent.get());
                    Line line = lineCurrent.get();
                    line.getModelList().add(model);
                    modelRepository.save(model);
                    lineRepository.saveAndFlush(line);
                } else {
                    model.setLine(lineCurrent.get());
                    List<Model> modelList = new ArrayList<>();
                    modelList.add(model);
                    Line line = lineCurrent.get();
                    line.setModelList(modelList);
                    modelRepository.save(model);
                    lineRepository.saveAndFlush(line);
                }
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

    //price_l::price_h
    public List<ModelDTO> getListWithParam(String name,
                                           String type,
                                           String color,
                                           String price,
                                           String param) {
        List<ModelDTO> modelDTOS = new ArrayList<>();
        Specification<Model> specification = (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("id"), -1);

        if (name != null) {
            try {
                specification.and(findByFullName(name.trim()));
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        } else {
            if (param != null) {
                specification.and(findSpecModelWithParam(findParameters(param)));
            }
            if (color != null) {
                specification.and(modelCustomSpec.findLike("modelColor", color));
            }
            if (price != null) {
                specification.and(modelCustomSpec.findBetween("modelColor", Integer.parseInt(price.split("::")[0]),
                        Integer.parseInt(price.split("::")[1])));
            }
            if (type != null) {
                specification.and(findByFullName(type));
            }
        }
        for (Model model : modelRepository.findAll(specification)) {
            ModelDTO modelDTO = new ModelDTO();
            modelDTO.setModelName(model.getModelName());
            modelDTO.setModelPrice(model.getModelPrice());
            modelDTO.setModelColor(model.getModelColor());
            modelDTO.setModelSize(model.getModelSize());
            modelDTO.setModelAvailability(model.isModelAvailability());
            modelDTO.setModelSerialNumber(model.getModelSerialNumber());
            List<Long> paramList = new ArrayList<>();
            for (Parameters parameters : model.getParametersList()) {
                paramList.add(parameters.getId());
            }
            modelDTO.setParametersList(paramList);
            modelDTOS.add(modelDTO);
        }
        return modelDTOS;
    }

    private Specification<Model> findByFullName(String line_search) {
        Specification<Model> specification = Specification.where(null);
        try {
            for (Line line : lineRepository.findAllByName(line_search)) {
                specification.and(
                        modelCustomSpec.findEq("line_id", line.getId())
                );
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return specification;
    }


    public Specification<Parameters> findParameters(String param) {
        Specification<Parameters> specification = Specification.where(null);
        for (String key_value : param.split("X")) {
            String key = key_value.split("::")[0];
            String value = key_value.split("::")[1];
            specification.and(
                    parametersCustomSpec.findLike("key", key)
            );
            specification.and(
                    parametersCustomSpec.findLike("value", value)
            );
        }
        return specification;
    }

    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    public Specification<Model> findSpecModelWithParam(Specification<Parameters> specification) {
        Specification<Model> specification2 = Specification.where(null);
        for (Parameters parameters : parametersRepository.findAll(specification)) {
            log.error("param id"+ parameters.getId());
            specification2.and(
                    modelCustomSpec.findEq("model_id", parameters.getModel().getId())
            );
            log.error("modelid"+ parameters.getModel().getId());
        }
        return specification2;
    }
}
