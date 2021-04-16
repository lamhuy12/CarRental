/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.category;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class CategoryDTO implements Serializable{
    private String cateID;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String cateID, String name) {
        this.cateID = cateID;
        this.name = name;
    }

    /**
     * @return the cateID
     */
    public String getCateID() {
        return cateID;
    }

    /**
     * @param cateID the cateID to set
     */
    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
