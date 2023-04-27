package com.test_case.app.controllers;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.dto.SearchResponseDTO;
import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import com.test_case.app.model.entity.User;
import com.test_case.app.service.LineService;
import com.test_case.app.service.ModelService;
import com.test_case.app.service.ParametersService;
import com.test_case.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UniversalController {
    @Autowired
    private UserService userService;
    @Autowired
    private LineService lineService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ParametersService parametersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Регистрация")
    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@RequestBody User userForm, BindingResult bindingResult, Model model) {

        try {
            userForm.setRole("user");
            userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
            if (userService.loadUserByUsername(userForm.getUsername()) == null) {
                userService.saveUser(userForm);
            } else {
                throw new RuntimeException("user exists already");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Добавить модель,линейку или параметр")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody AddDTO dto) throws RuntimeException {
        if (dto.getLineDTO() != null) {
            try {
                Line line = new Line();
                try {
                    lineService.addLine(line, dto);
                } catch (RuntimeException e) {
                    throw new RuntimeException("Не получилось сохранить линейку");
                }
                lineService.save(line);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getModelDTO() != null) {
            Model model = new Model();
            try {
                modelService.addModel(model, dto);
            } catch (RuntimeException e) {
                throw new RuntimeException("Не получилось сохранить модель");
            }
            modelService.save(model);
        }
        if (dto.getParametersDTO() != null) {
            Parameters parameters = new Parameters();
            try {
                parametersService.addParameters(parameters, dto);
            } catch (RuntimeException e) {
                throw new RuntimeException("Не получилось сохранить параметр");
            }
            parametersService.save(parameters);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получить список моделей")
    @GetMapping("/get/full/")
    public ResponseEntity<SearchResponseDTO> get(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String param) throws RuntimeException {
        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
        try {
            searchResponseDTO.setModelList(modelService.getListWithParam(type, color, price, param));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(searchResponseDTO,HttpStatus.OK);
    }

    @Operation(summary = "Получить список моделей")
    @GetMapping("/get/name")
    public ResponseEntity<SearchResponseDTO> get(@RequestParam(required = true) String full_name) throws RuntimeException {
        SearchResponseDTO dto1 = new SearchResponseDTO();
        String line_search = full_name.toLowerCase().trim();
        try {
            for (Line line : lineService.findByName(full_name)) {
                List<Model> modelList = new ArrayList<>();
                if (line.getModelList() != null) {
                    String model = full_name.replace(line.getName(), "");
                    if (!model.equals("")) {
                        for (Model model1 : line.getModelList()) {
                            if (model1.getModelName().equals(model)) {
                                modelList.add(model1);
                            }
                        }
                    } else {
                        modelList = line.getModelList();
                    }
                    dto1.setModelList(modelList);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }
}