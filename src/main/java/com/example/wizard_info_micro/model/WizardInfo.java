package com.example.wizard_info_micro.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wizard_info")
public class WizardInfo {
    @Id
    private String id;
    @NotNull(message = "Wizard name should not be null.")
    @NotBlank(message = "Wizard name should not be blank.")
    private String name;
    @Min(value = 18, message = "Wizard should be at least 18 years of age.")
    @Max(value = 70, message = "Wizard should be below 70 years of age.")
    private int age;
    private String joinedDate;
    private boolean active;

    public WizardInfo() {
    }

    public WizardInfo(String id, String name, int age, String joinedDate, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.joinedDate = joinedDate;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
