package com.test_case.app.controllers;

import com.test_case.app.model.dto.AddDTO;
import com.test_case.app.model.dto.SearchDTO;
import com.test_case.app.model.dto.SearchResponseDTO;
import com.test_case.app.model.entity.*;
import com.test_case.app.model.entity.entity_model.*;
import com.test_case.app.repository.*;
import com.test_case.app.repository.model_repository.*;
import com.test_case.app.util.spec.ModelCriteria;
import com.test_case.app.util.spec.ModelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddDTO dto) {
        if (dto.getFridge() != null) {
            try {
                fridgeRepository.save(dto.getFridge());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        if (dto.getFridgeModel() != null) {
            try {
                FridgeModel fridgeModel = new FridgeModel();
                fridgeModel.setC_fridge(fridgeRepository.findById(dto.getFridgeModel().getFridge_id()).get());
                fridgeModel.setModelName(dto.getFridgeModel().getModelName());
                fridgeModel.setModelSerialNumber(dto.getFridgeModel().getModelSerialNumber());
                fridgeModel.setModelColor(dto.getFridgeModel().getModelColor());
                fridgeModel.setModelSize(dto.getFridgeModel().getModelSize());
                fridgeModel.setModelPrice(dto.getFridgeModel().getModelPrice());
                fridgeModel.setModelNumberOfDoors(dto.getFridgeModel().getModelNumberOfDoors());
                fridgeModel.setModelCompressorType(dto.getFridgeModel().getModelCompressorType());
                fridgeModel.setModelAvailability(dto.getFridgeModel().isModelAvailability());
                fridgeModelRepository.save(fridgeModel);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        if (dto.getHoover() != null) {
            try {
                hooverRepository.save(dto.getHoover());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        if (dto.getHooverModel() != null) {
            try {
                HooverModel model = new HooverModel();
                model.setC_hoover(hooverRepository.findById(dto.getHooverModel().getHoover_id()).get());
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
                throw new RuntimeException(e);
            }
        }
        if (dto.getTv() != null) {
            try {
                tvRepository.save(dto.getTv());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        if (dto.getTvModel() != null) {
            try {
                TVModel model = new TVModel();
                model.setC_tv(tvRepository.findById(dto.getTvModel().getTv_id()).get());
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
                throw new RuntimeException(e);
            }
        }
        if (dto.getPc() != null) {
            try {
                pcRepository.save(dto.getPc());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        if (dto.getPcModel() != null) {
            try {
                PCModel model = new PCModel();
                model.setC_pc(pcRepository.findById(dto.getPcModel().getPc_id()).get());
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
                throw new RuntimeException(e);
            }
        }
        if (dto.getSmartPhone() != null) {
            try {
                smartPhoneRepository.save(dto.getSmartPhone());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        if (dto.getSmartPhoneModel() != null) {
            try {
                SmartPhoneModel model = new SmartPhoneModel();
                model.setC_smartPhone(smartPhoneRepository.findById(dto.getSmartPhoneModel().getSmartphone_id()).get());
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
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<?> get(@RequestBody SearchDTO dto) throws Exception {
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
                    } else {
                        throw new RuntimeException("у линейки нет моделей");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
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
                    } else {
                        throw new RuntimeException("у линейки нет моделей");

                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                for (PC PC : pcRepository.findByName(line)) {
                    List<PCModel> PCModelList = new ArrayList<>();
                    if (PC.getPcModelList() != null) {
                        String model = line.replace(PC.getName(), "");
                        if (!model.equals("")) {
                            for (PCModel PCModel : PC.getPcModelList()) {
                                if (PCModel.getModelName().equals(model)) {
                                    PCModelList.add(PCModel);
                                }
                            }
                        } else {
                            PCModelList = PC.getPcModelList();
                        }
                    } else {
                        throw new RuntimeException("у линейки нет моделей");
                    }
                    dto1.setPcModelList(PCModelList);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
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
                    } else {
                        throw new RuntimeException("у линейки нет моделей");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
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
                    } else {
                        throw new RuntimeException("у линейки нет моделей");
                    }
                    dto1.setTvModelList(tvModelList);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            if (dto.getType() != null) {
                switch (dto.getType()) {
                    case "TV": {
                        ModelSpecification<TVModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("VARCHAR", "modelColor", dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("INT_CHECK_1", "modelPrice", String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("INT_CHECK_2", "modelPrice", String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("VARCHAR", "modelName", dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getTvModel() != null) {
                            if (dto.getTvModel().getModelSerialNumber() != 0) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("INT", "modelSerialNumber", String.valueOf(dto.getTvModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().getModelSize() != 0) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("INT", "modelSize", String.valueOf(dto.getTvModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().isModelAvailability()) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("BOOLEAN", "modelAvailability", String.valueOf(true)));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().getModelCategory() != null) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("INT", "modelCategory", String.valueOf(dto.getTvModel().getModelCategory())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getTvModel().getModelTechnology() != null) {
                                ModelSpecification<TVModel> modelSpecification = new ModelSpecification<TVModel>(new ModelCriteria("INT", "modelTechnology", String.valueOf(dto.getTvModel().getModelTechnology())));
                                specification.and(modelSpecification);
                            }
                        }
                        if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                            dto1.setTvModelList(tvModelRepository.findAll(specification));
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    List<TVModel> smartPhoneModelList = tvModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(TVModel::getModelPrice));
                                    dto1.setTvModelList(smartPhoneModelList);
                                } else {
                                    List<TVModel> smartPhoneModelList = tvModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(TVModel::getModelPrice).reversed());
                                    dto1.setTvModelList(smartPhoneModelList);
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    List<TVModel> smartPhoneModelList = tvModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(TVModel::getModelName));
                                    dto1.setTvModelList(smartPhoneModelList);
                                } else {
                                    List<TVModel> smartPhoneModelList = tvModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(TVModel::getModelName).reversed());
                                    dto1.setTvModelList(smartPhoneModelList);
                                }
                            }
                        }
                    }
                    case "Fridge": {
                        try {
                            ModelSpecification<FridgeModel> specification = new ModelSpecification<>(null);
                            if (dto.getColor() != null) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelColor", dto.getColor()));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPrice_l() != 0) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_1", "modelPrice", String.valueOf(dto.getPrice_l())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPrice_h() != 0) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_2", "modelPrice", String.valueOf(dto.getPrice_h())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getName() != null) {
                                ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelName", dto.getName().toLowerCase()));
                                specification.and(modelSpecification);
                            }
                            if (dto.getFridgeModel() != null) {
                                if (dto.getFridgeModel().getModelSerialNumber() != 0) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSerialNumber", String.valueOf(dto.getFridgeModel().getModelSerialNumber())));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().getModelSize() != 0) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSize", String.valueOf(dto.getFridgeModel().getModelSize())));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().isModelAvailability()) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("BOOLEAN", "modelAvailability", String.valueOf(true)));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().getModelNumberOfDoors() != null) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelNumberOfDoors", dto.getFridgeModel().getModelNumberOfDoors()));
                                    specification.and(modelSpecification);
                                }
                                if (dto.getFridgeModel().getModelCompressorType() != null) {
                                    ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelCompressorType", dto.getFridgeModel().getModelCompressorType()));
                                    specification.and(modelSpecification);
                                }
                            }
                            if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                                List<FridgeModel> smartPhoneModelList = new ArrayList<>();
                                for(FridgeModel fridgeModel : fridgeModelRepository.findAll()){
                                    //todo убрать loop
                                    fridgeModel.setC_fridge(null);
                                    smartPhoneModelList.add(fridgeModel);
                                }
                                dto1.setFridgeModelList(smartPhoneModelList);
                            } else {
                                if (dto.isSort_num()) {
                                    if (dto.isSort_num()) {
                                        List<FridgeModel> smartPhoneModelList = fridgeModelRepository.findAll(specification);
                                        smartPhoneModelList.sort(Comparator.comparingInt(FridgeModel::getModelPrice));
                                        dto1.setFridgeModelList(smartPhoneModelList);
                                    } else {
                                        List<FridgeModel> smartPhoneModelList = fridgeModelRepository.findAll(specification);
                                        smartPhoneModelList.sort(Comparator.comparingInt(FridgeModel::getModelPrice).reversed());
                                        dto1.setFridgeModelList(smartPhoneModelList);
                                    }
                                } else {
                                    if (dto.isSort_alphabet()) {
                                        List<FridgeModel> smartPhoneModelList = fridgeModelRepository.findAll(specification);
                                        smartPhoneModelList.sort(Comparator.comparing(FridgeModel::getModelName));
                                        dto1.setFridgeModelList(smartPhoneModelList);
                                    } else {
                                        List<FridgeModel> smartPhoneModelList = fridgeModelRepository.findAll(specification);
                                        smartPhoneModelList.sort(Comparator.comparing(FridgeModel::getModelName).reversed());
                                        dto1.setFridgeModelList(smartPhoneModelList);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            throw new Exception(e);
                        }
                    }
                    case "Hoover": {
                        ModelSpecification<HooverModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelColor", dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_1", "modelPrice", String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_2", "modelPrice", String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelName", dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getHooverModel() != null) {
                            if (dto.getHooverModel().getModelSerialNumber() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSerialNumber", String.valueOf(dto.getHooverModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().getModelSize() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSize", String.valueOf(dto.getHooverModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().isModelAvailability()) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("BOOLEAN", "modelAvailability", String.valueOf(true)));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().getModelNumberOfModes() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelNumberOfModes", String.valueOf(dto.getHooverModel().getModelNumberOfModes())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getHooverModel().getModelVolume() != 0) {
                                ModelSpecification<HooverModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelVolume", String.valueOf(dto.getHooverModel().getModelVolume())));
                                specification.and(modelSpecification);
                            }
                        }
                        if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                            dto1.setHooverModelList(hooverModelRepository.findAll(specification));
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    List<HooverModel> smartPhoneModelList = hooverModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(HooverModel::getModelPrice));
                                    dto1.setHooverModelList(smartPhoneModelList);
                                } else {
                                    List<HooverModel> smartPhoneModelList = hooverModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(HooverModel::getModelPrice).reversed());
                                    dto1.setHooverModelList(smartPhoneModelList);
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    List<HooverModel> smartPhoneModelList = hooverModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(HooverModel::getModelName));
                                    dto1.setHooverModelList(smartPhoneModelList);
                                } else {
                                    List<HooverModel> smartPhoneModelList = hooverModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(HooverModel::getModelName).reversed());
                                    dto1.setHooverModelList(smartPhoneModelList);
                                }
                            }
                        }
                    }
                    case "PC": {
                        ModelSpecification<PCModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelColor", dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_1", "modelPrice", String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_2", "modelPrice", String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelName", dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPcModel() != null) {
                            if (dto.getPcModel().getModelSerialNumber() != 0) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSerialNumber", String.valueOf(dto.getPcModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPcModel().getModelSize() != 0) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSize", String.valueOf(dto.getPcModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getPcModel().isModelAvailability()) {
                                ModelSpecification<PCModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("BOOLEAN", "modelAvailability", String.valueOf(true)));
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
                            List<PCModel> smartPhoneModelList = new ArrayList<>();
                            pcModelRepository.findAll().forEach(smartPhoneModelList::add);
                            dto1.setPcModelList(smartPhoneModelList);
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    List<PCModel> smartPhoneModelList = pcModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(PCModel::getModelPrice));
                                    dto1.setPcModelList(smartPhoneModelList);
                                } else {
                                    List<PCModel> smartPhoneModelList = pcModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(PCModel::getModelPrice).reversed());
                                    dto1.setPcModelList(smartPhoneModelList);
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    List<PCModel> smartPhoneModelList = pcModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(PCModel::getModelName));
                                    dto1.setPcModelList(smartPhoneModelList);
                                } else {
                                    List<PCModel> smartPhoneModelList = pcModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(PCModel::getModelName).reversed());
                                    dto1.setPcModelList(smartPhoneModelList);
                                }
                            }
                        }
                    }
                    case "SmartPhone": {
                        ModelSpecification<SmartPhoneModel> specification = new ModelSpecification<>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelColor", dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_1", "modelPrice", String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT_CHECK_2", "modelPrice", String.valueOf(dto.getPrice_h())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getName() != null) {
                            ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("VARCHAR", "modelName", dto.getName().toLowerCase()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getSmartPhoneModel() != null) {
                            if (dto.getSmartPhoneModel().getModelSerialNumber() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSerialNumber", String.valueOf(dto.getSmartPhoneModel().getModelSerialNumber())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().getModelSize() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSize", String.valueOf(dto.getSmartPhoneModel().getModelSize())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().isModelAvailability()) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("BOOLEAN", "modelAvailability", String.valueOf(true)));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().getModelMemory() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelSize", String.valueOf(dto.getSmartPhoneModel().getModelMemory())));
                                specification.and(modelSpecification);
                            }
                            if (dto.getSmartPhoneModel().getModelNumberOfCameras() != 0) {
                                ModelSpecification<SmartPhoneModel> modelSpecification = new ModelSpecification<>(new ModelCriteria("INT", "modelAvailability", String.valueOf(dto.getSmartPhoneModel().isModelAvailability())));
                                specification.and(modelSpecification);
                            }
                        }
                        if (!dto.isSort_alphabet() && !dto.isSort_num()) {
                            dto1.setSmartPhoneModelList(smartPhoneModelRepository.findAll(specification));
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    List<SmartPhoneModel> smartPhoneModelList = smartPhoneModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(SmartPhoneModel::getModelPrice));
                                    dto1.setSmartPhoneModelList(smartPhoneModelList);
                                } else {
                                    List<SmartPhoneModel> smartPhoneModelList = smartPhoneModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparingInt(SmartPhoneModel::getModelPrice).reversed());
                                    dto1.setSmartPhoneModelList(smartPhoneModelList);
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    List<SmartPhoneModel> smartPhoneModelList = smartPhoneModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(SmartPhoneModel::getModelName));
                                    dto1.setSmartPhoneModelList(smartPhoneModelList);
                                } else {
                                    List<SmartPhoneModel> smartPhoneModelList = smartPhoneModelRepository.findAll(specification);
                                    smartPhoneModelList.sort(Comparator.comparing(SmartPhoneModel::getModelName).reversed());
                                    dto1.setSmartPhoneModelList(smartPhoneModelList);
                                }
                            }
                        }
                    }
                }
            }
        }
        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }
}