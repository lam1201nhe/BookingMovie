package dal;

import database.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;
import model.Account;

/**
 * Lớp lấy dữ liệu từ database và xử lý dữu liệu
 *
 * @Phiên Bản : 1.0 04/06/2023
 * @Tác giả: Nguyễn Văn Thịnh
 */
public class LoginDAO extends DBContext {

    /**
     * Lấy dữ liệu tài khoản với username và pass
     */
    public Account getCheckAcc(String username, String pass) {
        Connection con = DBContext.getConnection();

        String sql = "select acc.id, "
                + "acc.username, "
                + "acc.pass, "
                + "acc.role_name "
                + "from acc where acc.username=? and acc.pass=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("role_name"));
                return a;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Lấy giá trị lớn nhất của id
     */
    public int getCountAcc() {
        Connection con = DBContext.getConnection();

        int a = 0;

        String sql = "select max(id) id from acc";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }

    /**
     * Kiểm tra chuỗi kí tự có dấu hay không
     */
    public boolean checkInput(String s1) {
        LoginDAO c = new LoginDAO();
        String s2 = s1;
        s1 = c.removeAccent(s1);
        if (s1.equalsIgnoreCase(s2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Xóa dấu kí tự trong chuỗi
     */
    public String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    /**
     * kiểm tra chuỗi có đủ kí tự chỉ định
     */
    public boolean checkInputRegiter(String s) {

        if (s.length() >= 6 && s.length() <= 19) {
            return true;
        }
        return false;
    }

    /**
     * Tạo ra tài khoản mới trong database
     */
    public boolean getCreateAcc(Account c) {

        Connection con = DBContext.getConnection();

        String sql = "INSERT INTO `swp391tmtnew`.`acc` "
                + "(`id`, `username`, `pass`, `role_name`) \n"
                + "VALUES (?, ? , ? , ?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.setString(2, c.getUsername());
            st.setString(3, c.getPass());
            st.setString(4, c.getRole_name());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Kiểm tra tên user đã tồn tại chưa
     */
    public boolean getCheckUser(String username) {
        Connection con = DBContext.getConnection();

        boolean dk = false;

        String sql = "select acc.username "
                + "from acc where acc.username=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getString("username").equalsIgnoreCase(username)) {
                    dk = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dk;
    }

    /**
     * Thay đổi mật khẩu mới tại username chỉ định
     */
    public boolean getUpdatePass(String username, String pass) {
        Connection con = DBContext.getConnection();

        String sql = "update acc "
                + "set pass = ? where acc.username=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    /**
     * Tạo ra mật mã để thay đổi mật khẩu 
     */
    public boolean getNumberReset(String username) {

        LoginDAO c = new LoginDAO();

        int number_reset = c.getNumberRandom();

        Connection con = DBContext.getConnection();

        String sql = "update acc "
                + "set number_reset = ? where acc.username=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, number_reset);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    
    /**
     * Ramdom một số ngẫu nhiên
     */
    public int getNumberRandom() {
        Random rand = new Random();
        int upperBound = 999999;
        int lowerBound = 100000;
        int n = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        return n;
    }

    public static void main(String[] args) {

//        LoginDAO c = new LoginDAO();
//
//        //   System.out.println(c.getUpdatePass("lmhsdf", "123456789"));
//        System.err.println(c.getCheckUser("minhtan"));
//
//        System.out.println(c.getCountAcc());
//
//        Account b = new Account((c.getCountAcc() + 1), "minhtanav", "12345678", "customer");
//        c.getCreateAcc(b);


        LoginDAO obj = new LoginDAO();

        Account a = obj.getCheckAcc("minhtanav", "123456789"); // Kiểm tra có tài khoản ko
        
        System.out.println(a.getUsername());

    }

}
