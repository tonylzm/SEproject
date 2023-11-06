package com.example.seproject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name="history")
public class History {
    @Id
    private String visitorPhone;
    private String visitorName;
    private Date arrivedate;
    private String arrivetime;
    private String lefttime;
    private String visitreason;
    private boolean useCar;
    private String licensePlate;
    private String visitAreas;

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

    public Date getArrivedate() {
        return arrivedate;
    }

    public void setArrivedate(Date arrivedate) {
        this.arrivedate = arrivedate;
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

    public String getVisitAreas() {
        return visitAreas;
    }

    public void setVisitAreas(String visitAreas) {
        this.visitAreas = visitAreas;
    }
}
