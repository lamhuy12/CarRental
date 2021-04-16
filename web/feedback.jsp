<%-- 
    Document   : feedback
    Created on : Mar 6, 2021, 10:45:19 AM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
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

        <c:if test="${empty fullname}">
            <a href="loginPage">Login</a> ||
            <a href="createNewAccountPage">Register</a>
        </c:if>

        <h1>Thanks for your feedback</h1>

        <form action="feedback">
            Content: <input type="text" name="txtFeedback" value="${param.txtFeedback}" minlength="0" maxlength="500" required="required"/> <br/>
            Rate (0-10): <input type="range" name="txtRate" value="${param.txtRate}" min="0" max="10" required="required"/> <br/>
            <input type="submit" value="Send" />
        </form>
        <c:set var="checkSuccess" value="${requestScope.FBSTATUS}" />
        <c:set var="checkFail" value="${requestScope.FBSTATUSERR}" />
        <c:if test="${not empty checkSuccess}" >
            <font color="red" >
            ${checkSuccess}
            </font>
        </c:if> <br/>
        <c:if test="${not empty checkFail}" >
            <font color="red" >
            ${checkFail}
            </font>
        </c:if> <br/>

        <a href="historyOrderPage">Back to history</a> <Br/>
        <a href="search">Back to homepage</a>

    </body>
</html>
