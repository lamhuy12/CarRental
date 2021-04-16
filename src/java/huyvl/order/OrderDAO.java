/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.order;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author HUYVU
 */
public class OrderDAO implements Serializable {

    public boolean createOrder(String orderID, String email,
            float total, Date rentalDate, Date returnDate, boolean status, Date dateOfCreate, String discountID) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Orders(OrderID, Email, Total, RentalDate, ReturnDate, Status, DateCreate, DiscountID) "
                        + "values(?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, email);
                stm.setFloat(3, total);
                stm.setTimestamp(4, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(5, new Timestamp(returnDate.getTime()));
                stm.setBoolean(6, status);
                stm.setTimestamp(7, new Timestamp(dateOfCreate.getTime()));
                stm.setString(8, discountID);
                check = stm.executeUpdate() > 0;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public String getLastOrderIDByUser(String email) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderID = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select OrderID from Orders "
                        + "Where DateCreate = (Select MAX(DateCreate) "
                        + "From Orders where Email = ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    orderID = rs.getString("OrderID");
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderID;
    }
    
    public boolean createOrderDetail(String orderDetailID, String orderID, String carID, int amount, float price, String feedbackID) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                    String sql = "Insert into OrderDetail(OrderDetailID, OrderID, CarID, Amount, Price) "
                            + "values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderDetailID);
                stm.setString(2, orderID);
                stm.setString(3, carID);
                stm.setInt(4, amount);
                stm.setFloat(5, price);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
}
