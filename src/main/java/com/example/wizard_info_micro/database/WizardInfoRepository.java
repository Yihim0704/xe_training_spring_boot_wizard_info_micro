package com.example.wizard_info_micro.database;

import com.example.wizard_info_micro.model.WizardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WizardInfoRepository extends JpaRepository<WizardInfo, String> {
    List<WizardInfo> findWizardInfoByName(String name);
}
