package com.example.wizard_info_micro.dao;

import com.example.wizard_info_micro.database.WizardInfoRepository;
import com.example.wizard_info_micro.entity.WizardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WizardInfoDaoImpl implements WizardInfoDao {

    @Autowired
    private WizardInfoRepository wizardInfoRepository;

    @Override
    public boolean findDuplicatedName(WizardInfo wizardInfo) {
        WizardInfo existWizardInfo = wizardInfoRepository.findByName(wizardInfo.getName().trim());
        return existWizardInfo != null;
    }
}
