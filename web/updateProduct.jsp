<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param value="Update Product" name="title" />
    </jsp:include>

    <main>
        <c:if test="${errors.size()>0 }">
            <div class="alert-danger">

                <ul>
                    <c:forEach var="error" items="${errors }">
                        <li>${error }</li>
                    </c:forEach>
                </ul>

            </div>
        </c:if>

        <form method="post" action="Controller?action=updateProduct" novalidate>

            <p>
                <label for="Name">Name</label>
                <input type="text" id="Name" name="name" required value="${product.name}">
            </p>
            <p>
                <label for="description">Description</label>
                <input type="text" id="description" name="description" required value="${product.description}">
            </p>
            <p>
                <label for="price">Price</label>
                <input type="number" id="price" name="price" required value="${product.price}">
            </p>
            <p>
                <input type="hidden" id="proid" name="productId" required value="${proid}">
            </p>
            <p>
                <input type="submit" id="updateProduct" value="Update Product">
            </p>

        </form>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer></div>
</body>
</html>
