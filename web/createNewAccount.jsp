<%-- 
    Document   : createNewAccount
    Created on : Mar 3, 2021, 12:03:50 AM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Create new account</h1>
        <form action="createNewAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATEER}"/>
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" /> <br/>
            <c:if test="${not empty errors.emailLengthErr}">
                <font color ="red">
                ${errors.emailLengthErr}
                </font> </br>
            </c:if>  
            <c:if test="${not empty errors.emailIsExisted}">
                <font color ="red">
                ${errors.emailIsExisted}
                </font> </br>
            </c:if>  
            <c:if test="${not empty errors.emailFormErr}">
                <font color ="red">
                ${errors.emailFormErr}
                </font> </br>
            </c:if>
                Password: <input type="password" name="txtPassword" value="${param.txtPassword}" /> <br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color ="red">
                ${errors.passwordLengthErr}
                </font> </br>
            </c:if> 
            Confirm: <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color ="red">
                ${errors.confirmNotMatched}
                </font> </br>
            </c:if> 
                FullName: <input type="text" name="txtFullname" value="${param.txtFullname}" /> <br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color ="red">
                ${errors.fullnameLengthErr}
                </font> </br>
            </c:if> 
                Phone: <input type="text" name="txtPhone" value="${param.txtPhone}" /> <br/>
            <c:if test="${not empty errors.phoneErr}">
                <font color ="red">
                ${errors.phoneErr}
                </font> </br>
            </c:if> 
                Address: <input type="text" name="txtAddress" value="${param.txtAddress}" /> <br/>
            <c:if test="${not empty errors.addressErr}">
                <font color ="red">
                ${errors.addressErr}
                </font> </br>
            </c:if> 
            <input type="submit" value="Sign up" /> 
            <input type="reset" value="Reset" />
        </form>
        <a href="loginPage">Click here to login</a> <br/>
        <a href="search">Back to home page</a>
    </body>
</html>
