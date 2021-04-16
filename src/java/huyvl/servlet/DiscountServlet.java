/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.discount.DiscountDAO;
import huyvl.discount.ErrDiscount;
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
@WebServlet(name = "DiscountServlet", urlPatterns = {"/DiscountServlet"})
public class DiscountServlet extends HttpServlet {

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

        String code = request.getParameter("txtDiscount");
        String url = "viewCartPage";

        ErrDiscount err = new ErrDiscount();

        try {
            HttpSession session = request.getSession();
            DiscountDAO discountDAO = new DiscountDAO();
            int valueDiscount = discountDAO.discountValue(code);

            Date startDate = discountDAO.discountStartDate(code);
            Date endDate = discountDAO.discountEndDate(code);

            if (valueDiscount == 0) { //if code is not exist
                session.setAttribute("ERRORDISCOUNT", "Cannot find this code");
                session.removeAttribute("DISCOUNTVALUE");
                session.removeAttribute("ERRORDATE");
            } else { //code is exist
                long checkStartDate = (long) startDate.getTime();
                long checkEndDate = (long) endDate.getTime();
                long realtime = (long) new Date().getTime();
                boolean flag = false;
                //check date
                if ((realtime < checkStartDate)) {
                    flag = true;
                    err.setStartDateErr("Not yet the start date");
                }
                if ((realtime > checkEndDate)) {
                    flag = true;
                    err.setEndDateErr("Code has expired");
                }

                if (flag) {
                    request.setAttribute("ERRORDATE", err);
                    session.removeAttribute("ERRORDISCOUNT");
                    session.removeAttribute("DISCOUNTVALUE");
                } else {
                    //if code is exist 
                    session.setAttribute("DISCOUNTVALUE", valueDiscount);
                    session.removeAttribute("ERRORDISCOUNT");
                    session.removeAttribute("ERRORDATE");
                }
            }
            session.setAttribute("DISCOUNTID", code);
        } catch (SQLException ex) {
            log("DiscountServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DiscountServlet_Naming: " + ex.getMessage());
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
