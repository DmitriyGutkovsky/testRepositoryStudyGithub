<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<%--переход в файл index.jsp--%>
<h2>
    I am <b>main.jsp</b>
</h2>

<h1>
    Go to
    <a href="index.jsp"> index.jsp </a>
</h1>

<h1>
    Go to
    <a href="bye"> bye.jsp </a>
</h1>

<h3> Hello from main! </h3>
<form action="index.jsp" method="" post>
    <input type="submit" value="Go to Index Page"/>
</form>

</body>
</html>
