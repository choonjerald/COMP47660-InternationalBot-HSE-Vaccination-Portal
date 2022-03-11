<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
    <%@include file="../css/home.css"%>
    table, th, td {
        border: 1px solid;
        padding: 10px;
    }
    th, td {
        text-align: left;
        max-width: 300px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    #answer {
        width: 300px;
    }
    #title {
        max-width: 200px;
    }
</style>
<html>
<head>
    <title>FAQ</title>
</head>
<!-- Navbar-->
<header class="header">
    <nav class="navbar navbar-expand-lg fixed-top py-3">
        <div class="container"><a href="/" class="navbar-brand text-uppercase font-weight-bold">HSE Vaccination</a>
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>

            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active"><a href="/user/home" class="nav-link text-uppercase font-weight-bold">Home<span class="sr-only">(current)</span></a></li>
                    <li class="nav-item"><a href="/faq/create" class="nav-link text-uppercase font-weight-bold">Create New</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- Body -->
<div class="container">
    <div class="pt-5 text-white">
        <header class="py-5 mt-5">
            <h1 class = "display-4">See some already asked questions!</h1>
        </header>
<%--        <h3>Your Booked Appointments</h3>--%>
        <div class="card">
            <div class="card-body">
                <table class="center">
                    <tr>
                        <th>Title </th>
                        <th>Details </th>
                        <th>Answer </th>
                        <th>Status</th>
                    </tr>
                    <c:forEach var="question" items="${questionList}">
                        <tr>
                            <td id="title"><c:out value="${question.title} " /></td>
                            <td><c:out value="${question.details} " /></td>
                            <td id="answer"><c:out value="${question.answers} " /></td>
                            <td><c:out value="${question.status} " /></td>
                            <td>
                                <a href="/faq/view/${question.id}">view</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</html>