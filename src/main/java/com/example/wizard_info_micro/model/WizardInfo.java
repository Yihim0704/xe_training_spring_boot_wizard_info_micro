package com.example.wizard_info_micro.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "wizard_info")
public class WizardInfo {
    @Id
    private String id;
    private String name;
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
