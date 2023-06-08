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
import model.MenuDaily2;

/**
 *
 * @author msi
 */
public class DetailDAO {

    Connection con = DBContext.getConnection();

    public List<MenuDaily2> DetailId(int id_food) {
        List<MenuDaily2> list = new ArrayList<>();
             
        String sql = "select food.id, "
                + "food.name_food, "
                + "food.describe_food, "
                + "food.price_sell, "
                + "food.img, "
                + "menudaily.discout, "
                + "menudaily.quantity, "
                + "food.price_sell*menudaily.discout price_final "
                + "from food, menudaily "
                + "where menudaily.id_food = food.id ";
        // + "order by menudaily.discout DESC";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new MenuDaily2(
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

    public List<FoodDetail> getDetailFood(int id_food) {

        List<FoodDetail> list = new ArrayList<>();
        //String sql="select * from Categories";
        String sql = "select id, "
                + "id_acc, id_food, "
                + "rate, "
                + "comment_use, "
                + "time_comment "
                + "from rate "
                + "where id_food = ? "
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

        System.out.println(obj.getDetailFood(6).get(1).getId());
        System.out.println(obj.getAvg(6));

    }

}
