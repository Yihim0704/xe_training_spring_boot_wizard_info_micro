package com.example.wizard_info_micro.service;

import com.example.wizard_info_micro.database.WizardInfoRepository;
import com.example.wizard_info_micro.model.WizardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WizardInfoServiceImpl implements WizardInfoService {

    @Autowired
    private WizardInfoRepository wizardInfoRepository;


    @Override
    public WizardInfo saveWizardInfo(WizardInfo wizardInfo) {
        String id = UUID.randomUUID().toString();
        String joinedDate = String.valueOf(java.time.LocalDate.now());
        wizardInfo.setId(id);
        wizardInfo.setJoinedDate(joinedDate);
        wizardInfo.setActive(true);
        return wizardInfoRepository.save(wizardInfo);
    }

    @Override
    public List<WizardInfo> getAllWizardInfo() {
        return wizardInfoRepository.findAll();
    }

    @Override
    public WizardInfo getWizardInfoById(String id) {
        return wizardInfoRepository.findById(id).orElse(null);
    }

    @Override
    public WizardInfo updateWizardInfoById(String id, WizardInfo wizardInfo) {
        WizardInfo existingWizardInfo = wizardInfoRepository.findById(id).orElse(null);
        existingWizardInfo.setName(wizardInfo.getName());
        existingWizardInfo.setAge(wizardInfo.getAge());
        existingWizardInfo.setActive(wizardInfo.isActive());
        return wizardInfoRepository.save(existingWizardInfo);
    }

    @Override
    public String deleteWizardInfo(String id) {
        wizardInfoRepository.deleteById(id);
        return "Wizard info has been deleted successfully !\tID: " + id;
    }
}
