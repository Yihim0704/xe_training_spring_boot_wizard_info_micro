package com.example.wizard_info_micro.controller;

import com.example.wizard_info_micro.dto.WizardInfoDto;
import com.example.wizard_info_micro.model.WizardInfo;
import com.example.wizard_info_micro.service.WizardInfoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/wizard-info")
public class WizardInfoController {

    private static final Logger logger = LoggerFactory.getLogger(WizardInfoController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WizardInfoService wizardInfoService;

    @PostMapping("add")
    public ResponseEntity<WizardInfoDto> addWizardInfo(@RequestBody WizardInfoDto wizardInfoDto) {

        WizardInfo wizardInfoRequest = modelMapper.map(wizardInfoDto, WizardInfo.class);

        WizardInfo wizardInfo = wizardInfoService.saveWizardInfo(wizardInfoRequest);

        WizardInfoDto wizardInfoResponse = modelMapper.map(wizardInfo, WizardInfoDto.class);

        return new ResponseEntity<WizardInfoDto>(wizardInfoResponse, HttpStatus.CREATED);
    }

    @GetMapping("find-all")
    public List<WizardInfoDto> findAllWizardInfo() {
        logger.info("Server findAllWizardInfo");
        return wizardInfoService.getAllWizardInfo()
                .stream()
                .map(wizardInfo -> modelMapper.map(wizardInfo, WizardInfoDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("find-id/{id}")
    public ResponseEntity<WizardInfoDto> findWizardInfoById(@PathVariable String id) {
        logger.info("Server findWizardInfoById");
        WizardInfo wizardInfo = wizardInfoService.getWizardInfoById(id);

        WizardInfoDto wizardInfoResponse = modelMapper.map(wizardInfo, WizardInfoDto.class);
        return ResponseEntity.ok().body(wizardInfoResponse);
    }

    @PutMapping("update-id/{id}")
    public ResponseEntity<WizardInfoDto> changeWizardInfoById(@PathVariable String id, @RequestBody WizardInfoDto wizardInfoDto) {

        WizardInfo wizardInfoRequest = modelMapper.map(wizardInfoDto, WizardInfo.class);

        WizardInfo wizardInfo = wizardInfoService.updateWizardInfoById(id, wizardInfoRequest);

        WizardInfoDto wizardInfoResponse = modelMapper.map(wizardInfo, WizardInfoDto.class);

        return ResponseEntity.ok().body(wizardInfoResponse);
    }

    @DeleteMapping("delete-id/{id}")
    public String removeWizardInfoById(@PathVariable String id) {
        return wizardInfoService.deleteWizardInfo(id);
    }
}
