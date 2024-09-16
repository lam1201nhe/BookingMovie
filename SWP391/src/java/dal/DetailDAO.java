/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import database.DBContext;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.FoodDetail;
import model.MenuDaily;

/**
 *
 * @author msi
 */
public class DetailDAO {

    Connection con = DBContext.getConnection();

    public List<MenuDaily> DetailId(int id_food) {
        List<MenuDaily> list = new ArrayList<>();

        String sql = "select food.id, "
                + "food.name_food, "
                + "food.describe_food, "
                + "food.price_sell, "
                + "food.img, "
                + "menudaily.discout, "
                + "menudaily.quantity, "
                + "food.price_sell*menudaily.discout price_final "
                + "from food, menudaily "
                + "where menudaily.id_food = food.id and food.id = ?";
        // + "order by menudaily.discout DESC";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_food);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new MenuDaily(
                        rs.getInt("id"),
                        rs.getString("name_food"),
                        rs.getString("describe_food"),
                        rs.getInt("price_sell"),
                        rs.getString("img"),
                        rs.getFloat("discout"),
                        rs.getInt("quantity"),
                        rs.getInt("price_final")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    
    // phan ko sua
    public List<FoodDetail> getDetailFood(int id_food) {

        List<FoodDetail> list = new ArrayList<>();
        //String sql="select * from Categories";

        String sql = "select rate.id, "
                + "rate.id_acc, "
                + "rate.id_food, "
                + "rate.rate,"
                + "inforuser.full_name , "
                + "rate.comment_use, "
                + "rate.time_comment "
                + "from rate, inforuser "
                + "where rate.id_acc = inforuser.id_acc "
                + "and rate.id_food = ? "
                + "order by time_comment desc";
        // + "order by menudaily.discout DESC";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_food);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new FoodDetail(
                        rs.getInt("id"),
                        rs.getInt("id_acc"),
                        rs.getInt("id_food"),
                        rs.getInt("rate"),
                        rs.getString("full_name"),
                        rs.getString("comment_use"),
                        rs.getString("time_comment")
                )); 
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getAvg(int id_food) {

        int a = 0;

        String sql = "select avg(rate) "
                + "from rate "
                + "where rate.id_food = ?;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_food);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("avg(rate)");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }

    

    public static void main(String[] args) {
        DetailDAO obj = new DetailDAO();

//        System.out.println(obj.getDetailFood(6).get(1).getId() + obj.getDetailFood(6).get(1).getFull_name());
//        System.out.println(obj.getAvg(6));

                List<MenuDaily> list1 = obj.DetailId(6);
                
                System.out.println(list1.get(0).getName_food());
               
    }

}
