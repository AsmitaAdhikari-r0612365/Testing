<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Errors</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param value="Error" name="title" />
    </jsp:include>

<main>
    <h1>There is something wrong here!</h1>
    <h2>Error caused = ${pageContext.exception}</h2>

    <h4><a href="Controller">Home</a></h4>
</main>
</div>

</body>
</html>