/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inti_hotel;

/**
 *
 * @author rdmn5
 */
public class Customer {

    private String customerID;
    private String customerName;
    private String roomType;
    private String boockedDate;
    private String check_In_Date;
    private String check_Out_Date;
    private String desiredPrice;
    private String totalDesiredPrice;
    private String services;

    public Customer(String customerName, String roomType, String boockedDate, String check_In_Date, String check_Out_Date, String desiredPrice, String totalDesiredPrice, String services) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.boockedDate = boockedDate;
        this.check_In_Date = check_In_Date;
        this.check_Out_Date = check_Out_Date;
        this.desiredPrice = desiredPrice;
        this.totalDesiredPrice = totalDesiredPrice;
        this.services = services;
    }

    public String getTotalDesiredPrice() {
        return totalDesiredPrice;
    }

    public void setTotalDesiredPrice(String totalDesiredPrice) {
        this.totalDesiredPrice = totalDesiredPrice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBoockedDate() {
        return boockedDate;
    }

    public void setBoockedDate(String boockedDate) {
        this.boockedDate = boockedDate;
    }

    public String getCheck_In_Date() {
        return check_In_Date;
    }

    public void setCheck_In_Date(String check_In_Date) {
        this.check_In_Date = check_In_Date;
    }

    public String getCheck_Out_Date() {
        return check_Out_Date;
    }

    public void setCheck_Out_Date(String check_Out_Date) {
        this.check_Out_Date = check_Out_Date;
    }

    public String getDesiredPrice() {
        return desiredPrice;
    }

    public void setDesiredPrice(String desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return customerName + "," + roomType + "," + boockedDate + "," + check_In_Date + "," + check_Out_Date + "," + desiredPrice + "," + totalDesiredPrice + "," + services;
    }

}
