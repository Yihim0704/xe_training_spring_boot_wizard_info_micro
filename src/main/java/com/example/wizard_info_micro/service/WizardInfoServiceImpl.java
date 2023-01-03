package com.example.wizard_info_micro.service;

import com.example.wizard_info_micro.business.DetailsValidation;
import com.example.wizard_info_micro.dao.WizardInfoDao;
import com.example.wizard_info_micro.database.WizardInfoRepository;
import com.example.wizard_info_micro.entity.WizardInfo;
import com.example.wizard_info_micro.exception.server.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WizardInfoServiceImpl implements WizardInfoService {

    @Autowired
    private WizardInfoRepository wizardInfoRepository;

    @Autowired
    private DetailsValidation detailsValidation;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WizardInfoDao wizardInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(WizardInfoServiceImpl.class);

    @Override
    public WizardInfo saveWizardInfo(WizardInfo wizardInfo) throws HttpRequestMethodNotSupportedException {
        logger.info("Server WizardInfoService.saveWizardInfo");
        try {
            if (wizardInfoDao.findDuplicatedName(wizardInfo)) {
                throw new WizardInfoExistException("Wizard name exists, consider change to another name.");
            } else {
                detailsValidation.wizardInfoValidation(wizardInfo);
                WizardInfo validatedWizardInfo = detailsValidation.wizardInfoValidation(wizardInfo);
                String joinedDate = String.valueOf(java.time.LocalDate.now());
                validatedWizardInfo.setName(wizardInfo.getName().trim());
                validatedWizardInfo.setJoinedDate(joinedDate);
                validatedWizardInfo.setActive(true);
                return wizardInfoRepository.save(validatedWizardInfo);
            }
        } catch (NullPointerException e) {
            throw new InvalidWizardInfoDetailsException("Fields must not be null.");
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException(e.getLocalizedMessage());
        }
    }

    @Override
    public List<WizardInfo> getAllWizardInfo() throws HttpRequestMethodNotSupportedException {
        logger.info("Server WizardInfoService.getAllWizardInfo");
        try {
            if (wizardInfoRepository.findAll().isEmpty()) {
                throw new NoWizardInfoFoundException("There is no wizard info in the database.");
            }
            return wizardInfoRepository.findAll()
                    .stream()
                    .map(wizardInfo -> modelMapper.map(wizardInfo, WizardInfo.class))
                    .collect(Collectors.toList());
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException(e.getLocalizedMessage());
        }
    }


    @Override
    public WizardInfo getWizardInfoById(String id) throws HttpRequestMethodNotSupportedException {
        logger.info("Server WizardInfoService.getWizardInfoById");
        try {
            return wizardInfoRepository.findById(UUID.fromString(id)).orElseThrow(() -> new WizardIdNotFoundException("Wizard Id does not exist. -- " + id));
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException(e.getLocalizedMessage());
        }
    }

    @Override
    public WizardInfo updateWizardInfoById(String id, WizardInfo wizardInfo) throws HttpRequestMethodNotSupportedException {
        logger.info("Server WizardInfoService.updateWizardInfoById");
        try {
            WizardInfo existingWizardInfo = wizardInfoRepository.findById(UUID.fromString(id)).orElseThrow(() -> new WizardIdNotFoundException("Wizard Id does not exist. -- " + id));
            boolean existWizardInfoName = wizardInfoDao.findDuplicatedName(wizardInfo);
            if (!existWizardInfoName || existingWizardInfo.getName().equalsIgnoreCase(wizardInfo.getName().trim())) {
                WizardInfo validatedWizardInfo = detailsValidation.wizardInfoValidation(wizardInfo);
                existingWizardInfo.setName(validatedWizardInfo.getName().trim());
                existingWizardInfo.setAge(validatedWizardInfo.getAge());
                existingWizardInfo.setActive(validatedWizardInfo.isActive());
                return wizardInfoRepository.save(existingWizardInfo);
            } else {
                throw new WizardInfoExistException("Wizard name exists, consider change to another name.");
            }
        } catch (NullPointerException e) {
            throw new InvalidWizardInfoDetailsException("Fields must not be null.");
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException(e.getLocalizedMessage());
        }
    }

    @Override
    public String deleteWizardInfo(String id) throws HttpRequestMethodNotSupportedException {
        logger.info("Server WizardInfoService.deleteWizardInfo");
        try {
            if (!wizardInfoRepository.findById(UUID.fromString(id)).isPresent()) {
                throw new WizardIdNotFoundException("Wizard Id does not exist. -- " + id);
            }
            wizardInfoRepository.deleteById(UUID.fromString(id));
            return "Wizard info has been deleted successfully !\tId: " + id;
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException(e.getLocalizedMessage());
        }
    }
}
