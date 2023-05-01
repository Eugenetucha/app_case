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

import static org.springframework.data.jpa.domain.Specification.where;

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

    //price_l::price_hf
    public List<ModelDTO> getListWithParam(String name,
                                           String type,
                                           String color,
                                           String price,
                                           String param) {
        List<ModelDTO> modelDTOS = new ArrayList<>();
        Specification<Model> specification = Specification.where(null);

        if (name != null) {
            specification = where(specification).and(findByFullName(name, specification));
        } else {
            if (type != null) {
                specification = where(specification).and(findByType(type, specification));
                if (param != null) {
                    specification = where(specification).and(findSpecModelWithParam(specification, findParameters(param)));
                }
                if (color != null) {
                    specification = where(specification).and(modelCustomSpec.findLike("modelColor", color));
                }
                if (price != null) {
                    specification = where(specification).and(modelCustomSpec.findBetween("modelPrice", Integer.parseInt(price.split("::")[0]),
                            Integer.parseInt(price.split("::")[1])));
                }
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

    public Specification<Model> findByFullName(String line_search, Specification<Model> specification) {
        for (Line line1 : lineRepository.findAllByName(line_search.split("\\s")[0])) {
            specification = where(specification).or(
                    modelCustomSpec.findEq("line", line1)
            );
        }
        specification = where(specification).and(
                modelCustomSpec.findLike("modelName", line_search.split("\\s")[1])
        );
        return specification;
    }

    public Specification<Model> findByType(String line_search, Specification<Model> specification) {

        for (Line line1 : lineRepository.findAllByName(line_search)) {
            specification = where(specification).or(
                    modelCustomSpec.findEq("line", line1)
            );
        }
        return specification;
    }

    public Specification<Parameters> findParameters(String param) {
        Specification<Parameters> specification = where(null);
        for (String key_value : param.split("X")) {
            String key = key_value.split("::")[0];
            String value = key_value.split("::")[1];
            specification = where(specification).and(
                    parametersCustomSpec.findLike("key", key)
            );
            specification = where(specification).and(
                    parametersCustomSpec.findLike("value", value)
            );
        }
        return specification;
    }

    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    public Specification<Model> findSpecModelWithParam(Specification<Model> specification_model, Specification<Parameters> specification_param) {
        for (Parameters parameters : parametersRepository.findAll(specification_param)) {
            specification_model = where(specification_model).and(
                    modelCustomSpec.findEq("id", parameters.getModel().getId())
            );
        }
        return specification_model;
    }
}
