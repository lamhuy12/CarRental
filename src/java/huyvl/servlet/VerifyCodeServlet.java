/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "VerifyCodeServlet", urlPatterns = {"/VerifyCodeServlet"})
public class VerifyCodeServlet extends HttpServlet {

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

        String tmpCode = request.getParameter("txtAuthcode");
        String url = "verifyPage";
        int code = 0;
        try {

            try {
                code = Integer.parseInt(tmpCode);
            } catch (NumberFormatException e) {
                log("VerifyCodeServlet_NumberFormatException: " + e.getMessage());
            }
            HttpSession session = request.getSession();
            Integer authCode = (Integer) session.getAttribute("AUTHCODE");
            String email = (String) session.getAttribute("EMAILCONFIRM");
            String emailNeedVerify = (String) session.getAttribute("EMAILNEEDVERIFY");
            if (email != null) {
                if (code == authCode) {
                    url = "loginPage";
                    RegistrationDAO registrationDAO = new RegistrationDAO();
                    registrationDAO.UpdateStatus(email);
                } else {
                    session.setAttribute("VERIFYERR", "Incorrect verification code");
                }
            } else {
                if (code == authCode) {
                    url = "loginPage";
                    RegistrationDAO registrationDAO = new RegistrationDAO();
                    registrationDAO.UpdateStatus(emailNeedVerify);
                } else {
                    session.setAttribute("VERIFYERR", "Incorrect verification code");
                }
            }

        } catch (SQLException ex) {
            log("VerifyCodeServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("VerifyCodeServlet_Naming: " + ex.getMessage());
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
