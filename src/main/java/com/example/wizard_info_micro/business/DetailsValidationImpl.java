package com.example.wizard_info_micro.business;

import com.example.wizard_info_micro.database.WizardInfoRepository;
import com.example.wizard_info_micro.entity.WizardInfo;
import com.example.wizard_info_micro.exception.server.InvalidWizardInfoDetailsException;
import com.example.wizard_info_micro.service.WizardInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DetailsValidationImpl implements DetailsValidation {

    private static final Logger logger = LoggerFactory.getLogger(WizardInfoServiceImpl.class);

    @Autowired
    private WizardInfoRepository wizardInfoRepository;

    @Override
    public WizardInfo wizardInfoValidation(WizardInfo wizardInfo) {
        logger.info("Server DetailsValidation.wizardInfoValidation");
        if (wizardInfo.getName().trim().equalsIgnoreCase("")) {
            throw new InvalidWizardInfoDetailsException("Wizard name must not be empty.");
        }

        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!@#^$%&*(),.+=|<>?{}\\[\\]~-]");

        Matcher hasDigit = digit.matcher(wizardInfo.getName().trim());
        Matcher hasSpecial = special.matcher(wizardInfo.getName().trim());

        if (hasDigit.find() || hasSpecial.find()) {
            throw new InvalidWizardInfoDetailsException("Wizard name must not be containing special characters and numbers.");
        }

        if (wizardInfo.getAge() < 18 || wizardInfo.getAge() > 70) {
            throw new InvalidWizardInfoDetailsException("Wizard age must be between 18 to 70 years of age.");
        }

        return wizardInfo;
    }
}
