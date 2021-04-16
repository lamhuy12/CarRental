/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.feedback;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class FeedbackDAO implements Serializable {

    public boolean createFeedback(String feedbackID, String content, int rate,
            Date createDate, String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Feedback(FeedbackID, ContentFeedback, Rate, CreateDate, Email) "
                        + "Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, feedbackID);
                stm.setString(2, content);
                stm.setInt(3, rate);
                stm.setTimestamp(4, new Timestamp(createDate.getTime()));
                stm.setString(5, email);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public String getLastFeedbackID() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String feedbackID = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select FeedbackID from Feedback \n"
                        + "Where CreateDate = (Select MAX(CreateDate) From Feedback)";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    feedbackID = rs.getString("FeedbackID");
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
        return feedbackID;
    }
}
