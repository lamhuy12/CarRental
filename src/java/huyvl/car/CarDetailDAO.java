/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.car;

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
public class CarDetailDAO implements Serializable {

    public List<CarDetailDTO> listAllCar(int rowOfPages, int pageNumber) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                int startLine = rowOfPages * pageNumber;
                String sql = "select CarID, CarName, Color, Year, CateID, Price, Quantity\n"
                        + "from CarDetail\n"
                        + "order by CarID offset ? rows fetch next ? rows only";
                stm = con.prepareStatement(sql);
                stm.setInt(1, startLine);
                stm.setInt(2, rowOfPages);
                rs = stm.executeQuery();
                List<CarDetailDTO> allCar = null;
                while (rs.next()) {
                    String carID = rs.getString("CarID");
                    String carName = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int Year = rs.getInt("Year");
                    String cateID = rs.getString("CateID");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    CarDetailDTO dto = new CarDetailDTO(carID, carName, color, Year, cateID, price, quantity);

                    if (allCar == null) {
                        allCar = new ArrayList<>();
                    }

                    allCar.add(dto);
                }
                return allCar;
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

    public CarDetailDTO findPrimaryKey(String carIDValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CarDetailDTO dto = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select CarID, CarName, Color, Year, CateID, Price, Quantity\n"
                        + "                        from CarDetail\n"
                        + "where CarID =?";

                stm = con.prepareStatement(sql);
                stm.setString(1, carIDValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("CarID");
                    String carName = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int Year = rs.getInt("Year");
                    String cateID = rs.getString("CateID");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    dto = new CarDetailDTO(carID, carName, color, Year, cateID, price, quantity);
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
        return dto;
    }

    public List<CarDetailDTO> searchCarWithName(String carNameValue, Date rentalDate, Date returnDate, int quantityValue, int rowOfPages, int pageNumber) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                int startLine = rowOfPages * pageNumber;
                String sql = "SELECT CarDetail.CarID, CarDetail.CarName, CarDetail.Color, CarDetail.Year, CarDetail.CateID, CarDetail.Price, CarDetail.Quantity\n"
                        + "FROM CarDetail\n"
                        + "WHERE CarDetail.CarName LIKE ?  AND  CarDetail.Quantity >= ? AND CarDetail.CarID NOT IN\n"
                        + "(\n"
                        + "	SELECT CarDetail.CarID\n"
                        + "	FROM CarDetail\n"
                        + "	JOIN OrderDetail\n"
                        + "	ON CarDetail.CarID=OrderDetail.CarID \n"
                        + "	JOIN Orders \n"
                        + "	ON Orders.OrderID=OrderDetail.OrderID\n"
                        + "	WHERE  \n"
                        + "	Orders.Status = 1 AND (CarDetail.Quantity - OrderDetail.Amount < ?) AND\n"
                        + "	(( Orders.RentalDate  <= ? AND Orders.ReturnDate >= ?) \n"
                        + "	OR (Orders.RentalDate < ? AND Orders.ReturnDate >= ? ) \n"
                        + "	OR (? <= Orders.RentalDate AND ? >= Orders.ReturnDate))\n"
                        + ") \n"
                        + "ORDER BY CarDetail.CarID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + carNameValue + "%");
                stm.setInt(2, quantityValue);
                stm.setInt(3, quantityValue);
                stm.setTimestamp(4, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(5, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(6, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(7, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(8, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(9, new Timestamp(returnDate.getTime()));
                stm.setInt(10, startLine);
                stm.setInt(11, rowOfPages);
                rs = stm.executeQuery();
                List<CarDetailDTO> searchByName = null;
                CarDetailDAO dao = new CarDetailDAO();
                while (rs.next()) {
                    String carID = rs.getString("CarID");
                    String carName = rs.getString("CarName");
                    String color = rs.getString("Color");
                    String cateID = rs.getString("CateID");
                    int Year = rs.getInt("Year");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    CarDetailDTO dto = new CarDetailDTO(carID, carName, color, Year, cateID, price, quantity);

                    if (quantity > dao.QuantityCarIsRent(rentalDate, returnDate, carName)) {
                        if (searchByName == null) {
                            searchByName = new ArrayList<>();
                        }
                        searchByName.add(dto);
                    }

                }

                return searchByName;
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

    public List<CarDetailDTO> searchCarWithCate(String cateIDValue, Date rentalDate, Date returnDate, int quantityValue, int rowOfPages, int pageNumber) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                int startLine = rowOfPages * pageNumber;
                String sql = "SELECT CarDetail.CarID, CarDetail.CarName, CarDetail.Color, CarDetail.Year, CarDetail.CateID, CarDetail.Price, CarDetail.Quantity\n"
                        + "FROM CarDetail\n"
                        + "WHERE CarDetail.CateID = ? AND  CarDetail.Quantity >= ? AND CarDetail.CarID NOT IN\n"
                        + "(\n"
                        + "	SELECT CarDetail.CarID\n"
                        + "	FROM CarDetail\n"
                        + "	JOIN OrderDetail\n"
                        + "	ON CarDetail.CarID=OrderDetail.CarID \n"
                        + "	JOIN Orders \n"
                        + "	ON Orders.OrderID=OrderDetail.OrderID\n"
                        + "	WHERE  \n"
                        + "	Orders.Status = 1 AND (CarDetail.Quantity - OrderDetail.Amount < ?) AND\n"
                        + "	(( Orders.RentalDate  <= ? AND Orders.ReturnDate >= ?) \n"
                        + "	OR (Orders.RentalDate < ? AND Orders.ReturnDate >= ? ) \n"
                        + "	OR (? <= Orders.RentalDate AND ? >= Orders.ReturnDate))\n"
                        + ") \n"
                        + "ORDER BY CarDetail.CarID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, cateIDValue);
                stm.setInt(2, quantityValue);
                stm.setInt(3, quantityValue);
                stm.setTimestamp(4, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(5, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(6, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(7, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(8, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(9, new Timestamp(returnDate.getTime()));
                stm.setInt(10, startLine);
                stm.setInt(11, rowOfPages);
                rs = stm.executeQuery();
                List<CarDetailDTO> searchByCate = null;
                while (rs.next()) {
                    String carID = rs.getString("CarID");
                    String carName = rs.getString("CarName");
                    String color = rs.getString("Color");
                    String cateID = rs.getString("CateID");
                    int Year = rs.getInt("Year");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    CarDetailDTO dto = new CarDetailDTO(carID, carName, color, Year, cateID, price, quantity);

                    if (searchByCate == null) {
                        searchByCate = new ArrayList<>();
                    }

                    searchByCate.add(dto);
                }
                return searchByCate;
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

    public List<CarDetailDTO> searchCarWithNameAndCate(String carNameValue, String cateIDValue, Date rentalDate, Date returnDate, int quantityValue, int rowOfPages, int pageNumber) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                int startLine = rowOfPages * pageNumber;
                String sql = "SELECT CarDetail.CarID, CarDetail.CarName, CarDetail.Color, CarDetail.Year, CarDetail.CateID, CarDetail.Price, CarDetail.Quantity\n"
                        + "FROM CarDetail\n"
                        + "WHERE CarDetail.CarName like ? AND CarDetail.CateID = ? AND  CarDetail.Quantity >= ? AND CarDetail.CarID NOT IN\n"
                        + "(\n"
                        + "	SELECT CarDetail.CarID\n"
                        + "	FROM CarDetail\n"
                        + "	JOIN OrderDetail\n"
                        + "	ON CarDetail.CarID=OrderDetail.CarID \n"
                        + "	JOIN Orders \n"
                        + "	ON Orders.OrderID=OrderDetail.OrderID\n"
                        + "	WHERE  \n"
                        + "	Orders.Status = 1 AND (CarDetail.Quantity - OrderDetail.Amount < ?) AND\n"
                        + "	(( Orders.RentalDate  <= ? AND Orders.ReturnDate >= ?) \n"
                        + "	OR (Orders.RentalDate < ? AND Orders.ReturnDate >= ? ) \n"
                        + "	OR (? <= Orders.RentalDate AND ? >= Orders.ReturnDate))\n"
                        + ") \n"
                        + "ORDER BY CarDetail.CarID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + carNameValue + "%");
                stm.setString(2, cateIDValue);
                stm.setInt(3, quantityValue);
                stm.setInt(4, quantityValue);
                stm.setTimestamp(5, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(6, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(7, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(8, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(9, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(10, new Timestamp(returnDate.getTime()));
                stm.setInt(11, startLine);
                stm.setInt(12, rowOfPages);
                rs = stm.executeQuery();
                List<CarDetailDTO> searchByNamAndCate = null;
                while (rs.next()) {
                    String carID = rs.getString("CarID");
                    String carName = rs.getString("CarName");
                    String color = rs.getString("Color");
                    String cateID = rs.getString("CateID");
                    int Year = rs.getInt("Year");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    CarDetailDTO dto = new CarDetailDTO(carID, carName, color, Year, cateID, price, quantity);

                    if (searchByNamAndCate == null) {
                        searchByNamAndCate = new ArrayList<>();
                    }

                    searchByNamAndCate.add(dto);
                }
                return searchByNamAndCate;
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

    public int QuantityCarIsRent(Date rentalDate, Date returnDate, String carIDValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rentalQuantity = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT SUM(Amount) AS SumRental\n"
                        + "FROM CarDetail \n"
                        + "JOIN OrderDetail\n"
                        + "ON CarDetail.CarID = OrderDetail.CarID \n"
                        + "JOIN Orders ON Orders.OrderID = OrderDetail.OrderID\n"
                        + "WHERE Orders.Status = 1 \n"
                        + "	AND (( Orders.RentalDate  <= ? AND Orders.ReturnDate >= ?) \n"
                        + "	OR (Orders.RentalDate < ? AND Orders.ReturnDate >= ? ) \n"
                        + "	OR (? <= Orders.RentalDate AND ? >= Orders.ReturnDate))\n"
                        + "	AND CarDetail.CarID = ?\n"
                        + "	";
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(2, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(3, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(4, new Timestamp(returnDate.getTime()));
                stm.setTimestamp(5, new Timestamp(rentalDate.getTime()));
                stm.setTimestamp(6, new Timestamp(returnDate.getTime()));
                stm.setString(7, carIDValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    rentalQuantity = rs.getInt("SumRental");
                    return rentalQuantity;
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
        return rentalQuantity;
    }

    public int findByCarID(String carIDValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rentalQuantity = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "	select Quantity\n"
                        + "	from CarDetail\n"
                        + "	where CarID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, carIDValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    rentalQuantity = rs.getInt("Quantity");
                    return rentalQuantity;
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
        return rentalQuantity;

    }

}
