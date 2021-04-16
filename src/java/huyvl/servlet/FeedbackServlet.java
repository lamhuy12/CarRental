/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.feedback.FeedbackDAO;
import huyvl.orderDetail.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HUYVU
 */
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/FeedbackServlet"})
public class FeedbackServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String content = request.getParameter("txtFeedback");
        String ratetmp = request.getParameter("txtRate");
        int rate = 0;
        int count = 0;
        try {
            rate = Integer.parseInt(ratetmp);
        } catch (NumberFormatException ex) {
            log("FeedbackServlet_Numberformat: " + ex.getMessage());
        }

        String url = "feedbackPage";
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String email = (String) session.getAttribute("EMAIL");
                String orderDetailID = (String) session.getAttribute("ORDERDETAILID");
                FeedbackDAO dao = new FeedbackDAO();
                String lastFeedbackID = dao.getLastFeedbackID();
                String feedbackID = "";

                if (lastFeedbackID == null) {
                    feedbackID = "FB01";
                } else {
                    String tmp = lastFeedbackID.substring(2);
                    int parseID = Integer.parseInt(tmp) + 1;
                    feedbackID = "FB" + parseID;
                }
                
                boolean resultFeedback = dao.createFeedback(feedbackID, content, rate, new Date(), email);
                if (resultFeedback) {
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    orderDetailDAO.updateOrderDetail(feedbackID, orderDetailID);
                    request.setAttribute("FBSTATUS", "Send feedback success");                    
                } else {
                    request.setAttribute("FBSTATUSERR", "send feedback error");
                }
            }

        } catch (SQLException ex) {
            log("FeedbackServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("FeedbackServlet_Naming: " + ex.getMessage());
        } catch (Exception ex) {
            log("FeedbackServlet_Exception: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
