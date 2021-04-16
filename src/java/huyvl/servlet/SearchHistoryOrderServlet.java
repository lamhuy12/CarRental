/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.history.HistoryDAO;
import huyvl.history.SearchHistoryByCarNameDTO;
import huyvl.order.OrderDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "SearchHistoryOrderServlet", urlPatterns = {"/SearchHistoryOrderServlet"})
public class SearchHistoryOrderServlet extends HttpServlet {

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

        String carName = request.getParameter("txtCarNameHistory");
        String bookingDateTmp = request.getParameter("txtBookingDate");

        String url = "historyOrderPage";

        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            Date bookingDate = null;
            if (!bookingDateTmp.isEmpty()) {
                bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDateTmp);
            }
            List<OrderDTO> resultByDate = null;
            List<SearchHistoryByCarNameDTO> result = null;

            if (!carName.isEmpty() && (bookingDateTmp.isEmpty())) {
                HistoryDAO historyDAO = new HistoryDAO();
                historyDAO.SearchHistoryByName(carName, email);
                result = historyDAO.getListSearchHistoryByCarname();

                if (result != null) {
                    session.setAttribute("HISTORYSEARCHBYNAME", result);
                    session.removeAttribute("HISTORYSEARCH");
                    url = "historyOrderPage";
                } else {
                    request.setAttribute("ERRORSEARCH", "No record to match");
                    session.removeAttribute("HISTORYSEARCH");
                    session.removeAttribute("HISTORYSEARCHBYNAME");
                }

            } else if ((carName.isEmpty()) && (!bookingDateTmp.isEmpty())) {
                HistoryDAO historyDAO = new HistoryDAO();
                historyDAO.SearchHistoryByDate(bookingDate, email);
                resultByDate = historyDAO.getListSearchHistoryByDate();
                if (resultByDate != null) {
                    session.setAttribute("HISTORYSEARCH", resultByDate);
                    session.removeAttribute("HISTORYSEARCHBYNAME");
                    url = "historyOrderPage";
                } else {
                    request.setAttribute("ERRORSEARCH", "No record to match");
                    session.removeAttribute("HISTORYSEARCHBYNAME");
                    session.removeAttribute("HISTORYSEARCH");
                }

            } else if ((!carName.isEmpty()) && (!bookingDateTmp.isEmpty())){
                HistoryDAO historyDAO = new HistoryDAO();
                historyDAO.SearchHistory(carName, bookingDate, email);
                result = historyDAO.getListSearchHistoryAll();

                if (result != null) {
                    session.setAttribute("HISTORYSEARCHBYNAME", result);
                    session.removeAttribute("HISTORYSEARCH");
                    url = "historyOrderPage";
                } else {
                    request.setAttribute("ERRORSEARCH", "No record to match");
                    session.removeAttribute("HISTORYSEARCH");
                    session.removeAttribute("HISTORYSEARCHBYNAME");
                }

            } else {
                request.setAttribute("ERRORSEARCH", "No record to match");
            }

            request.setAttribute("RENTALDATEHISTORY", bookingDateTmp);
        } catch (ParseException ex) {
            log("HistoryOrderServlet_ParseException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("HistoryOrderServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("HistoryOrderServlet_Naming: " + ex.getMessage());
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
