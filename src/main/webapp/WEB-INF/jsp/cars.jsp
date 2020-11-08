<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars page</title>
</head>
<body>
<div>Cars</div>
<div>
    <table>
        <tr>
            <td>Car Id</td>
            <td>Model</td>
            <td>CreationYear</td>
            <td>userId</td>
            <td>Price</td>
            <td>Color</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>${car.id}</td>
                <td>${car.model}</td>
                <td>${car.creationYear}</td>
                <td>${car.userId}</td>
                <td>${car.price}</td>
                <td>${car.color}</td>
                <td><button>Edit</button></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
