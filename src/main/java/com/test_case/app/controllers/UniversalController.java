package com.test_case.app.controllers;

import com.test_case.app.model.KeyOfOperations;
import com.test_case.app.model.TypeOfOperations;
import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.dto.SearchDTO;
import com.test_case.app.model.dto.SearchResponseDTO;
import com.test_case.app.model.entity.*;
import com.test_case.app.model.entity.entity_model.*;
import com.test_case.app.repository.*;
import com.test_case.app.repository.model_repository.*;
import com.test_case.app.service.UserService;
import com.test_case.app.util.specification.ModelCriteria;
import com.test_case.app.util.specification.ModelSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
public class UniversalController {
    @Autowired
    PCRepository pcRepository;
    @Autowired
    HooverRepository hooverRepository;
    @Autowired
    FridgeRepository fridgeRepository;
    @Autowired
    SmartPhoneRepository smartPhoneRepository;
    @Autowired
    TVRepository tvRepository;
    @Autowired
    PCModelRepository pcModelRepository;
    @Autowired
    HooverModelRepository hooverModelRepository;
    @Autowired
    FridgeModelRepository fridgeModelRepository;
    @Autowired
    SmartPhoneModelRepository smartPhoneModelRepository;
    @Autowired
    TVModelRepository tvModelRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        try {
            userService.saveUser(userForm);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody AddDTO dto) throws RuntimeException {
        if (dto.getFridge() != null) {
            try {
                fridgeRepository.save(dto.getFridge());
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getFridgeModel() != null) {
            FridgeModel fridgeModel = new FridgeModel();
            if (fridgeRepository.findById(dto.getFridgeModel().getFridge_id()).isPresent()) {
                fridgeModel.setC_fridge(fridgeRepository.findById(dto.getFridgeModel().getFridge_id()).get());
                
                if (fridgeRepository.findById(dto.getFridgeModel().getFridge_id()).get().getFridgeModelList() != null) {
                    fridgeRepository.findById(dto.getFridgeModel().getFridge_id()).get().getFridgeModelList().add(fridgeModel);
                } else {
                    fridgeRepository.findById(dto.getFridgeModel().getFridge_id()).get().setFridgeModelList(new ArrayList<>());
                    fridgeRepository.findById(dto.getFridgeModel().getFridge_id()).get().getFridgeModelList().add(fridgeModel);
                }
            }
            fridgeModel.setModelName(dto.getFridgeModel().getModelName());
            fridgeModel.setModelSerialNumber(dto.getFridgeModel().getModelSerialNumber());
            fridgeModel.setModelColor(dto.getFridgeModel().getModelColor());
            fridgeModel.setModelSize(dto.getFridgeModel().getModelSize());
            fridgeModel.setModelPrice(dto.getFridgeModel().getModelPrice());
            fridgeModel.setModelNumberOfDoors(dto.getFridgeModel().getModelNumberOfDoors());
            fridgeModel.setModelCompressorType(dto.getFridgeModel().getModelCompressorType());
            fridgeModel.setModelAvailability(dto.getFridgeModel().isModelAvailability());
            fridgeModelRepository.save(fridgeModel);
        }
        if (dto.getHoover() != null) {
            try {
                hooverRepository.save(dto.getHoover());
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getHooverModel() != null) {
            try {
                HooverModel model = new HooverModel();
                if (hooverRepository.findById(dto.getHooverModel().getHoover_id()).isPresent()) {
                    model.setC_hoover(hooverRepository.findById(dto.getHooverModel().getHoover_id()).get());
                    
                    if (hooverRepository.findById(dto.getHooverModel().getHoover_id()).get().getHooverModelList() != null) {
                        hooverRepository.findById(dto.getHooverModel().getHoover_id()).get().getHooverModelList().add(model);
                    } else {
                        hooverRepository.findById(dto.getHooverModel().getHoover_id()).get().setHooverModelList(new ArrayList<>());
                        hooverRepository.findById(dto.getHooverModel().getHoover_id()).get().getHooverModelList().add(model);
                    }
                }
                model.setModelName(dto.getFridgeModel().getModelName());
                model.setModelSerialNumber(dto.getFridgeModel().getModelSerialNumber());
                model.setModelColor(dto.getFridgeModel().getModelColor());
                model.setModelSize(dto.getFridgeModel().getModelSize());
                model.setModelPrice(dto.getFridgeModel().getModelPrice());
                model.setModelNumberOfModes(dto.getHooverModel().getModelNumberOfModes());
                model.setModelVolume(dto.getHooverModel().getModelVolume());
                model.setModelAvailability(dto.getFridgeModel().isModelAvailability());
                hooverModelRepository.save(model);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getTv() != null) {
            try {
                tvRepository.save(dto.getTv());
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getTvModel() != null) {
            try {
                TVModel model = new TVModel();
                if (tvRepository.findById(dto.getTvModel().getTv_id()).isPresent()) {
                    model.setC_tv(tvRepository.findById(dto.getTvModel().getTv_id()).get());
                    
                    if (tvRepository.findById(dto.getTvModel().getTv_id()).get().getTvModelList() != null) {
                        tvRepository.findById(dto.getTvModel().getTv_id()).get().getTvModelList().add(model);
                    } else {
                        tvRepository.findById(dto.getTvModel().getTv_id()).get().setTvModelList(new ArrayList<>());
                        tvRepository.findById(dto.getTvModel().getTv_id()).get().getTvModelList().add(model);
                    }
                }
                model.setModelName(dto.getTvModel().getModelName());
                model.setModelSerialNumber(dto.getTvModel().getModelSerialNumber());
                model.setModelColor(dto.getTvModel().getModelColor());
                model.setModelSize(dto.getTvModel().getModelSize());
                model.setModelPrice(dto.getTvModel().getModelPrice());
                model.setModelCategory(dto.getTvModel().getModelCategory());
                model.setModelTechnology(dto.getTvModel().getModelTechnology());
                model.setModelAvailability(dto.getTvModel().isModelAvailability());
                tvModelRepository.save(model);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getPc() != null) {
            try {
                pcRepository.save(dto.getPc());
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getPcModel() != null) {
            try {
                PCModel model = new PCModel();
                if (pcRepository.findById(dto.getPcModel().getPc_id()).isPresent()) {
                    model.setC_pc(pcRepository.findById(dto.getPcModel().getPc_id()).get());
                    
                    if (pcRepository.findById(dto.getPcModel().getPc_id()).get().getPcModelList() != null) {
                        pcRepository.findById(dto.getPcModel().getPc_id()).get().getPcModelList().add(model);
                    } else {
                        pcRepository.findById(dto.getPcModel().getPc_id()).get().setPcModelList(new ArrayList<>());
                        pcRepository.findById(dto.getPcModel().getPc_id()).get().getPcModelList().add(model);
                    }
                }
                model.setModelName(dto.getPcModel().getModelName());
                model.setModelSerialNumber(dto.getPcModel().getModelSerialNumber());
                model.setModelColor(dto.getPcModel().getModelColor());
                model.setModelSize(dto.getPcModel().getModelSize());
                model.setModelPrice(dto.getPcModel().getModelPrice());
                model.setModelCategory(dto.getPcModel().getModelCategory());
                model.setModelTypeOfProcessor(dto.getPcModel().getModelTypeOfProcessor());
                model.setModelAvailability(dto.getPcModel().isModelAvailability());
                pcModelRepository.save(model);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getSmartPhone() != null) {
            try {
                smartPhoneRepository.save(dto.getSmartPhone());
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        if (dto.getSmartPhoneModel() != null) {
            try {
                SmartPhoneModel model = new SmartPhoneModel();
                if (smartPhoneRepository.findById(dto.getSmartPhoneModel().getSmartphone_id()).isPresent()) {
                    model.setC_smartPhone(smartPhoneRepository.findById(dto.getSmartPhoneModel().getSmartphone_id()).get());
                    
                    if (smartPhoneRepository.findById(dto.getSmartPhoneModel().getSmartphone_id()).get().getSmartPhoneModelList() != null) {
                        smartPhoneRepository.findById(dto.getSmartPhoneModel().getSmartphone_id()).get().getSmartPhoneModelList().add(model);
                    } else {
                        smartPhoneRepository.findById(dto.getSmartPhoneModel().getSmartphone_id()).get().setSmartPhoneModelList(new ArrayList<>());
                        smartPhoneRepository.findById(dto.getSmartPhoneModel().getSmartphone_id()).get().getSmartPhoneModelList().add(model);
                    }
                }
                model.setModelName(dto.getSmartPhoneModel().getModelName());
                model.setModelSerialNumber(dto.getSmartPhoneModel().getModelSerialNumber());
                model.setModelColor(dto.getSmartPhoneModel().getModelColor());
                model.setModelSize(dto.getSmartPhoneModel().getModelSize());
                model.setModelPrice(dto.getSmartPhoneModel().getModelPrice());
                model.setModelMemory(dto.getSmartPhoneModel().getModelMemory());
                model.setModelNumberOfCameras(dto.getSmartPhoneModel().getModelNumberOfCameras());
                model.setModelAvailability(dto.getSmartPhoneModel().isModelAvailability());
                smartPhoneModelRepository.save(model);
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<SearchResponseDTO> get(@RequestBody SearchDTO dto) throws RuntimeException {
        //todo работает все кроме поиска по имени и сделать тестовые данные для дампа и написать ридми
        SearchResponseDTO dto1 = new SearchResponseDTO();
        if (dto.getName() != null) {
            String line = dto.getName().toLowerCase().trim();
            try {
                for (Fridge fridge : fridgeRepository.findByName(line)) {
                    List<FridgeModel> fridgeModelList = new ArrayList<>();
                    if (fridge.getFridgeModelList() != null) {
                        String model = line.replace(fridge.getName(), "");
                        if (!model.equals("")) {
                            for (FridgeModel fridgeModel : fridge.getFridgeModelList()) {
                                if (fridgeModel.getModelName().equals(model)) {
                                    fridgeModelList.add(fridgeModel);
                                }
                            }
                        } else {
                            fridgeModelList = fridge.getFridgeModelList();
                        }
                        dto1.setFridgeModelList(fridgeModelList);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            try {
                for (Hoover hoover : hooverRepository.findByName(line)) {
                    List<HooverModel> hooverModelList = new ArrayList<>();
                    if (hoover.getHooverModelList() != null) {
                        String model = line.replace(hoover.getName(), "");
                        if (!model.equals("")) {
                            for (HooverModel hooverModel : hoover.getHooverModelList()) {
                                if (hooverModel.getModelName().equals(model)) {
                                    hooverModelList.add(hooverModel);
                                }
                            }
                        } else {
                            hooverModelList = hoover.getHooverModelList();
                        }
                        dto1.setHooverModelList(hooverModelList);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            try {
                for (PC PC : pcRepository.findByName(line)) {
                    List<PCModel> pcModelList = new ArrayList<>();
                    if (PC.getPcModelList() != null) {
                        String model = line.replace(PC.getName(), "");
                        if (!model.equals("")) {
                            for (PCModel PCModel : PC.getPcModelList()) {
                                if (PCModel.getModelName().equals(model)) {
                                    pcModelList.add(PCModel);
                                }
                            }
                        } else {
                            pcModelList = PC.getPcModelList();
                        }
                    }
                    dto1.setPcModelList(pcModelList);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            try {
                for (SmartPhone smartPhone : smartPhoneRepository.findByName(line)) {
                    List<SmartPhoneModel> smartPhoneModelList = new ArrayList<>();
                    if (smartPhone.getSmartPhoneModelList() != null) {
                        String model = line.replace(smartPhone.getName(), "");
                        if (!model.equals("")) {
                            for (SmartPhoneModel smartPhoneModel : smartPhone.getSmartPhoneModelList()) {
                                if (smartPhoneModel.getModelName().equals(model)) {
                                    smartPhoneModelList.add(smartPhoneModel);
                                }
                            }
                        } else {
                            smartPhoneModelList = smartPhone.getSmartPhoneModelList();
                        }
                        dto1.setSmartPhoneModelList(smartPhoneModelList);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            try {
                for (TV tv : tvRepository.findByName(line)) {
                    List<TVModel> tvModelList = new ArrayList<>();
                    if (tv.getTvModelList() != null) {
                        String model = line.replace(tv.getName(), "");
                        if (!model.equals("")) {
                            for (TVModel tvModel : tv.getTvModelList()) {
                                if (tvModel.getModelName().equals(model)) {
                                    tvModelList.add(tvModel);
                                }
                            }
                        } else {
                            tvModelList = tv.getTvModelList();
                        }
                    }
                    dto1.setTvModelList(tvModelList);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            if (dto.getType() != null) {
                switch (dto.getType()) {
                    case "TV": {
                        ModelSpecification<TVModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_COLOR.getValue(), dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_1.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_2.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_NAME.getValue(), dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getTvModel() != null) {
                            if (dto.getTvModel().getModelSerialNumber() != 0) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SERIAL_NUMBER.getValue(), String.valueOf(dto.getTvModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().getModelSize() != 0) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SIZE.getValue(), String.valueOf(dto.getTvModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().isModelAvailability()) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.BOOLEAN.toString(), KeyOfOperations.MODEL_AVAILABILITY.getValue(), String.valueOf(true)));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().getModelCategory() != null) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), "modelCategory", String.valueOf(dto.getTvModel().getModelCategory())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().getModelTechnology() != null) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), "modelTechnology", String.valueOf(dto.getTvModel().getModelTechnology())));
                                specification.and(modelSpecification);
                            }
                        }
                        if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                            List<TVModel> tvModels = new ArrayList<>();
                            for (TVModel tvModel : tvModelRepository.findAll()) {
                                tvModel.setC_tv(null);
                                tvModels.add(tvModel);
                            }
                            dto1.setTvModelList(tvModels);
                        } else {
                            List<TVModel> smartPhoneModelList = tvModelRepository.findAll(specification);
                            if (dto.isSort_num()) {
                                smartPhoneModelList.sort(Comparator.comparingInt(TVModel::getModelPrice));
                            } else {
                                smartPhoneModelList.sort(Comparator.comparing(TVModel::getModelName));
                            }
                            dto1.setTvModelList(smartPhoneModelList);
                        }
                        new ResponseEntity<>(dto1, HttpStatus.OK);
                    }
                    case "Fridge": {
                        try {
                            ModelSpecification<FridgeModel> specification = new ModelSpecification<>(null);
                            if (dto.getColor() != null) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_COLOR.getValue(), dto.getColor()));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPrice_l() != 0) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_1.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_l())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPrice_h() != 0) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_2.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_h())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getName() != null) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_NAME.getValue(), dto.getName().toLowerCase()));
                                specification.and(modelSpecification);
                            }
                            if (dto.getFridgeModel() != null) {
                                if (dto.getFridgeModel().getModelSerialNumber() != 0) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SERIAL_NUMBER.getValue(), String.valueOf(dto.getFridgeModel().getModelSerialNumber())));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().getModelSize() != 0) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SIZE.getValue(), String.valueOf(dto.getFridgeModel().getModelSize())));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().isModelAvailability()) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.BOOLEAN.toString(), KeyOfOperations.MODEL_AVAILABILITY.getValue(), String.valueOf(true)));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().getModelNumberOfDoors() != null) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), "modelNumberOfDoors", dto.getFridgeModel().getModelNumberOfDoors()));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().getModelCompressorType() != null) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), "modelCompressorType", dto.getFridgeModel().getModelCompressorType()));
                                    specification.and(modelSpecification);
                                }
                            }
                            List<FridgeModel> smartPhoneModelList;
                            if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                                smartPhoneModelList = new ArrayList<>();
                                for (FridgeModel fridgeModel : fridgeModelRepository.findAll()) {
                                    fridgeModel.setC_fridge(null);
                                    smartPhoneModelList.add(fridgeModel);
                                }
                            } else {
                                smartPhoneModelList = fridgeModelRepository.findAll(specification);
                                if (dto.isSort_num()) {
                                    smartPhoneModelList.sort(Comparator.comparingInt(FridgeModel::getModelPrice));
                                } else {
                                    smartPhoneModelList.sort(Comparator.comparing(FridgeModel::getModelName));
                                }
                            }
                            dto1.setFridgeModelList(smartPhoneModelList);
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                        new ResponseEntity<>(dto1, HttpStatus.OK);
                    }
                    case "Hoover": {
                        ModelSpecification<HooverModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_COLOR.getValue(), dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_1.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_2.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_NAME.getValue(), dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getHooverModel() != null) {
                            if (dto.getHooverModel().getModelSerialNumber() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SERIAL_NUMBER.getValue(), String.valueOf(dto.getHooverModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().getModelSize() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SIZE.getValue(), String.valueOf(dto.getHooverModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().isModelAvailability()) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.BOOLEAN.toString(), KeyOfOperations.MODEL_AVAILABILITY.getValue(), String.valueOf(true)));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().getModelNumberOfModes() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), "modelNumberOfModes", String.valueOf(dto.getHooverModel().getModelNumberOfModes())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().getModelVolume() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), "modelVolume", String.valueOf(dto.getHooverModel().getModelVolume())));
                                specification.and(modelSpecification);
                            }
                        }
                        if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                            List<HooverModel> hooverModelList = new ArrayList<>();
                            for (HooverModel hooverModel : hooverModelRepository.findAll()) {
                                hooverModel.setC_hoover(null);
                                hooverModelList.add(hooverModel);
                            }
                            dto1.setHooverModelList(hooverModelList);
                        } else {
                            List<HooverModel> smartPhoneModelList = hooverModelRepository.findAll(specification);
                            if (dto.isSort_num()) {
                                smartPhoneModelList.sort(Comparator.comparingInt(HooverModel::getModelPrice));
                            } else {

                                smartPhoneModelList.sort(Comparator.comparing(HooverModel::getModelName));
                            }
                            dto1.setHooverModelList(smartPhoneModelList);
                        }
                        new ResponseEntity<>(dto1, HttpStatus.OK);
                    }
                    case "PC": {
                        ModelSpecification<PCModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_COLOR.getValue(), dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_1.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_2.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_NAME.getValue(), dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPcModel() != null) {
                            if (dto.getPcModel().getModelSerialNumber() != 0) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SERIAL_NUMBER.getValue(), String.valueOf(dto.getPcModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPcModel().getModelSize() != 0) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SIZE.getValue(), String.valueOf(dto.getPcModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPcModel().isModelAvailability()) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.BOOLEAN.toString(), KeyOfOperations.MODEL_AVAILABILITY.getValue(), String.valueOf(true)));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPcModel().getModelCategory() != null) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("STRING", "modelCategory", dto.getPcModel().getModelCategory()));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPcModel().getModelTypeOfProcessor() != null) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("STRING", "modelTypeOfProcessor", dto.getPcModel().getModelTypeOfProcessor()));
                                specification.and(modelSpecification);
                            }
                        }
                        if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                            List<PCModel> pcModels = new ArrayList<>();
                            for (PCModel pcModel : pcModelRepository.findAll()) {
                                pcModel.setC_pc(null);
                                pcModels.add(pcModel);
                            }
                            dto1.setPcModelList(pcModels);
                        } else {
                            List<PCModel> smartPhoneModelList = pcModelRepository.findAll(specification);
                            if (dto.isSort_num()) {
                                smartPhoneModelList.sort(Comparator.comparingInt(PCModel::getModelPrice));
                            } else {
                                smartPhoneModelList.sort(Comparator.comparing(PCModel::getModelName));
                            }
                            dto1.setPcModelList(smartPhoneModelList);
                        }
                        new ResponseEntity<>(dto1, HttpStatus.OK);
                    }
                    case "SmartPhone": {
                        ModelSpecification<SmartPhoneModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_COLOR.getValue(), dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_1.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT_CHECK_2.toString(), KeyOfOperations.MODEL_PRICE.getValue(), String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.VARCHAR.toString(), KeyOfOperations.MODEL_NAME.getValue(), dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getSmartPhoneModel() != null) {
                            if (dto.getSmartPhoneModel().getModelSerialNumber() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SERIAL_NUMBER.getValue(), String.valueOf(dto.getSmartPhoneModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().getModelSize() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SIZE.getValue(), String.valueOf(dto.getSmartPhoneModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().isModelAvailability()) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.BOOLEAN.toString(), KeyOfOperations.MODEL_AVAILABILITY.getValue(), String.valueOf(true)));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().getModelMemory() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_SIZE.getValue(), String.valueOf(dto.getSmartPhoneModel().getModelMemory())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().getModelNumberOfCameras() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria(TypeOfOperations.INT.toString(), KeyOfOperations.MODEL_AVAILABILITY.getValue(), String.valueOf(dto.getSmartPhoneModel().isModelAvailability())));
                                specification.and(modelSpecification);
                            }
                        }
                        List<SmartPhoneModel> smartPhoneModelList;
                        if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                            smartPhoneModelList = new ArrayList<>();
                            for (SmartPhoneModel smartPhoneModel : smartPhoneModelRepository.findAll()) {
                                smartPhoneModel.setC_smartPhone(null);
                                smartPhoneModelList.add(smartPhoneModel);
                            }
                        } else {
                            smartPhoneModelList = smartPhoneModelRepository.findAll(specification);
                            if (dto.isSort_num()) {
                                smartPhoneModelList.sort(Comparator.comparingInt(SmartPhoneModel::getModelPrice));
                            } else {
                                smartPhoneModelList.sort(Comparator.comparing(SmartPhoneModel::getModelName));
                            }
                        }
                        dto1.setSmartPhoneModelList(smartPhoneModelList);
                    }
                }
                return new ResponseEntity<>(dto1, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }
}