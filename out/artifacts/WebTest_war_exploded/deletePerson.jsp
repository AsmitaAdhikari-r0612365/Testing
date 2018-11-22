<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Delete Person</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param value="Delete ${param.id}" name="title" />
    </jsp:include>

    <main>
        <h3>Are you sure that you want to delete ${user.userid}?</h3>


        <form action="Controller?action=deletePerson" method="post">
            <p>Please note that once deleted, it cannot be undone.</p>
            <p>
                <input type="hidden" name="id" value="${uid}" />
                <input type="submit" Value="Delete Person" />
            </p>
        </form>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>
