package com.example.wizard_info_micro.service;

import com.example.wizard_info_micro.model.WizardInfo;

import java.util.List;

public interface WizardInfoService {

    WizardInfo saveWizardInfo(WizardInfo wizardInfo);

    List<WizardInfo> getAllWizardInfo();

    WizardInfo getWizardInfoById(String id);
    
    WizardInfo updateWizardInfoById(String id, WizardInfo wizardInfo);

    String deleteWizardInfo(String id);

}
