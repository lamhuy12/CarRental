/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.car.CarDetailDAO;
import huyvl.car.CarDetailDTO;
import huyvl.cart.CartObject;
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
@WebServlet(name = "AddCarToCartServlet", urlPatterns = {"/AddCarToCartServlet"})
public class AddCarToCartServlet extends HttpServlet {

    private final String SEARCHFOOD_PAGE = "searchPage";

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

        
        String url = "errorAllPage";

        try {
            HttpSession session = request.getSession();
            if (session != null) {
                String carName = (String) session.getAttribute("CARNAME");
                String rentalDate = (String) session.getAttribute("RENTALDATE");
                String returnDate = (String) session.getAttribute("RETURNDATE");
                String quantity = (String) session.getAttribute("QUANTITY");
                String cateID = (String) session.getAttribute("CATEID");
                if ((rentalDate.isEmpty()) || (returnDate.isEmpty())) {
                    request.setAttribute("ERRORADD", "Please input all valid information");
                    url = "search";
                } else {
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    if (cart == null) {
                        cart = new CartObject();
                    }

                    String carID = request.getParameter("txtCarID");
                    //request.setAttribute("CARID", carID);
                    CarDetailDAO cardetailDAO = new CarDetailDAO();
                    CarDetailDTO cardetailDTO = cardetailDAO.findPrimaryKey(carID);
                    System.out.println("dto: " + cardetailDTO.getName());
                    cardetailDTO.setQuantity(1);

                    cart.addCarToCart(cardetailDTO);
                    session.setAttribute("CUSTCART", cart);
                    url = "search";
                    String checkLogin = (String) session.getAttribute("FULLNAME");
                    if (checkLogin == null) {
                        url = "loginPage";
                    }
                }
            }

        } catch (SQLException ex) {
            log("AddCarToCartServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddCarToCartServlet_Naming: " + ex.getMessage());
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
