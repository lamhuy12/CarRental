/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.error;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class ErrorTime implements Serializable{
    private String rentalTimeErr;
    private String returnTimeErr;

    public ErrorTime() {
    }

    public ErrorTime(String rentalTimeErr, String returnTimeErr) {
        this.rentalTimeErr = rentalTimeErr;
        this.returnTimeErr = returnTimeErr;
    }

    /**
     * @return the rentalTimeErr
     */
    public String getRentalTimeErr() {
        return rentalTimeErr;
    }

    /**
     * @param rentalTimeErr the rentalTimeErr to set
     */
    public void setRentalTimeErr(String rentalTimeErr) {
        this.rentalTimeErr = rentalTimeErr;
    }

    /**
     * @return the returnTimeErr
     */
    public String getReturnTimeErr() {
        return returnTimeErr;
    }

    /**
     * @param returnTimeErr the returnTimeErr to set
     */
    public void setReturnTimeErr(String returnTimeErr) {
        this.returnTimeErr = returnTimeErr;
    }
    
    
}
