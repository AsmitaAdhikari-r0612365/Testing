<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Product Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Product Overview"></jsp:param>
    </jsp:include>
    <main>

        <%--
           <jsp:include page="header.jsp">
               <jsp:param value="Personen Overview" name="title"/>
           </jsp:include>--%>
        <main>

            <table>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price(€)</th>
                    <th></th>
                </tr>
                <c:forEach var="product" items="${allProducts}">
                    <tr>
                        <td>${product.productid }</td>
                        <td>${product.name }</td>
                        <td>${product.description }</td>
                        <td>${product.price }</td>
                        <td>
                            <a href="Controller?action=goToDeleteProduct&pid=${product.productid}">Delete</a>
                            <a href="Controller?action=goToUpdateProduct&upid=${product.productid}">Update</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </main>
        <footer>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </footer>
</div>
</body>
</html>
