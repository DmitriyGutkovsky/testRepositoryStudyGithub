<%--
  Created by IntelliJ IDEA.
  User: gutko
  Date: 29.09.20
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
Home Page
    <h1>
<%--        Переход в файл main.jsp--%>
        Go to
        <a href="main" >
            main.jsp
        </a>
    </h1>

<form action="FrontController" method="post">
    <input type="submit" name="Form Button" />
<%--    если использовать value - кнопка будет называться "Form Buttom"--%>
<%--    <input type="submit" value="Form Button" />--%>
</form>

</body>
</html>
