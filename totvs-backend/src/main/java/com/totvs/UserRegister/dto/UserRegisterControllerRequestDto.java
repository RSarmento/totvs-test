package com.totvs.UserRegister.dto;

import com.totvs.UserRegister.domain.PhoneNumber;

import java.util.List;

public class UserRegisterControllerRequestDto {

    private String name;
    private String address;
    private String neighborhood;
    private List<String> phoneNumberList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public List<String> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<String> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }
}
