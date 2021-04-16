<%-- Document : admin Created on : Mar 1, 2021, 11:19:54 AM Author : HUYVU --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
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
            <a href="viewCartPage">View You Cart</a> ||                 
            <a href="historyOrderPage">History</a>
        </c:if>

        <c:if test="${empty fullname}">
            <a href="loginPage">Login</a> ||
            <a href="createNewAccountPage">Register</a> ||
        </c:if>

        <h1> <u>Car rental:</u></h1>
                <c:set var="checktime" value="${requestScope.ERRSEARCHDATE}"/>
                <c:set var="checkAdd" value="${requestScope.ERRORADD}"/>
                <c:set var="pageNumber" value="${sessionScope.PAGENUMBER}" />

        <form action="search">
            Car name: <input type="text" name="txtCarName" value="${sessionScope.CARNAME}" placeholder="enter name" /> <br />
            Category: <select name="cbCategory">
                <option value="">Choose category</option>
                <c:forEach var="category" items="${requestScope.GETALLCATEGORY}">
                    <c:if test="${sessionScope.CATEID.equals(category.cateID)}">
                        <option value="${category.cateID}" selected>${category.name}</option>
                    </c:if>
                    <c:if test="${!sessionScope.CATEID.equals(category.cateID)}">
                        <option value="${category.cateID}">${category.name}</option>
                    </c:if>
                </c:forEach>
            </select> <br />
            Rental Date: <input type="date" name="txtRentalDate" id="txtRentalDate" value="${sessionScope.RENTALDATE}" required="required" /> <br />
            <c:if test="${not empty checktime.rentalTimeErr}" >
                <font color ="red">
                ${checktime.rentalTimeErr}
                </font> </br>
            </c:if>
            Return Date: <input type="date" name="txtReturnDate" id="txtReturnDate" value="${sessionScope.RETURNDATE}" required="required" /> <br />
            <c:if test="${not empty checktime.returnTimeErr}" >
                <font color ="red">
                ${checktime.returnTimeErr}
                </font> </br>
            </c:if>
            Quantity: <input type="number" name="txtQuantity" value="${sessionScope.QUANTITY}" required="required" min="0"
                             placeholder="enter quantity" /> <br />
            <c:if test="${not empty checkAdd}" >
                <font color ="red">
                ${checkAdd}
                </font> </br>
            </c:if>

            <input type="hidden" name="txtPageNumber" value="${pageNumber}" />
            <input type="submit" value="Search" />
        </form>

        <h2>List car:</h2>
        <c:set var="listSearch" value="${requestScope.SEARCHRESULT}" />
        <c:if test="${not empty listSearch}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>CarID</th>
                        <th>Name</th>
                        <th>Color</th>
                        <th>Year</th>
                        <th>CateID</th>
                        <th>Price</th>
                        <th>Quantity Available</th>
                        <th>Add to cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listSearch}" varStatus="counter">
                    <form action="addCarToCart">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.carID}
                                <input type="hidden" name="txtCarID" value="${dto.carID}" />
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.color}
                            </td>
                            <td>
                                ${dto.year}
                            </td>
                            <td>
                                ${dto.cateID}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                <c:forEach var="quantity" items="${requestScope.QUANTITYAVAILABLE}" >
                                    <c:if test="${quantity.key eq dto.carID}" >
                                        ${quantity.value}/${dto.quantity}
                                    </c:if>
                                </c:forEach>

                            </td>
                            <td>
                                <input type="submit" value="Add to cart" />
                                
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>
        <form action="search">
            <input type="submit" value="Previous" name="btAction" /> 
            <input type="text" name="txtPageNumber" value="${sessionScope.PAGENUMBER}" readonly="readonly"/>
            <input type="hidden" name="txtCarName" value="${sessionScope.CARNAME}" />
            <input type="hidden" name="txtRentalDate" value="${sessionScope.RENTALDATE}" />
            <input type="hidden" name="txtReturnDate" value="${sessionScope.RETURNDATE}" />
            <input type="hidden" name="txtQuantity" value="${sessionScope.QUANTITY}" />
            <input type="hidden" name="cbCategory" value="${sessionScope.CATEID}" />
            <input type="submit" value="Next" name="btAction"/>
        </form>
    </c:if>
    <c:if test="${not empty requestScope.ERRORSEARCH}">${requestScope.ERRORSEARCH}</c:if>
</body>

<script type="text/javascript">
    var currentDate = new Date().toISOString().split("T")[0];
    document.getElementById("txtRentalDate").setAttribute("min", currentDate);
    document.getElementById("txtReturnDate").setAttribute("min", currentDate);
</script>

</html>