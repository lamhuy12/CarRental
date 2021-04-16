<%-- 
    Document   : verify
    Created on : Mar 3, 2021, 1:12:31 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <h1>Verify your account</h1>
        <form action="verifyCode" method="post">
            <input type="text" name="txtAuthcode" >
            <input type="submit" value="verify">
        </form>
            
        <c:set var="verifyCodeErr" value="${sessionScope.VERIFYERR}" />
        <c:if test="${not empty verifyCodeErr}" >
            <font color="red" >
            ${verifyCodeErr}
            </font>
        </c:if>
            
            <a href="loginPage">Login</a>
    </body>
</html>
