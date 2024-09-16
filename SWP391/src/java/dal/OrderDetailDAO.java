/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import database.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author msi
 */
public class OrderDetailDAO {

    public List<OrderDetail> getOrderDetail(int idDelivery, String status) {

        Connection con = DBContext.getConnection();
        List<OrderDetail> list = new ArrayList<>();
        //String sql="select * from Categories";
        String sql = "select oorder.id, "
                + "inforuser.full_name , "
                + "inforuser.address, "
                + "inforuser.phone , "
                + "oorder.timegiao, "
                + "oorder.status_order, "
                + "orderdetail.quantity, "
                + "orderdetail.price, "
                + "food.name_food "
                + "from oorder, inforuser, "
                + "orderdetail, food "
                + "where inforuser.id_acc = oorder.customer "
                + "and oorder.id_orderdetail = orderdetail.id "
                + "and orderdetail.id_food = food.id "
                + "and oorder.delivery = ? "
                + "and oorder.status_order like '" + status + "' "
                + "order by oorder.timegiao desc;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idDelivery);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("timegiao"),
                        rs.getString("status_order"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getString("name_food")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean getUpdateOrderDetail(int idOrder, String status, String time) {

        Connection con = DBContext.getConnection();
        String sql = "update oorder "
                + "set oorder.timegiao = ?, oorder.status_order = ?  where oorder.id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, time);
            st.setString(2, status);
            st.setInt(3, idOrder);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public String getTimeCurrent() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(date);
        return strDate;
    }
    

    public boolean getStatus(int idOrder) {
        Connection con = DBContext.getConnection();

        boolean dk = true;

        String sql = "select oorder.status_order "
                + "from oorder where oorder.id = ?";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idOrder);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getString("status_order").equalsIgnoreCase("ok")) {
                    dk = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dk;
    }
    
    
    public boolean getDKUpdateOrderDetail(int idOrder, String status){
        
        OrderDetailDAO obj = new OrderDetailDAO();
        
        String time = obj.getTimeCurrent();
        
        if(obj.getStatus(idOrder)){
            obj.getUpdateOrderDetail(idOrder, status, time);
            return true;
        } else {
            return false;
        }       
    }
    
    
    
    
    public static void main(String[] args) {
        
        OrderDetailDAO obj = new OrderDetailDAO();

        String time = obj.getTimeCurrent();
        System.out.println(obj.getDKUpdateOrderDetail(9, "bosua"));
        

    }

}
