/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.history;

import huyvl.order.OrderDTO;
import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class HistoryDAO implements Serializable {

    List<OrderDTO> listSearchHistoryByDate;

    public List<OrderDTO> getListSearchHistoryByDate() {
        return listSearchHistoryByDate;
    }

    public void SearchHistoryByDate(Date rentalDateValue, String emailValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                    String sql = "select OrderID, Email, Total, RentalDate, ReturnDate, Status, DateCreate, DiscountID\n"
                            + "from Orders\n"
                            + "where RentalDate = ? AND Email = ?";
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, new Timestamp(rentalDateValue.getTime()));
                stm.setString(2, emailValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    String email = rs.getString("Email");
                    float total = rs.getFloat("Total");
                    Date rentalDate = rs.getDate("RentalDate");
                    Date returnDate = rs.getDate("ReturnDate");
                    boolean status = rs.getBoolean("Status");
                    Date dateCreate = rs.getDate("DateCreate");
                    String discountID = rs.getString("DiscountID");
                    OrderDTO dto = new OrderDTO(orderID, email, total, rentalDate, returnDate, status, dateCreate, discountID);

                    if (this.listSearchHistoryByDate == null) {
                        this.listSearchHistoryByDate = new ArrayList<>();
                    }

                    this.listSearchHistoryByDate.add(dto);
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
    }

    List<SearchHistoryByCarNameDTO> listSearchHistoryByCarname;

    public List<SearchHistoryByCarNameDTO> getListSearchHistoryByCarname() {
        return listSearchHistoryByCarname;
    }

    public void SearchHistoryByName(String carNameValue, String emailValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select O.OrderID, OD.OrderDetailID, C.CarID, C.CarName, O.RentalDate, O.ReturnDate, OD.Price, OD.Amount, O.DateCreate, O.Status\n"
                        + "from Orders O\n"
                        + "JOIN OrderDetail OD\n"
                        + "ON O.OrderID = OD.OrderID\n"
                        + "JOIN CarDetail C \n"
                        + "ON C.CarID = OD.CarID\n"
                        + "where C.CarName like ? and O.Email= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + carNameValue + "%");
                stm.setString(2, emailValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderDetailID = rs.getString("OrderDetailID");
                    String carID = rs.getString("CarID");
                    String carName = rs.getString("CarName");
                    String orderID = rs.getString("OrderID");
                    Date rentalDate = rs.getDate("RentalDate");
                    Date returnDate = rs.getDate("ReturnDate");
                    float price = rs.getFloat("Price");
                    int amount = rs.getInt("Amount");
                    boolean status = rs.getBoolean("Status");
                    Date dateCreate = rs.getDate("DateCreate");
                    SearchHistoryByCarNameDTO dto = new SearchHistoryByCarNameDTO(orderID, orderDetailID, carID, carName, rentalDate, returnDate, price, amount, dateCreate, status);

                    if (this.listSearchHistoryByCarname == null) {
                        this.listSearchHistoryByCarname = new ArrayList<>();
                    }

                    this.listSearchHistoryByCarname.add(dto);
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
    }

    List<SearchHistoryByCarNameDTO> listSearchHistoryAll;

    public List<SearchHistoryByCarNameDTO> getListSearchHistoryAll() {
        return listSearchHistoryAll;
    }

    public void SearchHistory(String carNameValue, Date rentalDateValue, String emailValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select O.OrderID, OD.OrderDetailID, C.CarID, C.CarName, O.RentalDate, O.ReturnDate, OD.Price, OD.Amount, O.DateCreate, O.Status\n"
                        + "                       from Orders O\n"
                        + "                       JOIN OrderDetail OD\n"
                        + "                       ON O.OrderID = OD.OrderID\n"
                        + "                        JOIN CarDetail C \n"
                        + "                        ON C.CarID = OD.CarID\n"
                        + "                        where C.CarName like ? and O.Email= ? and O.RentalDate = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + carNameValue + "%");
                stm.setString(2, emailValue);
                stm.setTimestamp(3, new Timestamp(rentalDateValue.getTime()));
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderDetailID = rs.getString("OrderDetailID");
                    String carID = rs.getString("CarID");
                    String carName = rs.getString("CarName");
                    String orderID = rs.getString("OrderID");
                    Date rentalDate = rs.getDate("RentalDate");
                    Date returnDate = rs.getDate("ReturnDate");
                    float price = rs.getFloat("Price");
                    int amount = rs.getInt("Amount");
                    boolean status = rs.getBoolean("Status");
                    Date dateCreate = rs.getDate("DateCreate");
                    SearchHistoryByCarNameDTO dto = new SearchHistoryByCarNameDTO(orderID, orderDetailID, carID, carName, rentalDate, returnDate, price, amount, dateCreate, status);

                    if (this.listSearchHistoryAll == null) {
                        this.listSearchHistoryAll = new ArrayList<>();
                    }

                    this.listSearchHistoryAll.add(dto);
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
    }

    public boolean DeleteOrder(String orderID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Orders "
                        + "set Status = 0 "
                        + "where OrderID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
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
