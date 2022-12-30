package com.example.wizard_info_micro.service;

import com.example.wizard_info_micro.database.WizardInfoRepository;
import com.example.wizard_info_micro.exception.server.NoWizardInfoFoundException;
import com.example.wizard_info_micro.exception.server.WizardIdNotFoundException;
import com.example.wizard_info_micro.exception.server.WizardInfoExistException;
import com.example.wizard_info_micro.model.WizardInfo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class WizardInfoServiceImpl implements WizardInfoService {

    @Autowired
    private WizardInfoRepository wizardInfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(WizardInfoServiceImpl.class);

    @Override
    public WizardInfo saveWizardInfo(@Valid WizardInfo wizardInfo) {
        WizardInfo existWizardInfo = wizardInfoRepository.findByName(wizardInfo.getName().trim());
        if (existWizardInfo != null) {
            throw new WizardInfoExistException("Wizard info exists, consider update it with wizard Id: " + existWizardInfo.getId());
        }
        String joinedDate = String.valueOf(java.time.LocalDate.now());
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
        return wizardInfoRepository.findById(UUID.fromString(id)).orElseThrow(() -> new WizardIdNotFoundException("Wizard Id does not exist. -- " + id));
    }

    @Override
    public WizardInfo updateWizardInfoById(String id, @Valid WizardInfo wizardInfo) {
        WizardInfo existingWizardInfo = wizardInfoRepository.findById(UUID.fromString(id)).orElseThrow(() -> new WizardIdNotFoundException("Wizard Id does not exist. -- " + id));
        WizardInfo existWizardInfoName = wizardInfoRepository.findByName(wizardInfo.getName().trim());
        if (existWizardInfoName == null || existWizardInfoName != null && existingWizardInfo.getName().equalsIgnoreCase(wizardInfo.getName().trim())) {
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
        if (!wizardInfoRepository.findById(UUID.fromString(id)).isPresent()) {
            throw new WizardIdNotFoundException("Wizard ID does not exist. -- " + id);
        }
        wizardInfoRepository.deleteById(UUID.fromString(id));
        return "Wizard info has been deleted successfully !\tID: " + id;
    }
}
