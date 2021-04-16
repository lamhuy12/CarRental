/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.category;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HUYVU
 */
public class CategoryDAO implements Serializable {
    List<CategoryDTO> listAllCategory;

    public List<CategoryDTO> getListAllCategory() {
        return listAllCategory;
    }   

    public void listAllCategory() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
     
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select CateID, Name from Category";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("CateID");
                    String name = rs.getString("Name");
                    CategoryDTO dto = new CategoryDTO(cateID, name);

                    if (listAllCategory == null) {
                        listAllCategory = new ArrayList();
                    }
                    listAllCategory.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
