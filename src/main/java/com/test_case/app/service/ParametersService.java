package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.entity.Parameters;
import com.test_case.app.repository.ModelRepository;
import com.test_case.app.repository.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametersService {
    @Autowired
    ParametersRepository parametersRepository;
    @Autowired
    ModelRepository modelRepository;

    public void addParameters(Parameters param, AddDTO dto) {
        param.setKey(dto.getParametersDTO().getKey());
        if (modelRepository.findById(dto.getParametersDTO().getModel_id()).isPresent()) {
            param.setModel(modelRepository.findById(dto.getParametersDTO().getModel_id()).get());
        }
        param.setValue(dto.getParametersDTO().getValue());
    }
    public void findByName(String value) {
        parametersRepository.findByName(value);
    }
    public void save(Parameters parameters) {
        parametersRepository.save(parameters);
    }
}
