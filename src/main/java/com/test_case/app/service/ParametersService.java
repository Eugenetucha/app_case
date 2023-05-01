package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import com.test_case.app.repository.ModelRepository;
import com.test_case.app.repository.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametersService {
    @Autowired
    ParametersRepository parametersRepository;
    @Autowired
    ModelRepository modelRepository;
    public List<Parameters> findAll(Specification<Parameters> specification){
        return parametersRepository.findAll(specification);
    }
    public void addParameters(Parameters param, AddDTO dto) {
        if (modelRepository.findById(dto.getParametersDTO().getModel_id()).isPresent()) {
            param.setValue(dto.getParametersDTO().getValue());
            param.setKey(dto.getParametersDTO().getKey());
            param.setModel(modelRepository.findById(dto.getParametersDTO().getModel_id()).get());
            if (modelRepository.findById(dto.getParametersDTO().getModel_id()).get().getParametersList() != null) {
                Model model = modelRepository.findById(dto.getParametersDTO().getModel_id()).get();
                model.getParametersList().add(param);
                parametersRepository.saveAndFlush(param);
                modelRepository.saveAndFlush(model);
            } else {
                Model model = modelRepository.findById(dto.getParametersDTO().getModel_id()).get();
                List<Parameters> parameters = new ArrayList<>();
                parameters.add(param);
                model.setParametersList(parameters);
                parametersRepository.saveAndFlush(param);
                modelRepository.saveAndFlush(model);
            }
        }
    }
}
