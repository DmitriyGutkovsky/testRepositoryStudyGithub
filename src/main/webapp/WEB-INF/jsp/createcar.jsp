<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hello, enter the car details</title>
</head>
<body>
<h3>Car Details</h3>
<form:form method="POST"
           action="/cars" modelAttribute="carCreateRequest">
    <table>
        <tr>
            <td><form:label path="model">Model</form:label></td>
            <td><form:input path="model"/></td>
        </tr>
        <tr>
            <td><form:label path="creationYear">Creation Year</form:label></td>
            <td><form:input path="creationYear"/></td>
        </tr>
        <tr>
            <td><form:label path="userId">
                User Id</form:label></td>
            <td><form:input path="userId"/></td>
        </tr>

        <tr>
            <td><form:label path="price">
                Price</form:label></td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td><form:label path="color">
                Color</form:label></td>
            <td><form:input path="color"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
