/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import database.DBContext;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.MenuDaily;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuDailyDAO extends DBContext {

    //lấy danh sách món theo Menu Daily
    public List<MenuDaily> getAll() {
        Connection con = DBContext.getConnection();
        List<MenuDaily> list = new ArrayList<>();
        //String sql="select * from Categories";
        String query = "SELECT f.id, f.name_food, f.describe_food, f.price_sell, m.quantity, m.discount, m.img " +
                "FROM MenuDaily m " +
                "JOIN Food f ON m.id_food = f.id " +
                "WHERE m.id_food = ?";
        try {
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new MenuDaily(
                        rs.getInt("id"),
                        rs.getString("name_food"),
                        rs.getString("describe_food"),
                        rs.getInt("price_sell"),
                        rs.getInt("quantity"),
                        rs.getFloat("discount"),
                        rs.getString("img")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        MenuDailyDAO menu = new MenuDailyDAO();
        List<MenuDaily> list = menu.getAll();

        System.out.println(list.get(0).getId() + "--" + list.get(0).getNameFood());

    }
}