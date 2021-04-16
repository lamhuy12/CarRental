/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.orderDetail;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class OrderDetailDAO implements Serializable {

    public boolean createOrderDetail(String orderDetailID, String orderID, String carID, int amount, float price, String feedbackID) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into OrderDetail(OrderDetailID, OrderID, CarID, Amount, Price, FeedbackID) "
                        + "values (?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderDetailID);
                stm.setString(2, orderID);
                stm.setString(3, carID);
                stm.setInt(4, amount);
                stm.setFloat(5, price);
                stm.setString(6, feedbackID);
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

    public boolean updateOrderDetail(String feedbackID, String orderDetailID)
            throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update OrderDetail \n"
                        + "set FeedbackID=?\n"
                        + "where OrderDetailID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, feedbackID);
                stm.setString(2, orderDetailID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
}
