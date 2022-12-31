package com.example.wizard_info_micro.service;

import com.example.wizard_info_micro.entity.WizardInfo;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

public interface WizardInfoService {

    WizardInfo saveWizardInfo(WizardInfo wizardInfo) throws HttpRequestMethodNotSupportedException;

    List<WizardInfo> getAllWizardInfo() throws HttpRequestMethodNotSupportedException;

    WizardInfo getWizardInfoById(String id) throws HttpRequestMethodNotSupportedException;

    WizardInfo updateWizardInfoById(String id, WizardInfo wizardInfo) throws HttpRequestMethodNotSupportedException;

    String deleteWizardInfo(String id) throws HttpRequestMethodNotSupportedException;

}
