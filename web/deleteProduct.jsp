<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Delete Product</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param value="Delete ${param.pid}" name="title" />
    </jsp:include>

    <main>
        <h3>Are you sure that you want to delete ${product.name}?</h3>

        <h5>Details:</h5>
        <ul>
            <li>${product.name }</li>
            <li>${product.description }</li>
            <li>${product.price }</li>
        </ul>


        <form action="Controller?action=deleteProduct" method="post">
            <p>Please note that once deleted, it cannot be undone.</p>
            <p>
                <input type="hidden" name="id" value="${pid}" />
                <input type="submit" Value="Delete Product" />
            </p>
        </form>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>
