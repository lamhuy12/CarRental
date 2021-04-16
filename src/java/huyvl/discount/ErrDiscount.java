/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.discount;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class ErrDiscount implements Serializable{
    private String startDateErr;
    private String endDateErr;

    public ErrDiscount() {
    }

    public ErrDiscount(String startDateErr, String endDateErr) {
        this.startDateErr = startDateErr;
        this.endDateErr = endDateErr;
    }

    /**
     * @return the startDateErr
     */
    public String getStartDateErr() {
        return startDateErr;
    }

    /**
     * @param startDateErr the startDateErr to set
     */
    public void setStartDateErr(String startDateErr) {
        this.startDateErr = startDateErr;
    }

    /**
     * @return the endDateErr
     */
    public String getEndDateErr() {
        return endDateErr;
    }

    /**
     * @param endDateErr the endDateErr to set
     */
    public void setEndDateErr(String endDateErr) {
        this.endDateErr = endDateErr;
    }
    
    
}
