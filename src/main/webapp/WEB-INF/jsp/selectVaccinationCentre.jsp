<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<html>
<head>
    <title>HSE Vaccination Appointment Registration</title>
</head>
<style>
    .error {
        color: red;
        font-weight: bold;
    }
</style>
<body>
<div class="container">
    <br>
    <h1> Book an Appointment</h1>
    <div class="card">
        <div class="card-body">
            <h2> Select a Vaccination Centre</h2>
            <table class="table">
                <c:forEach var="vaccinationCentre" items="${listVaccinationCentres}">
                    <tr>
                        <td><c:out value="${vaccinationCentre.name}"/></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/selectAppointmentTime/${vaccinationCentre.id}"/>View Time Slots</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
