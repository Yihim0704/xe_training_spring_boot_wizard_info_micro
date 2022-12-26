package com.example.wizard_info_micro.service;

import com.example.wizard_info_micro.database.WizardInfoRepository;
import com.example.wizard_info_micro.exception.server.NoWizardInfoFoundException;
import com.example.wizard_info_micro.exception.server.ServerExceptionsHandler;
import com.example.wizard_info_micro.exception.server.WizardIdNotFoundException;
import com.example.wizard_info_micro.exception.server.WizardInfoExistException;
import com.example.wizard_info_micro.model.WizardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WizardInfoServiceImpl implements WizardInfoService {

    @Autowired
    private WizardInfoRepository wizardInfoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServerExceptionsHandler.class);

    @Override
    public WizardInfo saveWizardInfo(WizardInfo wizardInfo) {
        WizardInfo existWizardInfo = wizardInfoRepository.findWizardInfoByName(wizardInfo.getName());
        if (existWizardInfo != null) {
            throw new WizardInfoExistException("Wizard info exists, consider update it with wizard Id: " + existWizardInfo.getId());
        }
        String id = UUID.randomUUID().toString();
        String joinedDate = String.valueOf(java.time.LocalDate.now());
        wizardInfo.setId(id);
        wizardInfo.setName(wizardInfo.getName().trim());
        wizardInfo.setJoinedDate(joinedDate);
        wizardInfo.setActive(true);
        return wizardInfoRepository.save(wizardInfo);
    }

    @Override
    public List<WizardInfo> getAllWizardInfo() {
        if (wizardInfoRepository.findAll().isEmpty()) {
            throw new NoWizardInfoFoundException("There is no wizard info in the database.");
        }
        return wizardInfoRepository.findAll();
    }

    @Override
    public WizardInfo getWizardInfoById(String id) {
        if (!wizardInfoRepository.findById(id).isPresent()) {
            throw new WizardIdNotFoundException("Wizard ID does not exist.");
        }
        return wizardInfoRepository.findById(id).orElse(null);
    }

    @Override
    public WizardInfo updateWizardInfoById(String id, WizardInfo wizardInfo) {
        if (!wizardInfoRepository.findById(id).isPresent()) {
            throw new WizardIdNotFoundException("Wizard ID does not exist.");
        }
        WizardInfo existWizardInfoName = wizardInfoRepository.findWizardInfoByName(wizardInfo.getName());
        WizardInfo existingWizardInfo = getWizardInfoById(id);
        if (existWizardInfoName == null || existWizardInfoName != null && existingWizardInfo.getName().equalsIgnoreCase(wizardInfo.getName())) {
            existingWizardInfo.setName(wizardInfo.getName().trim());
            existingWizardInfo.setAge(wizardInfo.getAge());
            existingWizardInfo.setActive(wizardInfo.isActive());
            return wizardInfoRepository.save(existingWizardInfo);
        } else {
            throw new WizardInfoExistException("Wizard name exists, consider change to another name");
        }
    }

    @Override
    public String deleteWizardInfo(String id) {
        if (!wizardInfoRepository.findById(id).isPresent()) {
            throw new WizardIdNotFoundException("Wizard ID does not exist.");
        }
        wizardInfoRepository.deleteById(id);
        return "Wizard info has been deleted successfully !\tID: " + id;
    }
}
