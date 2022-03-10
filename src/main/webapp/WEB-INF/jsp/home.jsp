<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style><%@include file="../css/home.css"%></style>
<html>
<head>
    <title>Home</title>
</head>
<!-- Navbar-->
<header class="header">
    <nav class="navbar navbar-expand-lg fixed-top py-3">
        <div class="container"><a href="/" class="navbar-brand text-uppercase font-weight-bold">HSE Vaccination</a>
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>

            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active"><a href="#" class="nav-link text-uppercase font-weight-bold">Home<span class="sr-only">(current)</span></a></li>
                    <li class="nav-item"><a href="/selectVaccinationCentre/" class="nav-link text-uppercase font-weight-bold">Book Appointment</a></li>
                    <li class="nav-item"><a href="/logout" class="nav-link text-uppercase font-weight-bold">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- Body -->
<div class="container">
    <div class="pt-5 text-white">
        <header class="py-5 mt-5">
            <h1 class = "display-4">You are logged in as <c:out value="${user.firstName}" />!</h1>
        </header>
        <div class="py-5">
            <a href="/selectVaccinationCentre/" class="btn btn-light btn-lg action-button">Book an Appointment<i class="fa fa-long-arrow-right ml-2"></i></a>
        </div>
        <h3>Your Booked Appointments</h3>
        <div class="card">
            <div class="card-body">
                <table class="center">
                    <tr>
                        <th>Vaccination Centre </th>
                        <th>Date </th>
                        <th>Time </th>
                    </tr>
                    <c:forEach var="appointment" items="${user.appointments}">
                        <tr>
                            <td><c:out value="${appointment.vaccinationCentre.name} " /></td>
                            <td><c:out value="${appointment.date} " /></td>
                            <td><c:out value="${appointment.time} " /></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</html>