<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
    <%@include file="../css/home.css"%>
    .scrolled-down {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }

    .scrolled-up {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }
</style>
<html>
<head>
    <title>Home</title>
</head>
<!-- Navbar-->
<header class="autohide header">
    <nav class="navbar navbar-expand-lg fixed-top py-3">
        <div class="container"><a href="/user/home" class="navbar-brand text-uppercase font-weight-bold">HSE
            Vaccination</a>
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                    class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>

            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active"><a href="/statistic" class="nav-link text-uppercase font-weight-bold">Statistics<span
                            class="sr-only">(current)</span></a></li>
                    <c:choose>
                        <c:when test="${(user.firstVaccine != null && user.secondVaccine != null) ||
                        (user.firstVaccine != null && user.secondVaccine == null) || (userAppointment != null)}">
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item"><a href="/selectVaccinationCentre/"
                                                    class="nav-link text-uppercase font-weight-bold">Book
                                Appointment</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li class="nav-item"><a href="/faq" class="nav-link text-uppercase font-weight-bold">FAQ</a></li>
                    <li class="nav-item"><a href="/logout" class="nav-link text-uppercase font-weight-bold">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- Body -->
<div class="container">
    <div class="pt-5 text-white">
        <header class="py-5 mt-5">
            <h1 class="display-4">You are logged in as <c:out value="${user.firstName}"/>!</h1>
            <h2><c:choose>
                <c:when test="${user.firstVaccine == null && user.secondVaccine == null}">
                    <span class="text-danger">Status: Unvaccinated </span>
                </c:when>
                <c:when test="${user.firstVaccine != null && user.secondVaccine == null}">
                    <span class="text-warning">Status: Partially Vaccinated!</span>
                </c:when>
                <c:otherwise>
                    <span class="text-success">Status: Fully Vaccinated!</span>
                </c:otherwise>
            </c:choose></h2>
            <h4> First Vaccine: <c:out value="${user.firstVaccine}"/></h4>
            <h4> Second Vaccine: <c:out value="${user.secondVaccine}"/></h4>
        </header>

        <div>
            <c:choose>
                <c:when test="${user.firstVaccine != null && user.secondVaccine != null}">
                </c:when>
                <c:when test="${user.firstVaccine != null && user.secondVaccine == null}">
                    <div class="py-5">
                        <h4>Second Dose Appointment Booked</h4>
                    </div>
                </c:when>
                <c:when test="${userAppointment != null}">
                    <div class="py-5">
                        <h4>First Dose Appointment Booked</h4>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="py-5">
                        <a href="/selectVaccinationCentre/" class="btn btn-light btn-lg action-button">Book an
                            Appointment<i class="fa fa-long-arrow-right ml-2"></i></a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div>
            <c:choose>
                <c:when test="${user.firstVaccine != null && user.secondVaccine != null}">
                </c:when>
                <c:otherwise>
                    <h3>Your Booked Appointments</h3>
                    <div class="card">
                        <div class="card-body">
                            <table class="table">
                                <tr>
                                    <th scope="col">Vaccination Centre</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Time</th>
                                    <th scope="col">Type</th>
                                    <th scope="col"></th>
                                </tr>
                                <c:forEach var="appointment" items="${user.appointments}">
                                    <tr>
                                        <td><c:out value="${appointment.vaccinationCentre.name} "/></td>
                                        <td><c:out value="${appointment.date} "/></td>
                                        <td><c:out value="${appointment.time} "/></td>
                                        <td><c:out value="${appointment.appointmentType} "/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${appointment.appointmentType == 'First Dose'}">
                                                    <a class="btn btn-primary"
                                                       href="/cancelAppointment/${appointment.id}"/>Cancel</a>
                                                </c:when>
                                                <c:otherwise>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <br>
        <h3>Your Activity Log</h3>
        <div class="card">
            <div class="card-body">
                <table class="table">
                    <tr>
                        <th scope="col">Time</th>
                        <th scope="col">Activity</th>
                    </tr>
                    <c:forEach var="activity" items="${user.userActivities}">
                        <tr>
                            <td><c:out value="${activity.dateAndTime} "/></td>
                            <td><c:out value="${activity.message} "/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</html>

<script>
    document.addEventListener("DOMContentLoaded", function () {

        el_autohide = document.querySelector('.autohide');

        // add padding-top to bady (if necessary)
        navbar_height = document.querySelector('.navbar').offsetHeight;
        document.body.style.paddingTop = 0;

        if (el_autohide) {
            var last_scroll_top = 0;
            window.addEventListener('scroll', function () {
                let scroll_top = window.scrollY;
                if (scroll_top < last_scroll_top) {
                    el_autohide.classList.remove('scrolled-down');
                    el_autohide.classList.add('scrolled-up');
                } else {
                    el_autohide.classList.remove('scrolled-up');
                    el_autohide.classList.add('scrolled-down');
                }
                last_scroll_top = scroll_top;
            });
            // window.addEventListener
        }
        // if

    });
</script>
