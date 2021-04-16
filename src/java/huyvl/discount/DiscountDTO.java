/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.discount;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HUYVU
 */
public class DiscountDTO implements Serializable{
    private String discountID;
    private int value;
    private String contentDiscount;
    private Date startDate;
    private Date endDate;

    public DiscountDTO() {
    }

    public DiscountDTO(String discountID, int value, String contentDiscount, Date startDate, Date endDate) {
        this.discountID = discountID;
        this.value = value;
        this.contentDiscount = contentDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the discountID
     */
    public String getDiscountID() {
        return discountID;
    }

    /**
     * @param discountID the discountID to set
     */
    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the contentDiscount
     */
    public String getContentDiscount() {
        return contentDiscount;
    }

    /**
     * @param contentDiscount the contentDiscount to set
     */
    public void setContentDiscount(String contentDiscount) {
        this.contentDiscount = contentDiscount;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    
    
}
