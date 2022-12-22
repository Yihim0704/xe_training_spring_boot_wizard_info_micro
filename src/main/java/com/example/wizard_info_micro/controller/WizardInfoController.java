package com.example.wizard_info_micro.controller;

import com.example.wizard_info_micro.model.WizardInfo;

import java.util.List;


public interface WizardInfoController {

    WizardInfo addWizardInfo(WizardInfo wizardInfo);

    List<WizardInfo> findAllWizardInfo();

    WizardInfo findWizardInfoById(String id);

    WizardInfo changeWizardInfoById(String id, WizardInfo wizardInfo);

    String removeWizardInfoById(String id);

}
