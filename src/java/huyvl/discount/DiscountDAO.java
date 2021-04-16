/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.discount;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class DiscountDAO implements Serializable {

    public int discountValue(String discountID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select Value\n"
                        + "from Discount\n"
                        + "where DiscountID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int value = rs.getInt("Value");
                    return value;
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
        return 0;
    }

    public Date discountStartDate(String discountID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select StartDate\n"
                        + "from Discount\n"
                        + "where DiscountID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Date startDate = rs.getDate("StartDate");
                    return startDate;
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
        return null;
    }

    public Date discountEndDate(String discountID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select EndDate\n"
                        + "from Discount\n"
                        + "where DiscountID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Date endDate = rs.getDate("EndDate");
                    return endDate;
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
        return null;
    }
}
