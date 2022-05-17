<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
    <%@include file="../css/home.css"%>
    th, td {
        text-align: left;
        max-width: 300px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
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
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                    class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>

            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a href="/statistic" class="nav-link text-uppercase font-weight-bold">Statistics</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="container">
    <div class="pt-5 text-white">
        <header class="py-5 mt-5">
            <h1 class="display-4">See some already asked questions!</h1>
        </header>
        <div class="card">
            <div class="card-body">
                <a href="/faq/create" class="btn btn-dark btn-lg action-button float-right mb-4">Create New<i
                        class="fa fa-long-arrow-right ml-2"></i></a>
                <table class="table">
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Details</th>
                        <th scope="col">Answer</th>
                        <th scope="col">Status</th>
                        <th scope="col"></th>
                    </tr>
                    <c:forEach var="question" items="${questionList}">
                        <tr>
                            <td><c:out value="${question.title} "/></td>
                            <td><c:out value="${question.details} "/></td>
                            <td><c:out value="${question.answers} "/></td>
                            <c:set var="status" scope="session" value="${question.status}"/>
                            <c:if test="${status == false}">
                                <td><c:out value="Not Answered"/></td>
                            </c:if>
                            <c:if test="${status == true}">
                                <td><c:out value="Answered"/></td>
                            </c:if>
                            <td>
                                <form method="get" action="/faq/view/${question.id}">
                                    <button class="btn btn-primary" type="submit">View</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</html>
