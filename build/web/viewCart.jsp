<%-- 
    Document   : viewCart
    Created on : Jan 14, 2021, 11:20:03 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Shopping Cart</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope}" >
            <font color="red">
            Welcome, ${sessionScope.FULLNAME}
            </font> <br/>
        </c:if>
        <form action="logout">
            <input type="submit" value="LogOut" />
        </form> <br/>

        <h1>Your shopping cart</h1>


        <c:set var="cart" value="${sessionScope.CUSTCART}" />
        <c:set var="checkDiscount" value="${requestScope.ERRORDISCOUNT}" />
        <c:set var="checkDateDiscount" value="${requestScope.ERRORDATE}" />
        <c:set var="discountValue" value="${sessionScope.DISCOUNTVALUE}" />
        <c:set var="errorUpdate" value="${sessionScope.ERRORUPDATE}" />
        <c:if test="${not empty errorUpdate}" >
            <font color="red">
            ${errorUpdate}
            </font>
        </c:if>

        <c:if test="${not empty cart}" >
            <c:set var="getCars" value="${cart.cars}" />
            <c:if test="${not empty getCars}" >
                <c:set var="checktime" value="${sessionScope.ERRSEARCHDATE}"/>
                <form action="checkout">
                    <input type="submit" value="Check Out" /> <br/>
                    Rental Date: <font color ="red">${sessionScope.RENTALDATE} </font><br/>
                    <c:if test="${not empty checktime.rentalTimeErr}" >
                        <font color ="red">
                        ${checktime.rentalTimeErr}
                        </font> </br>
                    </c:if>
                    Return Date: <font color ="red">${sessionScope.RETURNDATE}</font> <br/>
                    <c:if test="${not empty checktime.returnTimeErr}" >
                        <font color ="red">
                        ${checktime.returnTimeErr}
                        </font> </br>
                    </c:if>
                </form>

                <form action="discount">
                    Discount Code: <input type="text" name="txtDiscount" value="${param.txtDiscount}" placeholder="enter discount code"/> 
                    <input type="submit" value="Use" /> <br/>
                    <c:if test="${not empty checkDiscount}" >
                        <font color="red" >
                        ${checkDiscount}
                        </font>
                    </c:if>
                    <c:if test="${not empty checkDateDiscount.startDateErr}">
                        <font color="red" >
                        ${checkDateDiscount.startDateErr}
                        </font>
                    </c:if>
                    <c:if test="${not empty checkDateDiscount.endDateErr}">
                        <font color="red" >
                        ${checkDateDiscount.endDateErr}
                        </font>
                    </c:if>
                    <c:if test="${not empty discountValue}" >
                        <font color="red" >
                        Discount: ${discountValue}%
                        </font>
                    </c:if>
                </form>

                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>CarID</th>
                            <th>Name</th>
                            <th>Color</th>
                            <th>Year</th>
                            <th>CateID</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="changeCart">
                        <c:forEach var="cars" items="${getCars}" varStatus="counter" >
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>                                   
                                    ${cars.value.carID}
                                    <input type="hidden" name="txtCarIDInCart" value="${cars.value.carID}" />
                                </td>
                                <td>
                                    ${cars.value.name}
                                </td>
                                <td>
                                    ${cars.value.color}
                                </td>
                                <td>
                                    ${cars.value.year}
                                </td>
                                <td>
                                    ${cars.value.cateID}
                                </td>
                                <td>
                                    <input type="number" name="txtQuantityInCart" value="${cars.value.quantity}" min="1" max="50" />
                                </td>
                                <td>
                                    ${cars.value.price * cars.value.quantity} 
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRemoveCarsFromCart" value="${cars.key}" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tr>
                            <td colspan="6">
                                <a href="search">Continues Shopping</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction"/>
                            </td>
                            <td>
                                ${sessionScope.CUSTCART.total - sessionScope.CUSTCART.total * sessionScope.DISCOUNTVALUE / 100}
                            </td>
                            <td>
                                <input type="submit" value="Remove selected food" name="btAction"/>
                            </td>
                        </tr>
                    </form>
                </table>
            </c:if>

            <c:if test="${empty getCars}" >
                Your cart is empty! <a href="search">Click here to rent a car</a>
            </c:if>
        </c:if>
        <c:if test="${empty cart}" >
            Your cart is empty! <a href="search">Click here to rent a car</a>
        </c:if>
    </body>
</html>
