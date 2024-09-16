/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author msi
 */
public class OrderDetail {

    private int id;
    private String full_name;
    private String address;
    private String phone;
    private String timegiao;
    private String status_order;
    private int quantity;
    private int price;
    private String name_food;

    public OrderDetail() {
    }

    public OrderDetail(int id, String full_name, String address, String phone, String timegiao, String status_order, int quantity, int price, String name_food) {
        this.id = id;
        this.full_name = full_name;
        this.address = address;
        this.phone = phone;
        this.timegiao = timegiao;
        this.status_order = status_order;
        this.quantity = quantity;
        this.price = price;
        this.name_food = name_food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimegiao() {
        return timegiao;
    }

    public void setTimegiao(String timegiao) {
        this.timegiao = timegiao;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

}
