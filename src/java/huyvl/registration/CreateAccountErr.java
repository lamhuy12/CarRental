/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.registration;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class CreateAccountErr implements Serializable {

    private String emailLengthErr;
    private String emailFormErr;
    private String passwordLengthErr;
    private String fullnameLengthErr;
    private String phoneErr;
    private String addressErr;
    private String confirmNotMatched;
    private String emailIsExisted;

    public CreateAccountErr() {
    }

    public CreateAccountErr(String emailLengthErr, String emailFormErr, String passwordLengthErr, String fullnameLengthErr, String phoneErr, String addressErr, String confirmNotMatched, String emailIsExisted) {
        this.emailLengthErr = emailLengthErr;
        this.emailFormErr = emailFormErr;
        this.passwordLengthErr = passwordLengthErr;
        this.fullnameLengthErr = fullnameLengthErr;
        this.phoneErr = phoneErr;
        this.addressErr = addressErr;
        this.confirmNotMatched = confirmNotMatched;
        this.emailIsExisted = emailIsExisted;
    }

    /**
     * @return the emailLengthErr
     */
    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    /**
     * @param emailLengthErr the emailLengthErr to set
     */
    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    /**
     * @return the emailFormErr
     */
    public String getEmailFormErr() {
        return emailFormErr;
    }

    /**
     * @param emailFormErr the emailFormErr to set
     */
    public void setEmailFormErr(String emailFormErr) {
        this.emailFormErr = emailFormErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the fullnameLengthErr
     */
    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    /**
     * @param fullnameLengthErr the fullnameLengthErr to set
     */
    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }

    /**
     * @return the phoneErr
     */
    public String getPhoneErr() {
        return phoneErr;
    }

    /**
     * @param phoneErr the phoneErr to set
     */
    public void setPhoneErr(String phoneErr) {
        this.phoneErr = phoneErr;
    }

    /**
     * @return the addressErr
     */
    public String getAddressErr() {
        return addressErr;
    }

    /**
     * @param addressErr the addressErr to set
     */
    public void setAddressErr(String addressErr) {
        this.addressErr = addressErr;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    /**
     * @return the emailIsExisted
     */
    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    /**
     * @param emailIsExisted the emailIsExisted to set
     */
    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

}
