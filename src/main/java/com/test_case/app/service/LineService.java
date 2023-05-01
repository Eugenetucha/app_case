package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LineService {
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private ModelService modelService;
    public void saveAndFlush(Line line){
        lineRepository.saveAndFlush(line);
    }

    public void addLine(Line line, AddDTO dto) {
        line.setName(dto.getLineDTO().getName());
        line.setCountry(dto.getLineDTO().getCountry());
        line.setCompany(dto.getLineDTO().getCompany());
        line.setName(dto.getLineDTO().getName());
        line.setOnlineTrade(dto.getLineDTO().getOnlineTrade());
        line.setCredit(dto.getLineDTO().getCredit());
        List<Model> modelList = new ArrayList<>();
        for (Long id : dto.getLineDTO().getModelList()) {
            if (modelService.findById(id).isPresent()) {
                modelList.add(modelService.findById(id).get());
            }
        }
        line.setModelList(modelList);
    }

    //todo поменять тут и не только чтобы везде кроме самих внтруков сервайса исполдьзовались сервисы
    //проверить на нулевые значения
    public List<Line> findAllByName(String value) {
        return lineRepository.findAllByName(value);
    }

    public Optional<Line> findById(Long id) {
        return lineRepository.findById(id);
    }

    public void save(Line line) {
        lineRepository.save(line);
    }
}
