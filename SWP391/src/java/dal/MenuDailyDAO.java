/*
}}}}
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

import model.MenuDaily;

public class MenuDailyDAO extends DBContext {
    //lấy danh sách món theo Menu Daily
    public List<MenuDaily> getFoodMenu() {
        Connection con = DBContext.getConnection();
        List<MenuDaily> foodMenu = new ArrayList<>();
        //String sql="select * from Categories";
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
                foodMenu.add(new MenuDaily(
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
        return foodMenu;
    }

    //String selectedCategories cho chuỗi tổ hợp filter
    //String sortType cho kiểu sort
    public List<MenuDaily> getFoodCategogy(String selectedCategories, String sortType) {
               Connection con = DBContext.getConnection();
        List<MenuDaily> foodMenuCategory = new ArrayList<>();

        //String sql="select * from Categories";
        String categoryCondition = ""; // Điều kiện lọc theo danh mục
        categoryCondition = "food.id_category = " + selectedCategories;

        // Sắp xếp theo giá tăng dần hoặc giảm dần
        String orderBy = ""; // Mệnh đề sắp xếp
        if (sortType != null) {
            if (sortType.equals("1")) {
                orderBy = " food.price_sell < 100000";
            } else if (sortType.equals("2")) {
                orderBy = " food.price_sell >= 100000 AND food.price_sell <= 200000";
            } else if (sortType.equals("3")) {
                // Lọc nhóm giá dưới 100,000
                categoryCondition += " food.price_sell >= 200000 AND food.price_sell <= 300000";
            } else if (sortType.equals("4")) {
                // Lọc nhóm giá trên 100,000
                categoryCondition += " food.price_sell >= 300000";
            } else if (sortType.equals("5")) {
                orderBy = "ORDER BY food.price_sell ASC";
            } else if (sortType.equals("6")) {
                orderBy = "ORDER BY food.price_sell DESC";
            }
        }

        // Câu truy vấn chính
        String sql = "SELECT food.id, "
                + "food.name_food, "
                + "food.describe_food, "
                + "food.price_sell, "
                + "food.img, "
                + "food.id_category,"
                + "menudaily.discout, "
                + "menudaily.quantity, "
                + "food.price_sell * menudaily.discout AS price_final "
                + "FROM food, menudaily "
                + "WHERE menudaily.id_food = food.id ";

        sql += "AND " + categoryCondition;

        sql += orderBy;

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                foodMenuCategory.add(new MenuDaily(
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
        
        return foodMenuCategory;
    }

    
    public List<MenuDaily> getFoodCategogy(String selectedCategories) {
        Connection con = DBContext.getConnection();
        List<MenuDaily> foodMenuCategory = new ArrayList<>();

        //String sql="select * from Categories";
        String categoryCondition = ""; 
        categoryCondition = "food.id_category = " + selectedCategories;

        // Câu truy vấn chính
        String sql = "SELECT food.id, "
                + "food.name_food, "
                + "food.describe_food, "
                + "food.price_sell, "
                + "food.img, "
                + "food.id_category,"
                + "menudaily.discout, "
                + "menudaily.quantity, "
                + "food.price_sell * menudaily.discout AS price_final "
                + "FROM food, menudaily "
                + "WHERE menudaily.id_food = food.id ";
        
        sql += "AND " + categoryCondition;

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                foodMenuCategory.add(new MenuDaily(
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
        return foodMenuCategory;
    }
    
    public List<MenuDaily> getFoodPrice(String sortType) {
        Connection con = DBContext.getConnection();
        List<MenuDaily> foodMenuPrice = new ArrayList<>();

        // Sắp xếp theo giá tăng dần hoặc giảm dần
        String orderBy = ""; // Mệnh đề sắp xếp
        if (sortType != null) {
            if (sortType.equals("1")) {
                orderBy = " food.price_sell < 100000";
            } else if (sortType.equals("2")) {
                orderBy = " food.price_sell >= 100000 AND food.price_sell <= 200000";
            } else if (sortType.equals("3")) {
                // Lọc nhóm giá dưới 100,000
                orderBy = " food.price_sell >= 200000 AND food.price_sell <= 300000";
            } else if (sortType.equals("4")) {
                // Lọc nhóm giá trên 100,000
                orderBy = " food.price_sell >= 300000";
            } else if (sortType.equals("5")) {
                orderBy = "food.price_sell > -1";
            } else if (sortType.equals("6")) {
                orderBy = "ORDER BY food.price_sell DESC";
            }
        }

        // Câu truy vấn chính
        String sql = "SELECT food.id, "
                + "food.name_food, "
                + "food.describe_food, "
                + "food.price_sell, "
                + "food.img, "
                + "food.id_category,"
                + "menudaily.discout, "
                + "menudaily.quantity, "
                + "food.price_sell * menudaily.discout AS price_final "
                + "FROM food, menudaily "
                + "WHERE menudaily.id_food = food.id ";
        
        sql += "AND " + orderBy;


        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                foodMenuPrice.add(new MenuDaily(
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
        return foodMenuPrice;
    }

    public static void main(String[] args) {
        MenuDailyDAO menu = new MenuDailyDAO();
        List<MenuDaily> list = menu.getFoodMenu();

        System.out.println(list.get(0).getId() + "--" + list.get(0).getName_food());

    }
}
