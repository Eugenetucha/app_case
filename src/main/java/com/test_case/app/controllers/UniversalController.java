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
    public ResponseEntity<?> get(@RequestBody SearchDTO dto) {
        SearchResponseDTO dto1 = new SearchResponseDTO();
        if (dto.getName() != null) {
            String line = dto.getName().toLowerCase().trim();
            try {
                for (Fridge fridge : fridgeRepository.findByName(line)) {
                    List<FridgeModel> fridgeModelList = new ArrayList<>();
                    if (fridge != null) {
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
                    }
                    dto1.setFridgeModelList(fridgeModelList);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                for (Hoover hoover : hooverRepository.findByName(line)) {
                    List<HooverModel> hooverModelList = new ArrayList<>();
                    if (hoover != null) {
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
                    }
                    dto1.setHooverModelList(hooverModelList);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                for (PC PC : pcRepository.findByName(line)) {
                    List<PCModel> PCModelList = new ArrayList<>();
                    if (PC != null) {
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
                    }
                    dto1.setPcModelList(PCModelList);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                for (SmartPhone smartPhone : smartPhoneRepository.findByName(line)) {
                    List<SmartPhoneModel> smartPhoneModelList = new ArrayList<>();
                    if (smartPhone != null) {
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
                    }
                    dto1.setSmartPhoneModelList(smartPhoneModelList);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                for (TV tv : tvRepository.findByName(line)) {
                    List<TVModel> tvModelList = new ArrayList<>();
                    if (tv != null) {
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
                        if (!dto.isSort_alphabet() && dto.isSort_num()) {
                            tvModelRepository.findAll(specification);
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    tvModelRepository.findAll(specification).sort(Comparator.comparingInt(TVModel::getModelPrice));
                                } else {
                                    tvModelRepository.findAll(specification).sort(Comparator.comparingInt(TVModel::getModelPrice).reversed());
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    tvModelRepository.findAll(specification).sort(Comparator.comparing(TVModel::getModelName));
                                } else {
                                    tvModelRepository.findAll(specification).sort(Comparator.comparing(TVModel::getModelName).reversed());
                                }
                            }
                        }
                    }
                    case "Fridge": {
                        ModelSpecification<FridgeModel> specification = new ModelSpecification<FridgeModel>(null);
                        if (dto.getColor() != null) {
                            ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<FridgeModel>(new ModelCriteria("VARCHAR", "modelColor", dto.getColor()));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_l() != 0) {
                            ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<FridgeModel>(new ModelCriteria("INT_CHECK_1", "modelPrice", String.valueOf(dto.getPrice_l())));
                            specification.and(modelSpecification);
                        }
                        if (dto.getPrice_h() != 0) {
                            ModelSpecification<FridgeModel> modelSpecification = new ModelSpecification<FridgeModel>(new ModelCriteria("INT_CHECK_2", "modelPrice", String.valueOf(dto.getPrice_h())));
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
                        if (!dto.isSort_alphabet() && dto.isSort_num()) {
                            fridgeModelRepository.findAll(specification);
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    fridgeModelRepository.findAll(specification).sort(Comparator.comparingInt(FridgeModel::getModelPrice));
                                } else {
                                    fridgeModelRepository.findAll(specification).sort(Comparator.comparingInt(FridgeModel::getModelPrice).reversed());
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    fridgeModelRepository.findAll(specification).sort(Comparator.comparing(FridgeModel::getModelName));
                                } else {
                                    fridgeModelRepository.findAll(specification).sort(Comparator.comparing(FridgeModel::getModelName).reversed());
                                }
                            }
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
                        if (!dto.isSort_alphabet() && dto.isSort_num()) {
                            hooverModelRepository.findAll(specification);
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    hooverModelRepository.findAll(specification).sort(Comparator.comparingInt(HooverModel::getModelPrice));
                                } else {
                                    hooverModelRepository.findAll(specification).sort(Comparator.comparingInt(HooverModel::getModelPrice).reversed());
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    hooverModelRepository.findAll(specification).sort(Comparator.comparing(HooverModel::getModelName));
                                } else {
                                    hooverModelRepository.findAll(specification).sort(Comparator.comparing(HooverModel::getModelName).reversed());
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
                        if (!dto.isSort_alphabet() && dto.isSort_num()) {
                            pcModelRepository.findAll(specification);
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    pcModelRepository.findAll(specification).sort(Comparator.comparingInt(PCModel::getModelPrice));
                                } else {
                                    pcModelRepository.findAll(specification).sort(Comparator.comparingInt(PCModel::getModelPrice).reversed());
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    pcModelRepository.findAll(specification).sort(Comparator.comparing(PCModel::getModelName));
                                } else {
                                    pcModelRepository.findAll(specification).sort(Comparator.comparing(PCModel::getModelName).reversed());
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
                        if (!dto.isSort_alphabet() && dto.isSort_num()) {
                            smartPhoneModelRepository.findAll(specification);
                        } else {
                            if (dto.isSort_num()) {
                                if (dto.isSort_num()) {
                                    smartPhoneModelRepository.findAll(specification).sort(Comparator.comparingInt(SmartPhoneModel::getModelPrice));
                                } else {
                                    smartPhoneModelRepository.findAll(specification).sort(Comparator.comparingInt(SmartPhoneModel::getModelPrice).reversed());
                                }
                            } else {
                                if (dto.isSort_alphabet()) {
                                    smartPhoneModelRepository.findAll(specification).sort(Comparator.comparing(SmartPhoneModel::getModelName));
                                } else {
                                    smartPhoneModelRepository.findAll(specification).sort(Comparator.comparing(SmartPhoneModel::getModelName).reversed());
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