package dal;

import static com.mysql.cj.conf.PropertyKey.logger;
import database.DBContext;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.MenuDaily2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;

/**
 * Lớp lấy dữ liệu từ database và xử lý dữu liệu
 *
 * @Phiên Bản : 1.0 04/06/2023
 * @Tác giả: Trịnh Minh Tân
 */
public class HomeDAO extends DBContext {

    /**
     * Lấy dữ liệu thức ăn từ bảng database menudaily
     */
    public List<MenuDaily2> getFoodMenu() {
        Connection con = DBContext.getConnection();
        List<MenuDaily2> list = new ArrayList<>();
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

    /**
     * Lấy danh sách dữ liệu thức ăn có giảm giá
     */
    public List<MenuDaily2> getFoodSale(List<MenuDaily2> foodmenu) {

        ArrayList<MenuDaily2> foodsale = new ArrayList<MenuDaily2>();

        for (int i = 0; i < foodmenu.size(); i++) {
            if (foodmenu.get(i).getDiscout() < 1) {
                foodsale.add(new MenuDaily2(foodmenu.get(i).getId(),
                        foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(),
                        foodmenu.get(i).getDiscout()));
            }
        }

        Collections.sort(foodsale, new Comparator<MenuDaily2>() {
            @Override
            public int compare(MenuDaily2 o1, MenuDaily2 o2) {
                return Float.compare(o1.getDiscout(), o2.getDiscout());
            }
        });
        return foodsale;
    }

    /**
     * Lấy danh sách dữ liệu món có giá rẻ so với mặt bằng chung món ở quán
     */
    public List<MenuDaily2> getFoodCheap(List<MenuDaily2> foodmenu) {

        int priceneeded = getPriceNeeded(foodmenu);

        ArrayList<MenuDaily2> foodcheap = new ArrayList<MenuDaily2>();

        for (int i = 0; i < foodmenu.size(); i++) {
            if (foodmenu.get(i).getPrice_final() <= priceneeded) {
                foodcheap.add(new MenuDaily2(foodmenu.get(i).getId(),
                        foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(),
                        foodmenu.get(i).getPrice_final()));
            }
        }

        Collections.sort(foodcheap, new Comparator<MenuDaily2>() {
            @Override
            public int compare(MenuDaily2 o1, MenuDaily2 o2) {
                return o1.getPrice_final() - o2.getPrice_final();
            }
        });
        return foodcheap;
    }

    /**
     * Lấy giá trị giá rẻ bằng trung bình cộng của một nửa của tất cả món khác
     */
    public int getPriceNeeded(List<MenuDaily2> foodmenu) {
        float count = 0;
        for (int i = 0; i < foodmenu.size(); i++) {
            count += (float) foodmenu.get(i).getPrice_final();
        }

        try {
            count = (((count / (foodmenu.size() + 1)) / 100) * 50);
        } catch (Exception e) {
            System.out.println("Error");
            count = 0;
        }
        return (int) count;
    }

    /**
     * Kiểm tra kí tự phải nằm trong vùng cho phép
     */
    public boolean checkInputSearch(String s) {

        if (s.length() >= 2 && s.length() <= 10) {
            return true;
        }
        return false;
    }

    /**
     * Lấy danh sách món ăn được chỉ định theo tên
     */
    public List<MenuDaily2> getSearch(List<MenuDaily2> foodmenu, String s) {

        ArrayList<MenuDaily2> foodsearch = new ArrayList<MenuDaily2>();

        for (int i = 0; i < foodmenu.size(); i++) {
            if (foodmenu.get(i).getName_food().toLowerCase().contains(s.toLowerCase())) {
                foodsearch.add(new MenuDaily2(foodmenu.get(i).getId(),
                        foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(),
                        foodmenu.get(i).getDiscout()));
            }
        }

        Collections.sort(foodsearch, new Comparator<MenuDaily2>() {
            @Override
            public int compare(MenuDaily2 o1, MenuDaily2 o2) {
                //return o1.get - o2.getPrice_final();
                return o1.getName_food().toLowerCase().compareTo(o2.getName_food().toLowerCase());
            }
        });
        return foodsearch;
    }

    /**
     * Nếu có hai khoảng trống liên tiếp thì xóa một cái đi
     */
    public String delSpace(String s) {
        s = s.trim();
        String[] t;
        String p = "";
        t = s.split("[ ]+");
        for (int i = 0; i < t.length; i++) {
            p += t[i] + " ";
        }
        return p;
    }

    /**
     * Xóa hết toàn bộ khoảng trống
     */
    public String delSpace2(String s) {
        s = s.trim();
        String[] t;
        String p = "";
        t = s.split("[ ]+");
        for (int i = 0; i < t.length; i++) {
            p += t[i];
        }
        return p;
    }

    /**
     * In ra màn hình theo kiểu phân trang hàng dọc
     */
    public List<MenuDaily2> setDisplay(List<MenuDaily2> foodmenu, int a, int b) {
        a = ((a * 3));

        ArrayList<MenuDaily2> displayfood = new ArrayList<MenuDaily2>();

        if (b == 1) {
            if (a < foodmenu.size()) {
                for (int i = 0; i < a; i++) {
                    float format = ((1 - foodmenu.get(i).getDiscout()) * 100);
                    int discout = (int) format;
                    displayfood.add(new MenuDaily2(foodmenu.get(i).getId(),
                            foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(), discout));

//                    displayfood.add(new MenuDaily2(foodmenu.get(i).getId(),
//                            foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(),
//                            foodmenu.get(i).getDiscout()));
                    //System.out.println(foodmenu.get(i).getName_food()+ foodmenu.get(i).getPrice_final());
                }
            } else if (a >= foodmenu.size()) {
                a = foodmenu.size();
                for (int i = 0; i < a; i++) {
                    float format = ((1 - foodmenu.get(i).getDiscout()) * 100);
                    int discout = (int) format;
                    displayfood.add(new MenuDaily2(foodmenu.get(i).getId(),
                            foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(), discout));
                    //System.out.println(foodmenu.get(i).getName_food()+ foodmenu.get(i).getPrice_final());
                }
            }
        } else if (b == 2) {
            if (a < foodmenu.size()) {
                for (int i = 0; i < a; i++) {
                    System.out.println(foodmenu.get(i).getName_food());
                    displayfood.add(new MenuDaily2(foodmenu.get(i).getId(),
                            foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(),
                            foodmenu.get(i).getPrice_final()));
                }

            } else if (a >= foodmenu.size()) {
                a = foodmenu.size();
                for (int i = 0; i < a; i++) {
                    System.out.println(foodmenu.get(i).getName_food());
                    displayfood.add(new MenuDaily2(foodmenu.get(i).getId(),
                            foodmenu.get(i).getName_food(), foodmenu.get(i).getImg(),
                            foodmenu.get(i).getPrice_final()));
                }

            }

        }
        return displayfood;
    }

    public static void main(String[] args) {
        HomeDAO c = new HomeDAO();

        List<MenuDaily2> list1 = c.getFoodMenu();
        List<MenuDaily2> list2 = c.getFoodCheap(list1);
        List<MenuDaily2> list3 = c.getFoodSale(list1);
        List<MenuDaily2> list = c.setDisplay(list2, 1, 1);
        //System.out.println(" gia trung binh la " + c.getPriceNeeded(list1));
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(" id = " + list.get(i).getId() + " name = " + list.get(i).getName_food() + " img = " + list.get(i).getImg() + " dis = " + list.get(i).getDiscout());
//        }
//        System.out.println(c.delSpace("   bắp        cải                to     "));

        System.out.println(c.delSpace2(" hom  nay lam sao"));

    }


}
