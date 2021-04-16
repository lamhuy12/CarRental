<%-- 
    Document   : historyOrder
    Created on : Mar 5, 2021, 10:36:23 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.FULLNAME}" />
        <c:if test="${not empty fullname}">
            <font color="red">
            Welcome, ${fullname}
            </font>
            <form action="logout">
                <input type="submit" value="LogOut" />
            </form> <br />
        </c:if>

        <a href="search">Back to homePage</a>
        <h1>History order</h1>
        <form action="historyOrder">
            Name: <input type="text" name="txtCarNameHistory" value="${param.txtCarNameHistory}" placeholder="enter name"/> <br/>
            RentalDate: <input type="date" name="txtBookingDate" value="${requestScope.RENTALDATEHISTORY}" /> <br/>
            <input type="submit" value="search" />
        </form>

        <c:set var="bookingDateValue" value="${param.txtBookingDate}" />
        <c:if test="${not empty bookingDateValue}" >
            <c:set var="resultHistory" value="${sessionScope.HISTORYSEARCH}" />
            <c:if test="${not empty resultHistory}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>OrderID</th>
                            <th>Email</th>
                            <th>Total</th>
                            <th>RentalDate</th>
                            <th>ReturnDate</th>
                            <th>Status</th>
                            <th>DateCreate</th>
                            <th>DiscountID</th>
                            <th>Cancel Order</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${resultHistory}" varStatus="counter" >
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.orderID}
                                    <input type="hidden" name="txtOrderID" value="${dto.orderID}" />
                                </td>
                                <td>
                                    ${dto.email}
                                </td>
                                <td>
                                    ${dto.total}
                                </td>
                                <td>
                                    ${dto.rentalDate}
                                </td>
                                <td>
                                    ${dto.returnDate}
                                </td>
                                <td>
                                    ${dto.status}
                                </td>
                                <td>
                                    ${dto.createDate}
                                </td>
                                <td>
                                    ${dto.discountID}
                                    <c:if test="${dto.discountID == null}" >
                                        None
                                    </c:if>
                                </td>
                                <td>
                                    <form action="deleteOrder">
                                        <input type="submit" value="Cancel Order" />
                                        <input type="hidden" name="txtOrderID" value="${dto.orderID}" />
                                        <input type="hidden" name="lastSearchCarName" value="${param.txtCarNameHistory}"/>
                                        <input type="hidden" name="lastSearchBookingDate" value="${param.txtBookingDate}" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>

        <c:set var="nameValue" value="${param.txtCarNameHistory}" />
        <c:if test="${not empty nameValue}" >
            <c:set var="resultSearchByName" value="${sessionScope.HISTORYSEARCHBYNAME}" />
            <c:if test="${not empty resultSearchByName}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>OrderID</th>
                            <th>OrderDetailID</th>
                            <th>CarID</th>
                            <th>CarName</th>
                            <th>RentalDate</th>
                            <th>ReturnDate</th>
                            <th>Price</th>
                            <th>Amount</th>
                            <th>DateCreate</th>
                            <th>Status</th>
                            <th>Cancel order bill</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${resultSearchByName}" varStatus="counter" >
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.orderID}
                                </td>
                                <td>
                                    ${dto.orderDetailID}
                                </td>
                                <td>
                                    ${dto.carID}
                                </td>
                                <td>
                                    ${dto.carName}
                                </td>
                                <td>
                                    ${dto.rentalDate}
                                </td>
                                <td>
                                    ${dto.returnDate}
                                </td>
                                <td>
                                    ${dto.price}
                                </td>
                                <td>
                                    ${dto.amount}
                                </td>
                                <td>
                                    ${dto.dateCreate}
                                </td>
                                <td>
                                    ${dto.status}
                                </td>
                                <td>
                                    <form action="deleteOrder">
                                        <input type="submit" value="Cancel order" />
                                        <input type="hidden" name="txtOrderID" value="${dto.orderID}" />
                                        <input type="hidden" name="lastSearchCarName" value="${param.txtCarNameHistory}"/>
                                        <input type="hidden" name="lastSearchBookingDate" value="${param.txtBookingDate}" />
                                    </form>
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="feedbackGetOrderDetailID" >
                                        <c:param name="carIDfeedback" value="${dto.carID}" />
                                        <c:param name="orderDetailIDFB" value="${dto.orderDetailID}" />
                                    </c:url>
                                    <a href="${urlRewriting}">feedback</a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.ERRORSEARCH}" >${requestScope.ERRORSEARCH}</c:if>
    </body>
</html>
