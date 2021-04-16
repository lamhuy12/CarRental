/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.servlet;

import huyvl.car.CarDetailDAO;
import huyvl.car.CarDetailDTO;
import huyvl.category.CategoryDAO;
import huyvl.category.CategoryDTO;
import huyvl.error.ErrorTime;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final int ROWS_OF_PAGE = 20;

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

        String carName = request.getParameter("txtCarName");
        String cateID = request.getParameter("cbCategory");
        String rentalDateTmp = request.getParameter("txtRentalDate");
        String returnDateTmp = request.getParameter("txtReturnDate");
        String quantityTmp = request.getParameter("txtQuantity");

        HttpSession session = request.getSession(false);
        String rentalDateSession = null;
        String returnDateSession = null;
        if (session != null) {
            rentalDateSession = (String) session.getAttribute("RENTALDATE");
            returnDateSession = (String) session.getAttribute("RETURNDATE");
        }

        if (carName == null) {
            carName = "";
        }
        if (cateID == null) {
            cateID = "";
        }
        if (rentalDateTmp == null) {
            if (rentalDateSession != null) {
                rentalDateTmp = rentalDateSession;
            } else {
                rentalDateTmp = "";
            }
        }
        if (returnDateTmp == null) {
            if (returnDateSession != null) {
                returnDateTmp = returnDateSession;
            } else {
                returnDateTmp = "";
            }
        }
        if (quantityTmp == null) {
            quantityTmp = "";
        }

        int quantity = 1;
        if (!quantityTmp.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityTmp);
            } catch (NumberFormatException e) {
                log("SearchServlet_NumberFormatExceptionQUANTITY: " + e.getMessage());
            }
        }

        String pageNumTmp = request.getParameter("txtPageNumber");

        String button = request.getParameter("btAction");
        String url = "searchPage";

        int pageNumber = 0;
        if (pageNumTmp != null) {
            if(pageNumTmp.isEmpty()){
                pageNumTmp = "1";
            }
            try {
                pageNumber = Integer.parseInt(pageNumTmp);
            } catch (NumberFormatException e) {
                log("SearchServlet_NumberFormatExceptionPAGENUMBER: " + e.getMessage());
            }
        }

        if (pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }

        if (button != null) {
            if (button.equals("Next")) {
                pageNumber = pageNumber + 1;
            }
            if (button.equals("Previous")) {
                pageNumber = pageNumber - 1;
                if (pageNumber < 0) {
                    pageNumber = pageNumber + 1;
                }
            }
        }

        ErrorTime err = new ErrorTime();
        int quantityNotRental = 0;

        try {
            //get category
            CategoryDAO cateDAO = new CategoryDAO();
            cateDAO.listAllCategory();
            List<CategoryDTO> resultCate = cateDAO.getListAllCategory();
            request.setAttribute("GETALLCATEGORY", resultCate);

            Map<String, Integer> quantityAvailable = new HashMap<String, Integer>();
            //check date
            Date currentDate = new Date();
            Date rentalDate = null;
            if (!rentalDateTmp.isEmpty()) {
                rentalDate = new SimpleDateFormat("yyyy-MM-dd").parse(rentalDateTmp);
            } else {
                rentalDate = currentDate;
            }

            Date returnDate = null;
            if (!returnDateTmp.isEmpty()) {
                returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDateTmp);
            } else {
                returnDate = currentDate;
            }

            long checkRentalTime = (long) rentalDate.getTime();
            long checkReturnTime = (long) returnDate.getTime();

            boolean foundError = false;
            if ((checkRentalTime > checkReturnTime)) {
                foundError = true;
                err.setRentalTimeErr("Rental date must be less than or equal to the Return date");
            }

            if (foundError) {
                request.setAttribute("ERRSEARCHDATE", err);
            } else {
                List<CarDetailDTO> result = null;
                CarDetailDAO dao = new CarDetailDAO();
                if ((carName.isEmpty()) && (cateID.isEmpty())) {
                    result = dao.listAllCar(ROWS_OF_PAGE, pageNumber);
                    if (button != null) {
                        if (result == null) {
                            if (button.equals("Next")) {
                                pageNumber = pageNumber - 1;
                                result = dao.listAllCar(ROWS_OF_PAGE, pageNumber);
                            }
                        }
                    }

                } //search by name
                else if ((cateID.isEmpty()) && (!carName.isEmpty())) {
                    result = dao.searchCarWithName(carName, rentalDate, returnDate, quantity, ROWS_OF_PAGE, pageNumber);
                    if (button != null) {
                        if (result == null) {
                            if (button.equals("Next")) {
                                pageNumber = pageNumber - 1;
                                result = dao.searchCarWithName(carName, rentalDate, returnDate, quantity, ROWS_OF_PAGE, pageNumber);
                            }
                        }
                    }

                } // search by cate
                else if (!cateID.isEmpty() && (carName.isEmpty())) {
                    result = dao.searchCarWithCate(cateID, rentalDate, returnDate, quantity, ROWS_OF_PAGE, pageNumber);
                    if (button != null) {
                        if (result == null) {
                            if (button.equals("Next")) {
                                pageNumber = pageNumber - 1;
                                result = dao.searchCarWithCate(cateID, rentalDate, returnDate, quantity, ROWS_OF_PAGE, pageNumber);
                            }
                        }
                    }
                } //search by name and cate 
                else if ((!carName.isEmpty()) && (!cateID.isEmpty())) {
                    result = dao.searchCarWithNameAndCate(carName, cateID, rentalDate, returnDate, quantity, ROWS_OF_PAGE, pageNumber);
                    if (button != null) {
                        if (result == null) {
                            if (button.equals("Next")) {
                                pageNumber = pageNumber - 1;
                                result = dao.searchCarWithNameAndCate(carName, cateID, rentalDate, returnDate, quantity, ROWS_OF_PAGE, pageNumber);
                            }
                        }
                    }

                }

                if (result != null) {
                    request.setAttribute("SEARCHRESULT", result);
                    for (CarDetailDTO carDetailDTO : result) {
                        quantityNotRental = carDetailDTO.getQuantity() - dao.QuantityCarIsRent(rentalDate, returnDate, carDetailDTO.getCarID());
                        quantityAvailable.put(carDetailDTO.getCarID(), quantityNotRental);
                    }
                    request.setAttribute("QUANTITYAVAILABLE", quantityAvailable);
//                    session.removeAttribute("ERRSEARCHDATE");
//                    session.removeAttribute("ERRORADD");
//                    session.removeAttribute("ERRORSEARCH");
                } else {
                    request.setAttribute("ERRORSEARCH", "No record to match");
                }
            }
            pageNumber = pageNumber + 1;
            session.setAttribute("PAGENUMBER", pageNumber);
            session.setAttribute("RENTALDATE", rentalDateTmp);
            session.setAttribute("RETURNDATE", returnDateTmp);
            session.setAttribute("QUANTITY", quantityTmp);
            session.setAttribute("CARNAME", carName);
            session.setAttribute("CATEID", cateID);
        } catch (SQLException ex) {
            log("SearchServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet_Naming: " + ex.getMessage());
        } catch (ParseException ex) {
            log("SearchServlet_ParseException: " + ex.getMessage());
        } catch (Exception ex) {
            log("SearchServlet_Exception: " + ex.getMessage());
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
