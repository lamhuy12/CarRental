/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.orderDetail;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class OrderDetailDTO implements Serializable{
    private String orderDetailID;
    private String orderID;
    private String carID;
    private int amount;
    private float price;
    private String feedbackID;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderDetailID, String orderID, String carID, int amount, float price, String feedbackID) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.carID = carID;
        this.amount = amount;
        this.price = price;
        this.feedbackID = feedbackID;
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
     * @return the feedbackID
     */
    public String getFeedbackID() {
        return feedbackID;
    }

    /**
     * @param feedbackID the feedbackID to set
     */
    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    
    
    
}
