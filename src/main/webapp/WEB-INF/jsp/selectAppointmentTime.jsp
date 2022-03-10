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
    <h1> Book an Appointment</h1>
    <h2> Select Appointment Time</h2>
    <div class="card">
        <div class="card-body">
            <table class="center">
                <tr>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Type</th>
                    <th></th>
                </tr>
                <c:forEach var="appointment" items="${listAppointments}">
                    <tr>
                        <td><c:out value="${appointment.date}" /></td>
                        <td><c:out value="${appointment.time}" /></td>
                        <td><c:out value="${appointment.appointmentType}" /></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/bookAppointment/${appointment.id}"  />Book</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
