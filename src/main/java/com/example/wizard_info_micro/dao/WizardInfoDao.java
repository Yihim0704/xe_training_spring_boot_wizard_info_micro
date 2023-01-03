package com.example.wizard_info_micro.dao;

import com.example.wizard_info_micro.entity.WizardInfo;

public interface WizardInfoDao {
    boolean findDuplicatedName(WizardInfo wizardInfo);
}
