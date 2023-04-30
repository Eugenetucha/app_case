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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView addUser(@ModelAttribute("employee") User user) {

        try {
            user.setRole("user");
            user.setAccountNonLocked(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (userService.loadUserByUsername(user.getUsername()) == null) {
                userService.saveUser(user);
            } else {
                throw new RuntimeException("user exists already");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return new RedirectView("/login");
    }

    //todo тут окончательно перенести логику в сервис и все покрыть исключениями и убрать не только логику но и исключения внутрь
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
        }
        if (dto.getParametersDTO() != null) {
            Parameters parameters = new Parameters();
            try {
                parametersService.addParameters(parameters, dto);
            } catch (RuntimeException e) {
                throw new RuntimeException("Не получилось сохранить параметр");
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:8080/get/full/?type=112&color=Jon
    @Operation(summary = "Получить список моделей")
    @GetMapping("/get/full/")
    public ResponseEntity<SearchResponseDTO> get(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String param) throws RuntimeException {
        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
        searchResponseDTO.setModelList(modelService.getListWithParam(name, type, color, price, param));
        return new ResponseEntity<>(searchResponseDTO, HttpStatus.OK);
    }
}