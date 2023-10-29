package com.example.seproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="block")
public class block {
    @Id
    private String visitorName;
    private String visitorPhone;
    private String affiliatedUnit;
    private String blockreason;
    private String blocktime;
    private String blockpeople;

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getAffiliatedUnit() {
        return affiliatedUnit;
    }

    public void setAffiliatedUnit(String affiliatedUnit) {
        this.affiliatedUnit = affiliatedUnit;
    }

    public String getBlockreason() {
        return blockreason;
    }

    public void setBlockreason(String blockreason) {
        this.blockreason = blockreason;
    }

    public String getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(String blocktime) {
        this.blocktime = blocktime;
    }

    public String getBlockpeople() {
        return blockpeople;
    }

    public void setBlockpeople(String blockpeople) {
        this.blockpeople = blockpeople;
    }
}
