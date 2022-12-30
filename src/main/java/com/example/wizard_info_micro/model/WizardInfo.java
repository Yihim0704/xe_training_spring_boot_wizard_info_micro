package com.example.wizard_info_micro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wizard_info")
public class WizardInfo {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @NotNull(message = "Wizard name should not be null.")
    @NotBlank(message = "Wizard name should not be blank.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Wizard name should not be containing special characters and numbers.")
    private String name;

    @Range(min = 18, max = 70, message = "Wizard should be between 18 to 70 years of age.")
    private int age;

    private String joinedDate;

    private boolean active;
}
