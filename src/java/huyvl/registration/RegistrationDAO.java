/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.registration;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select Status \n"
                        + "from Registration \n"
                        + "where Email = ? and Password = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    //role = rs.getString("Role");
                    boolean status = rs.getBoolean("Status");
                    return status;
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

    public String fullname(String email) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select Fullname "
                        + "from Registration "
                        + "where Email =?";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String fullname = rs.getString("Fullname");
                    return fullname;
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
    
    public boolean checkStatus(String email) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select Status "
                        + "from Registration "
                        + "where Email =?";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                while (rs.next()) {
                    
                    boolean status = rs.getBoolean("Status");
                    return status;
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

    public boolean createAccount(String email, String password,
            String fullname, String phone, String address, String role, boolean status, Date createDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Registration(Email, Password, Fullname, Phone, Address, Role, Status, CreateDate) "
                        + "Values(?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setString(4, phone);
                stm.setString(5, address);
                stm.setString(6, role);
                stm.setBoolean(7, status);
                stm.setTimestamp(8, new Timestamp(createDate.getTime()));
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public boolean UpdateStatus(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Registration\n"
                        + "set Status =1\n"
                        + "where Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
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
