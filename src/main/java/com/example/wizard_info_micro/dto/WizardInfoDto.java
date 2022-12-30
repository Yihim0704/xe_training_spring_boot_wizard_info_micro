package com.example.wizard_info_micro.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WizardInfoDto {
    private UUID id;
    private String name;
    private int age;
    private String joinedDate;
    private boolean active;
}
