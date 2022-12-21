package com.example.wizard_info_micro.controller;

import com.example.wizard_info_micro.model.WizardInfo;
import com.example.wizard_info_micro.service.WizardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wizard-info")
public class WizardInfoController {
    @Autowired
    private WizardInfoService wizardInfoService;

    @PostMapping("add")
    public WizardInfo addWizardInfo(@RequestBody WizardInfo wizardInfo){
        return wizardInfoService.saveWizardInfo(wizardInfo);
    }

    @GetMapping("find-all")
    public List<WizardInfo> findAllWizardInfo (){
        return wizardInfoService.getAllWizardInfo();
    }

    @GetMapping("find-id/{id}")
    public WizardInfo findWizardInfoById (@PathVariable String id){
        return wizardInfoService.getWizardInfoById(id);
    }

    @PutMapping("update-id/{id}")
    public WizardInfo changeWizardInfoById(@PathVariable String id, @RequestBody WizardInfo wizardInfo){
        return wizardInfoService.updateWizardInfoById(id, wizardInfo);
    }

    @DeleteMapping("delete-id/{id}")
    public String removeWizardInfoById(@PathVariable String id){
        return wizardInfoService.deleteWizardInfo(id);
    }
}
