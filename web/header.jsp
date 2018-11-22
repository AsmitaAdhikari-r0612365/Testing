<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <h1><span>Web shop</span></h1>
    <nav>
        <ul>
            <li><a href="Controller">Home</a></li>
            <li><a href="Controller?action=personOverview">Person Overview</a></li>
            <li><a href="Controller?action=signUp">Sign up</a></li>
            <li><a href="Controller?action=productOverview">Product Overview</a></li>
            <li><a href="Controller?action=createProduct">Add Product</a></li>
        </ul>
    </nav>
    <h2> ${param.title}</h2>

</header>
