///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dal;
//
//import database.DBContext;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import model.MenuDaily;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import static java.util.Collections.list;
//
//public class MenuDailyDAO extends DBContext {
//
////    Danh sách các danh mục (categories) muốn truy vấn
////    List<Integer> categoryIds = Arrays.asList(1, 2, 3);
////    Tạo chuỗi điều kiện cho danh mục (category) trong câu truy vấn
////    String categoryCondition = "";
////        for (int categoryId : categoryIds) {
////        if (!categoryCondition.isEmpty()) {
////            categoryCondition += " OR ";
////        }
////        categoryCondition += "food.id_category = " + categoryId;
////    }
//
//    //lấy danh sách món theo Menu Daily
//    public List<MenuDaily> getFoodMenu() {
//        Connection con = DBContext.getConnection();
//        List<MenuDaily> foodMenu = new ArrayList<>();
//        //String sql="select * from Categories";
//        String sql = "select food.id, "
//                + "food.name_food, "
//                + "food.describe_food, "
//                + "food.price_sell, "
//                + "food.img, "
//                + "menudaily.discout, "
//                + "menudaily.quantity, "
//                + "food.price_sell*menudaily.discout price_final "
//                + "from food, menudaily "
//                + "where menudaily.id_food = food.id ";
//        // + "order by menudaily.discout DESC";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                list.add(new MenuDaily(
//                        rs.getInt("id"),
//                        rs.getString("name_food"),
//                        rs.getString("describe_food"),
//                        rs.getInt("price_sell"),
//                        rs.getString("img"),
//                        rs.getFloat("discout"),
//                        rs.getInt("quantity"),
//                        rs.getInt("price_final")
//                ));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return foodMenu;
//    }
//
//    //String selectedCategories cho chuỗi tổ hợp filter
//    //String sortType cho kiểu sort
//    public List<MenuDaily> getFoodCategogy(selectedCategories, sortType) {
//        Connection con = DBContext.getConnection();
//        List<MenuDaily> foodMenuCategory = new ArrayList<>();
//
//        //String sql="select * from Categories";
//        String categoryCondition = ""; // Điều kiện lọc theo danh mục
//        if (selectedCategories != null && selectedCategories.length > 0) {
//            categoryCondition = "food.id_category IN (" + Arrays.stream(selectedCategories).collect(Collectors.joining(",")) + ")";
//        }
//
//        // Sắp xếp theo giá tăng dần hoặc giảm dần
//        String orderBy = ""; // Mệnh đề sắp xếp
//        if (sortType.equals("asc")) {
//            orderBy = "ORDER BY food.price_final ASC";
//        } else if (sortType.equals("desc")) {
//            orderBy = "ORDER BY food.price_final DESC";
//        }
//
//        // Câu truy vấn chính
//        String sql = "SELECT food.id, "
//                + "food.name_food, "
//                + "food.describe_food, "
//                + "food.price_sell, "
//                + "food.img, "
//                + "food.id_category"
//                + "menudaily.discout, "
//                + "menudaily.quantity, "
//                + "food.price_sell * menudaily.discout AS price_final "
//                + "FROM food, menudaily "
//                + "WHERE menudaily.id_food = food.id ";
//
//        if (!categoryCondition.isEmpty()) {
//            sql += "AND (" + categoryCondition + ") ";
//        }
//
//        sql += orderBy;
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                list.add(new MenuDaily(
//                        rs.getInt("id"),
//                        rs.getString("name_food"),
//                        rs.getString("describe_food"),
//                        rs.getInt("price_sell"),
//                        rs.getString("img"),
//                        rs.getFloat("discout"),
//                        rs.getInt("quantity"),
//                        rs.getInt("price_final")
//                ));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return foodMenuCategory;
//    }
//
//    public List<MenuDaily> getFoodIncrease(List<MenuDaily> foodmenu) {
//
//        ArrayList<MenuDaily> foodincrease = new ArrayList<MenuDaily>();
//
//        for (int i = 0; i < foodmenu.size(); i++) {
//            if (foodmenu.get(i).getDiscout() < 1) {
//                foodsale.add(new MenuDaily(foodmenu.get(i).getId(),
//                        foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(),
//                        foodmenu.get(i).getDiscout()));
//            }
//        }
//
//    public List<MenuDaily> getFoodCategoryByPage(int page, int itemsPerPage, String[] selectedCategories, String sortType)
//        {
//            Connection con = DBContext.getConnection();
//            List<MenuDaily> foodMenuPaging = new ArrayList<>();
//
//            // Xác định vị trí bắt đầu của trang
//            int startIndex = (page - 1) * itemsPerPage;
//
//            // Xây dựng câu truy vấn
//            String categoryCondition = ""; // Điều kiện lọc theo danh mục
//            if (selectedCategories != null && selectedCategories.length > 0) {
//                categoryCondition = "food.id_category IN (" + Arrays.stream(selectedCategories).collect(Collectors.joining(",")) + ")";
//            }
//
//            // Sắp xếp theo giá tăng dần hoặc giảm dần
//            String orderBy = ""; // Mệnh đề sắp xếp
//            if (sortType.equals("asc")) {
//                orderBy = "ORDER BY food.price_final ASC";
//            } else if (sortType.equals("desc")) {
//                orderBy = "ORDER BY food.price_final DESC";
//            }
//
//            // Câu truy vấn chính
//            String sql = "SELECT food.id, "
//                    + "food.name_food, "
//                    + "food.describe_food, "
//                    + "food.price_sell, "
//                    + "food.img, "
//                    + "food.id_category, "
//                    + "menudaily.discout, "
//                    + "menudaily.quantity, "
//                    + "food.price_sell * menudaily.discout AS price_final "
//                    + "FROM food, menudaily "
//                    + "WHERE menudaily.id_food = food.id ";
//
//            if (!categoryCondition.isEmpty()) {
//                sql += "AND (" + categoryCondition + ") ";
//            }
//
//            sql += orderBy;
//            sql += " LIMIT ?, ?";
//
//            try {
//                PreparedStatement st = con.prepareStatement(sql);
//                st.setInt(1, startIndex);
//                st.setInt(2, itemsPerPage);
//                ResultSet rs = st.executeQuery();
//                while (rs.next()) {
//                    foodMenuPaging.add(new MenuDaily(
//                            rs.getInt("id"),
//                            rs.getString("name_food"),
//                            rs.getString("describe_food"),
//                            rs.getInt("price_sell"),
//                            rs.getString("img"),
//                            rs.getFloat("discout"),
//                            rs.getInt("quantity"),
//                            rs.getInt("price_final")
//                    ));
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//            return foodMenuPaging;
//        }
//
//    public List<MenuDaily> setDisplay(List<MenuDaily> foodMenu, int a, int b) {
//        a = ((a * 3) - 1);
//
//        ArrayList<MenuDaily> displayFood = new ArrayList<MenuDaily>();
//
//            if (b == 1) {
//                if (a < foodMenu.size()) {
//                    for (int i = 0; i < a; i++) {
//                        displayFood.add(new MenuDaily(foodMenu.get(i).getId(),
//                                foodMenu.get(i).getName_food(), foodMenu.get(i).getImg(),
//                                foodMenu.get(i).getDiscout()));
//
//                    }
//                } else if (a >= foodMenu.size()) {
//                    a = foodMenu.size();
//                    for (int i = 0; i < a; i++) {
//                        displayFood.add(new MenuDaily(foodMenu.get(i).getId(),
//                                foodMenu.get(i).getName_food(), foodMenu.get(i).getImg(),
//                                foodMenu.get(i).getDiscout()));
//                        //System.out.println(foodmenu.get(i).getName_food()+ foodmenu.get(i).getPrice_final());
//                    }
//                }
//            } else if (b == 2) {
//                if (a < foodMenu.size()) {
//                    for (int i = 0; i < a; i++) {
//                        System.out.println(foodMenu.get(i).getName_food());
//                        displayFood.add(new MenuDaily(foodMenu.get(i).getId(),
//                                foodMenu.get(i).getName_food(), foodMenu.get(i).getImg(),
//                                foodMenu.get(i).getPrice_final()));
//                    }
//
//                } else if (a >= foodMenu.size()) {
//                    a = foodMenu.size();
//                    for (int i = 0; i < a; i++) {
//                        System.out.println(foodMenu.get(i).getName_food());
//                        displayFood.add(new MenuDaily(foodMenu.get(i).getId(),
//                                foodMenu.get(i).getName_food(), foodMenu.get(i).getImg(),
//                                foodMenu.get(i).getPrice_final()));
//                    }
//
//                }
//
//            }
//            return displayFood;
//        }
//
//
//        public static void main(String[] args) {
//        MenuDailyDAO menu = new MenuDailyDAO();
//        List<MenuDaily> list = menu.getAll();
//
//        System.out.println(list.get(0).getId() + "--" + list.get(0).getNameFood());
//
//    }
//}
