package com.example.seproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="internal_staff")
public class Internal_staff {
    @Id
    private String staffName;
    private String staffPhone;
    private String staffIdcard;
    private String staffAffiliatedUnit;
    private String staffPosition;
    private String staffPassword;
    private String UUID;
//副权限
    private String secondarypower;
    private String mainpower;

    public String getSecondarypower() {
        return secondarypower;
    }

    public void setSecondarypower(String secondarypower) {
        this.secondarypower = secondarypower;
    }

    public String getMainpower() {
        return mainpower;
    }

    public void setMainpower(String mainpower) {
        this.mainpower = mainpower;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffIdcard() {
        return staffIdcard;
    }

    public void setStaffIdcard(String staffIdcard) {
        this.staffIdcard = staffIdcard;
    }

    public String getStaffAffiliatedUnit() {
        return staffAffiliatedUnit;
    }

    public void setStaffAffiliatedUnit(String staffAffiliatedUnit) {
        this.staffAffiliatedUnit = staffAffiliatedUnit;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }
}
