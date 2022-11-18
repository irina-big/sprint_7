package model;

import java.util.List;

public class Order {
    private String firstName;
    private String lastName;
    private String address	;
    private String metroStation;
    private String phone;
    private String  rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;
    public Order() {
        this.firstName = "Анна";
        this.lastName = "Покровская";
        this.address = "Пушкинская, 78";
        this.metroStation = "Чистые пруды";
        this.phone = "+79289998877";
        this.rentTime = "3";
        this.deliveryDate = "2022-11-23";
        this.comment = "Вечером";
        this.color = List.of();
    }
    public Order(String firstName, String lastName,     String address, String metroStation, String phone,
                 String rentTime,  String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRentTime() { return rentTime; }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}