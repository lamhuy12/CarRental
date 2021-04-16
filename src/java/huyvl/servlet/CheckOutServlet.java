/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.car.CarDetailDAO;
import huyvl.car.CarDetailDTO;
import huyvl.cart.CartObject;
import huyvl.order.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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

        String url = "search";
        boolean error = false;

        try {
            HttpSession session = request.getSession();
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {
                    Map<String, CarDetailDTO> cars = cart.getCars();
                    if (cars != null) {
                        OrderDAO orderDAO = new OrderDAO();
                        boolean checkCreateOrder = false;
                        String orderID = "";
                        String email = (String) session.getAttribute("EMAIL");
                        String lastID = orderDAO.getLastOrderIDByUser(email);
                        String discountID = (String) session.getAttribute("DISCOUNTID");
                        String rentalDateTmp = (String) session.getAttribute("RENTALDATE");
                        String returnDateTmp = (String) session.getAttribute("RETURNDATE");
                        Date rentalDate = new SimpleDateFormat("yyyy-MM-dd").parse(rentalDateTmp);
                        Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDateTmp);
                        int count = 0;

                        //orderID
                        if (lastID == null) {
                            orderID = email + "-1";
                        } else {
                            String[] tmp = lastID.split("-");
                            orderID = email + "-" + (Integer.parseInt(tmp[1]) + 1);
                        }

                        //check quantity
                        Map<String, CarDetailDTO> check = cart.getCars();

                        for (Map.Entry<String, CarDetailDTO> entry : check.entrySet()) {
                            String key = entry.getKey();
                            CarDetailDTO dto = entry.getValue();
                            int quantityInCart = dto.getQuantity();
                            CarDetailDAO dao = new CarDetailDAO();
                            int totalQuantity = dao.findByCarID(key);
                            int quantityIsRent = dao.QuantityCarIsRent(rentalDate, returnDate, key);
                            if (quantityInCart > (totalQuantity - quantityIsRent)) {
                                session.setAttribute("ERRORUPDATE", dto.getName() + " is out of stock" + " - " + "Total car: " + totalQuantity + " - " + "TotalCar is renting on this day: " + quantityIsRent);
                                error = true;
                                url = "viewCartPage";
                            }
                        }

                        //create order
                        if (error == false) {
                            int discount = (int) session.getAttribute("DISCOUNTVALUE");
                            if (discount <= 0) {
                                discount = 1;
                            }
                            float totalMoney = cart.getTotal() - cart.getTotal()*discount/100;
                            checkCreateOrder = orderDAO.createOrder(orderID, email, totalMoney,
                                    rentalDate, returnDate, true, new Date(), discountID);
                            if (checkCreateOrder) {
                                int countOrderID = 0;
                                for (CarDetailDTO dto : cart.getCars().values()) {
                                    String orderDetailID = orderID + "-" + countOrderID++;
                                    String feedbackID = "";
                                    orderDAO.createOrderDetail(orderDetailID, orderID, dto.getCarID(), dto.getQuantity(), dto.getPrice() * dto.getQuantity(), feedbackID);
                                    session.removeAttribute("CUSTCART");
                                    session.removeAttribute("RENTALDATE");
                                    session.removeAttribute("RETURNDATE");
                                    session.removeAttribute("ERRORADD");
                                    session.removeAttribute("ERRORUPDATE");
                                    session.removeAttribute("QUANTITY");
                                    session.removeAttribute("CARNAME");
                                    session.removeAttribute("SEARCHRESULT");
                                    session.removeAttribute("DISCOUNTVALUE");
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            log("CheckOutServlet_Exception:" + ex.getMessage());
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
