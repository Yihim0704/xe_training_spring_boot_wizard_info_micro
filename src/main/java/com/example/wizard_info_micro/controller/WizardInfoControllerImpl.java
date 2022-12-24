package com.example.wizard_info_micro.controller;

import com.example.wizard_info_micro.model.WizardInfo;
import com.example.wizard_info_micro.service.WizardInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wizard-info")
public class WizardInfoControllerImpl implements WizardInfoController {

    private static final Logger logger = LoggerFactory.getLogger(WizardInfoControllerImpl.class);

    @Autowired
    private WizardInfoServiceImpl wizardInfoServiceImpl;

    @Override
    @PostMapping("add")
    public WizardInfo addWizardInfo(@RequestBody @Valid WizardInfo wizardInfo) {
        return wizardInfoServiceImpl.saveWizardInfo(wizardInfo);
    }

    @Override
    @GetMapping("find-all")
    public List<WizardInfo> findAllWizardInfo() {
        return wizardInfoServiceImpl.getAllWizardInfo();
    }

    @Override
    @GetMapping("find-id/{id}")
    public WizardInfo findWizardInfoById(@PathVariable String id) {
        return wizardInfoServiceImpl.getWizardInfoById(id);
    }

    @Override
    @PutMapping("update-id/{id}")
    public WizardInfo changeWizardInfoById(@PathVariable String id, @RequestBody @Valid WizardInfo wizardInfo) {
        return wizardInfoServiceImpl.updateWizardInfoById(id, wizardInfo);
    }

    @Override
    @DeleteMapping("delete-id/{id}")
    public String removeWizardInfoById(@PathVariable String id) {
        return wizardInfoServiceImpl.deleteWizardInfo(id);
    }
}
