/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.feedback;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HUYVU
 */
public class FeedbackDTO implements Serializable{
    private String feedbackID;
    private String content;
    private int rate;
    private Date createDate;
    private String email;
    private String carID;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String feedbackID, String content, int rate, Date createDate, String email, String carID) {
        this.feedbackID = feedbackID;
        this.content = content;
        this.rate = rate;
        this.createDate = createDate;
        this.email = email;
        this.carID = carID;
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

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

    
}
