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
    <h1> Appointment Details</h1>
    <div class="card">
        <div class="card-body">
            <table class="table">
                <tr>
                    <th scope="col">Vaccination Centre</th>
                    <th scope="col">Date</th>
                    <th scope="col">Time</th>
                    <th scope="col">Type</th>
                </tr>
                <tr>
                    <td><c:out value="${appointment.vaccinationCentre.name}"/></td>
                    <td><c:out value="${appointment.date}"/></td>
                    <td><c:out value="${appointment.time}"/></td>
                    <td><c:out value="${appointment.appointmentType}"/></td>
                </tr>
            </table>
        </div>
    </div>
    <br>
    <h2> Patient Details</h2>
    <div class="card">
        <div class="card-body">
            <table class="table">
                <tr>
                    <th>Firstname:</th>
                    <td><c:out value="${user.firstName}"/></td>
                </tr>
                <tr>
                    <th>Surname:</th>
                    <td><c:out value="${user.surname}"/></td>
                </tr>
                <tr>
                    <th>Date of Birth:</th>
                    <td><c:out value="${user.DOB}"/></td>
                </tr>
                <tr>
                    <th>Age Group:</th>
                    <td><c:out value="${user.ageGroup}"/></td>
                </tr>
                <tr>
                    <th>Sex:</th>
                    <td><c:out value="${user.sex}"/></td>
                </tr>
                <tr>
                    <th>PPS:</th>
                    <td><c:out value="${user.PPS}"/></td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td><c:out value="${user.address}"/></td>
                </tr>
                <tr>
                    <th>Phone Number:</th>
                    <td><c:out value="${user.phone}"/></td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td><c:out value="${user.email}"/></td>
                </tr>
                <tr>
                    <th>Nationality:</th>
                    <td><c:out value="${user.nationality}"/></td>
                </tr>
                <tr>
                    <th>First Dose:</th>
                    <td><c:out value="${user.firstVaccine}"/></td>
                </tr>
            </table>
        </div>
    </div>
    <br>
    <h2> Record Vaccination</h2>
    <div class="card">
        <div class="card-body">
            <h3> Select the vaccine that was administered: </h3>
            <table class="table">
                <tr>
                    <td>
                        <a href="/recordVaccination/${user.id}/${"pfizer"}" class="btn btn-dark btn-lg action-button">Pfizer<i
                                class="fa fa-long-arrow-right ml-2"></i></a>
                    </td>
                    <td>
                        <a href="/recordVaccination/${user.id}/${"moderna"}" class="btn btn-dark btn-lg action-button">Moderna<i
                                class="fa fa-long-arrow-right ml-2"></i></a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
