/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.email.SendEmail;
import huyvl.registration.CreateAccountErr;
import huyvl.registration.RegistrationDAO;
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
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

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

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        boolean status = false;
        String url = "createNewAccountPage";

        CreateAccountErr errors = new CreateAccountErr();

        try {
            boolean foundErr = false;
            if (email.trim().length() < 1 || email.trim().length() > 50) {
                foundErr = true;
                errors.setEmailLengthErr("Email requires typing from 1 to 50 chars");
            }
            if (!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?")) {
                errors.setEmailFormErr("Email must be in form abc123@def.xyz(.hkt)");
                foundErr = true;
            }
            if (password.trim().length() < 1 || password.trim().length() > 50) {
                foundErr = true;
                errors.setPasswordLengthErr("Password requires typing from 1 to 50 chars");
            } else if (!password.trim().equals(confirm.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (fullname.trim().length() < 1 || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullnameLengthErr("Fullname requires typing from 1 to 50 chars");
            }
            if (phone.trim().length() < 1 || phone.trim().length() > 20) {
                foundErr = true;
                errors.setPhoneErr("Phone requires typing from 1 to 20 chars");
            }
            if (address.trim().length() < 1 || address.trim().length() > 500) {
                foundErr = true;
                errors.setAddressErr("Phone requires typing from 1 to 500 chars");
            }

            if (foundErr) {
                request.setAttribute("CREATEER", errors);
            } else {
                RegistrationDAO registrationDAO = new RegistrationDAO();
                boolean createAccount = registrationDAO.createAccount(email, password, fullname, phone, address, "user", status, new Date());

                if (createAccount) {
                    SendEmail.send(email, request);
                    url = "verifyPage";
                }
            }
            HttpSession session = request.getSession();
            session.setAttribute("EMAILCONFIRM", email);
            System.out.println("email: " + email);
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateNewAccountServlet_SQL: " + errMsg);
            if (errMsg.contains("duplicate")) {
                errors.setEmailIsExisted(email + " is Existed!!!");
                request.setAttribute("CREATEER", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet_Naming: " + ex.getMessage());
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
