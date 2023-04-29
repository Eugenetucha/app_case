package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import com.test_case.app.repository.ModelRepository;
import com.test_case.app.repository.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametersService {
    @Autowired
    ParametersRepository parametersRepository;
    @Autowired
    ModelRepository modelRepository;

    public void addParameters(Parameters param, AddDTO dto) {
        param.setValue(dto.getParametersDTO().getValue());
        param.setKey(dto.getParametersDTO().getKey());
        if (modelRepository.findById(dto.getParametersDTO().getModel_id()).isPresent()) {
            param.setModel(modelRepository.findById(dto.getParametersDTO().getModel_id()).get());
            if (modelRepository.findById(dto.getParametersDTO().getModel_id()).get().getParametersList() != null) {
                Model model = modelRepository.findById(dto.getParametersDTO().getModel_id()).get();
                model.getParametersList().add(param);
                modelRepository.saveAndFlush(model);
            } else {
                Model model = modelRepository.findById(dto.getParametersDTO().getModel_id()).get();
                List<Parameters> parameters = new ArrayList<>();
                parameters.add(param);
                model.setParametersList(parameters);
                modelRepository.saveAndFlush(model);
            }
        }
        parametersRepository.save(param);
    }
}
