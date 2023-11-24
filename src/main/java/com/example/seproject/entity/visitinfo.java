package com.example.seproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

//
@Entity
@Table(name="visitorinfo")
public class visitinfo {
    @Id
    private String visitorPhone;
    private String visitorName;
    private String lockinfo;
    private String UUID;
    private String idcard;
    private Date arrivedate;
    private String arrivetime;
    private String lefttime;
    private String visitreason;
    private boolean useCar;
    private String licensePlate;
    private String affiliatedUnit;
    private String visitAreas;
    private String applicationStatus;
//物流信息
    private String logisticsnum;
    private String goods;
    //施工人员信息
    private String equipment;
    private boolean elsepeople;
    private String elsepeopleinfo;


    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getLogisticsnum() {
        return logisticsnum;
    }

    public void setLogisticsnum(String logisticsnum) {
        this.logisticsnum = logisticsnum;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public boolean isElsepeople() {
        return elsepeople;
    }

    public void setElsepeople(boolean elsepeople) {
        this.elsepeople = elsepeople;
    }

    public String getElsepeopleinfo() {
        return elsepeopleinfo;
    }

    public void setElsepeopleinfo(String elsepeopleinfo) {
        this.elsepeopleinfo = elsepeopleinfo;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getLefttime() {
        return lefttime;
    }

    public void setLefttime(String lefttime) {
        this.lefttime = lefttime;
    }

    public String getApplicationStatus(){
        return applicationStatus;
    }
    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getArrivedate() {
        return arrivedate;
    }

    public void setArrivedate(Date arrivedate) {
        this.arrivedate = arrivedate;
    }

    public String getVisitreason() {
        return visitreason;
    }

    public void setVisitreason(String visitreason) {
        this.visitreason = visitreason;
    }

    public boolean isUseCar() {
        return useCar;
    }

    public void setUseCar(boolean useCar) {
        this.useCar = useCar;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getAffiliatedUnit() {
        return affiliatedUnit;
    }

    public void setAffiliatedUnit(String affiliatedUnit) {
        this.affiliatedUnit = affiliatedUnit;
    }

    public String getVisitAreas() {
        return visitAreas;
    }

    public void setVisitAreas(String visitAreas) {
        this.visitAreas = visitAreas;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getLockinfo() {
        return lockinfo;
    }

    public void setLockinfo(String lockinfo) {
        this.lockinfo = lockinfo;
    }

    public boolean isApplicationStatusIsNull() {
        return applicationStatus == null;
    }

    public boolean isApplicationStatusIsNotNull() {
        return applicationStatus != null;
    }
}
