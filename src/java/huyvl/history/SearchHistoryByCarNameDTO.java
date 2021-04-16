/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.history;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HUYVU
 */
public class SearchHistoryByCarNameDTO implements Serializable {

    private String orderID;
    private String orderDetailID;
    private String carID;
    private String carName;
    private Date rentalDate;
    private Date returnDate;
    private float price;
    private int amount;
    private Date dateCreate;
    private boolean status;

    public SearchHistoryByCarNameDTO() {
    }

    public SearchHistoryByCarNameDTO(String orderID, String orderDetailID, String carID, String carName, Date rentalDate, Date returnDate, float price, int amount, Date dateCreate, boolean status) {
        this.orderID = orderID;
        this.orderDetailID = orderDetailID;
        this.carID = carID;
        this.carName = carName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.price = price;
        this.amount = amount;
        this.dateCreate = dateCreate;
        this.status = status;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the orderDetailID
     */
    public String getOrderDetailID() {
        return orderDetailID;
    }

    /**
     * @param orderDetailID the orderDetailID to set
     */
    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    /**
     * @return the carID
     */
    public String getCarID() {
        return carID;
    }

    /**
     * @param carID the carID to set
     */
    public void setCarID(String carID) {
        this.carID = carID;
    }

    /**
     * @return the carName
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * @return the rentalDate
     */
    public Date getRentalDate() {
        return rentalDate;
    }

    /**
     * @param rentalDate the rentalDate to set
     */
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
