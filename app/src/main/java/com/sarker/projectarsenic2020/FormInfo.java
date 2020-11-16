package com.sarker.projectarsenic2020;

public class FormInfo {

    String headName,phone;

    public FormInfo(){

    }

    public FormInfo(String headName, String phone) {
        this.headName = headName;
        this.phone = phone;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
