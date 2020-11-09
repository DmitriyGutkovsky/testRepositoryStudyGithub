<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hello, enter the car details for update</title>
</head>
<body>
<h3>Car Details for update</h3>
<form:form method="POST"
           action="/cars" modelAttribute="carUpdateRequest">
    <table>
        <tr>
            <td><form:label path="id">Car Id</form:label></td>
            <td><form:input path="id"/></td>
        </tr>
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
