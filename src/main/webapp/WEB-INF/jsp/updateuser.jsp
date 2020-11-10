<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hello, enter the user details for update</title>
</head>
<body>
<h3>User Details For Update</h3>
<form:form method="POST"
           action="/users/update" modelAttribute="userUpdateRequest">
    <table>
        <tr>
            <td><form:label path="id">User Id</form:label></td>
            <td><form:input path="id"/></td>
        </tr>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="surname">Surname</form:label></td>
            <td><form:input path="surname"/></td>
        </tr>
        <tr>
            <td><form:label path="birthDate">
                Birth Date</form:label></td>
            <td><form:input type="date" path="birthDate" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td><form:label path="gender">
                Gender</form:label></td>
            <td><form:input path="gender"/></td>
        </tr>
        <tr>
            <td><form:label path="weight">
                Weight</form:label></td>
            <td><form:input path="weight"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>