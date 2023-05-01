package com.test_case.app.service;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.repository.LineRepository;
import com.test_case.app.repository.ModelRepository;
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
public class LineService {
    @Autowired
    LineRepository lineRepository;
    @Autowired
    private ModelRepository modelRepository;

    public void addLine(Line line, AddDTO dto) {
        line.setName(dto.getLineDTO().getName());
        line.setCountry(dto.getLineDTO().getCountry());
        line.setCompany(dto.getLineDTO().getCompany());
        line.setName(dto.getLineDTO().getName());
        line.setOnlineTrade(dto.getLineDTO().getOnlineTrade());
        line.setCredit(dto.getLineDTO().getCredit());
        List<Model> modelList = new ArrayList<>();
        for (Long id : dto.getLineDTO().getModelList()) {
            if (modelRepository.findById(id).isPresent()) {
                modelList.add(modelRepository.findById(id).get());
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
