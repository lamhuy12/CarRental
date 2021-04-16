<%-- 
    Document   : confirmEmail
    Created on : Mar 15, 2021, 7:47:11 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm email again Page</title>
    </head>
    <body>
        <h1>Confirm email again</h1>

        <form action="emailNeedVerify">
            Email: <input type="text" name="txtEmailNeedVerify" value="${param.txtEmailNeedVerify}" /> <br/>
            <input type="submit" value="Send code" />
        </form>
            <c:set var="checkStatus" value="${requestScope.EMAILHASBEENVERIFY}" />
            <c:if test="${not empty checkStatus}" >
                <font color="red"> 
                ${checkStatus}
                </font>
            </c:if> <br/>
                <a href="loginPage">Click here to login</a> <br/>
                <a href="search">Back to homepage</a>
    </body>
</html>
