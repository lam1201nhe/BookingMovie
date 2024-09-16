/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author msi
 */
public class Inforuser {

    private int id, id_acc;
    private String full_name;
    private String dob;
    private String address;
    private String phone;
    private String gender;
    private String role_name;
    private String img;

    public Inforuser() {
    }

    public Inforuser(int id, int id_acc, String full_name, String dob, String address, String phone, String gender, String role_name, String img) {
        this.id = id;
        this.id_acc = id_acc;
        this.full_name = full_name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.role_name = role_name;
        this.img = img;
    }

    public Inforuser(int id_acc, String full_name, String address, String phone, String gender, String role_name) {
        this.id_acc = id_acc;
        this.full_name = full_name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.role_name = role_name;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_acc() {
        return id_acc;
    }

    public void setId_acc(int id_acc) {
        this.id_acc = id_acc;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
