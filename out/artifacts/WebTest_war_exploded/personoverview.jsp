<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Person Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="User Overview"></jsp:param>
    </jsp:include>
    <main>

        <%--
           <jsp:include page="header.jsp">
               <jsp:param value="Personen Overview" name="title"/>
           </jsp:include>--%>
        <main>

            <table>
                <tr>
                    <th>User ID</th>
                    <th>E-mail</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th></th>
                </tr>
                <c:forEach var="people" items="${allPeople}">
                    <tr>
                        <td>${people.userid }</td>
                        <td>${people.email }</td>
                        <td>${people.firstName }</td>
                        <td>${people.lastName }</td>
                        <td>
                            <a href="Controller?action=goToDeletePerson&id=${people.userid}">Delete</a>
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