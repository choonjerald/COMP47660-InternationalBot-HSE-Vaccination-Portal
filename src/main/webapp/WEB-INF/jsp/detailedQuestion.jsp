<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
    <%@include file="../css/home.css"%>
    th, td {
        text-align: left;
        max-width: 60%;
    }

    tr {
        margin-bottom: 20px;
    }

    textarea {
        resize: none;
    }

    header {
        margin-bottom: 30px;
    }
</style>
<html>
<body>
<head>
    <title>FAQ</title>
</head>
<!-- Navbar-->
<header class="header">
    <nav class="navbar navbar-expand-lg fixed-top py-3">
        <div class="container"><a href="/" class="navbar-brand text-uppercase font-weight-bold">HSE Vaccination</a>
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                    class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>

            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a href="/faq/create" class="nav-link text-uppercase font-weight-bold">Create
                        New</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>


<div class="container">
    <div class="pt-5 text-white">
        <h3 class="mt-5"> Question Information: </h3>
        <div class="card">
            <div class="card-body">
                <table class="table">
                    <tr>
                        <th>Title:</th>
                        <td><c:out value="${displayQuestion.title} "/></td>
                    </tr>
                    <tr>
                        <th>Details:</th>
                        <td><c:out value="${displayQuestion.details} "/></td>

                    </tr>
                    <tr>
                        <th>Answer:</th>
                        <td><c:out value="${displayQuestion.answers} "/></td>
                    </tr>
                    <tr>
                        <th>Status:</th>
                        <c:set var="status" scope="session" value="${displayQuestion.status}"/>
                        <c:if test="${status == false}">
                            <td><c:out value="Not Answered"/></td>
                        </c:if>
                        <c:if test="${status == true}">
                            <td><c:out value="Answered"/></td>
                        </c:if>
                    </tr>
                </table>
            </div>
        </div>
        <form class="mt-2" method="get" action="/faq">
            <button class="btn btn-secondary" type="submit">Back</button>
        </form>
    </div>
</div>
</body>
</html>
