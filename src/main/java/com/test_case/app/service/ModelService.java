package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.dto.ModelDTO;
import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import com.test_case.app.repository.ModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
public class ModelService {
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    LineService lineService;
    @Autowired
    ParametersService parametersService;
    @Autowired
    CustomSpecification<Model> modelCustomSpec;
    @Autowired
    CustomSpecification<Line> lineCustomSpec;
    @Autowired
    CustomSpecification<Parameters> parametersCustomSpec;

    public void saveAndFlush(Model model) {
        modelRepository.saveAndFlush(model);
    }

    public void addModel(Model model, AddDTO dto) {
        try {
            Optional<Line> lineCurrent = lineService.findById(dto.getModelDTO().getLine_id());
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
                    lineService.saveAndFlush(line);
                } else {
                    model.setLine(lineCurrent.get());
                    List<Model> modelList = new ArrayList<>();
                    modelList.add(model);
                    Line line = lineCurrent.get();
                    line.setModelList(modelList);
                    modelRepository.save(model);
                    lineService.saveAndFlush(line);
                }
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

    //todo переписать readme и сделать миграцию данных
    //price_l::price_hf
    public List<ModelDTO> getListWithParam(String name,
                                           String type,
                                           String color,
                                           String price,
                                           String param,
                                           Boolean sortNumbers,
                                           Boolean sortLetters) {
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
        if (sortNumbers != null) {
            if (sortNumbers) {
                modelDTOS.sort(Comparator.comparingInt(ModelDTO::getModelPrice));
            } else {
                modelDTOS.sort(Comparator.comparingInt(ModelDTO::getModelPrice).reversed());
            }
        }
        if (sortLetters != null) {
            if (sortLetters) {
                modelDTOS.sort(Comparator.comparing(ModelDTO::getModelName));
            } else {
                modelDTOS.sort(Comparator.comparing(ModelDTO::getModelName).reversed());
            }
        }
        return modelDTOS;
    }

    public Specification<Model> findByFullName(String line_search, Specification<Model> specification) {
        try {
            for (Line line1 : lineService.findAllByName(line_search.split("\\s")[0])) {
                specification = where(specification).or(
                        modelCustomSpec.findEq("line", line1)
                );
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось найти линейки с таким именем");
        }
        if (line_search.split("\\s").length > 1) {
            specification = where(specification).and(
                    modelCustomSpec.findLike("modelName", line_search.split("\\s")[1])
            );
        }
        return specification;
    }

    public Specification<Model> findByType(String line_search, Specification<Model> specification) {
        try {
            for (Line line1 : lineService.findAllByName(line_search)) {
                specification = where(specification).or(
                        modelCustomSpec.findEq("line", line1)
                );
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось найти линейки с таким именем");
        }
        return specification;
    }

    //key_one::value_oneXkey_two::value_two
    public List<Parameters> findParameters(String param) {
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
        return parametersService.findAll(specification);
    }

    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    public Specification<Model> findSpecModelWithParam(Specification<Model> specification_model, List<Parameters> parameters) {
        try {
            for (Parameters parameter : parameters) {
                specification_model = where(specification_model).or(
                        modelCustomSpec.findEq("id", parameter.getModel().getId())
                );
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось найти нужные параметры");
        }
        return specification_model;
    }
}
