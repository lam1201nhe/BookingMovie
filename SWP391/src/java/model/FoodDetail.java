/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author msi
 */
public class FoodDetail {
       
    private int id, id_acc, id_food, rate;
    private String full_name;
    private String comment_use;
    private String time_comment;

    public FoodDetail() {
    }

    public FoodDetail(int id, int id_acc, int id_food, int rate, String full_name, String comment_use, String time_comment) {
        this.id = id;
        this.id_acc = id_acc;
        this.id_food = id_food;
        this.rate = rate;
        this.full_name = full_name;
        this.comment_use = comment_use;
        this.time_comment = time_comment;
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

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getComment_use() {
        return comment_use;
    }

    public void setComment_use(String comment_use) {
        this.comment_use = comment_use;
    }

    public String getTime_comment() {
        return time_comment;
    }

    public void setTime_comment(String time_comment) {
        this.time_comment = time_comment;
    }


    
        
}
