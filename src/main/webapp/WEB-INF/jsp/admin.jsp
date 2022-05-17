<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
    <%@include file="../css/admin.css"%>
    .scrolled-down {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }

    .scrolled-up {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }

    .Qtable th, td {
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
    <title>Admin Home</title>
</head>
<header class="autohide header">
    <nav class="navbar navbar-expand-lg fixed-top py-3">
        <div class="container"><a href="/admin/home" class="navbar-brand text-uppercase font-weight-bold">HSE
            Vaccination</a>
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"
                    class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>

            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a href="/faq" class="nav-link text-uppercase font-weight-bold">FAQ</a></li>
                    <li class="nav-item"><a href="/statistic" class="nav-link text-uppercase font-weight-bold">Statistics</a>
                    </li>
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
            <h1 class="display-4">You are logged in as admin</h1>
        </header>
        <div class="py-5">
            <a href="/admin/home" class="btn btn-light btn-lg action-button">Reload Appointments<i
                    class="fa fa-long-arrow-right ml-2"></i></a>
        </div>
        <h3>Booked Appointments</h3>
        <div class="card">
            <div class="card-body">
                <table class="table">
                    <tr>
                        <th scope="col">Vaccination Centre</th>
                        <th scope="col">Date</th>
                        <th scope="col">Time</th>
                        <th scope="col">Name</th>
                        <th scope="col">Surname</th>
                        <th scope="col">Type</th>
                        <th scope="col"></th>
                    </tr>
                    <c:forEach var="appointment" items="${appointments}">
                        <tr>
                            <td><c:out value="${appointment.vaccinationCentre.name} "/></td>
                            <td><c:out value="${appointment.date} "/></td>
                            <td><c:out value="${appointment.time} "/></td>
                            <td><c:out value="${appointment.user.firstName} "/></td>
                            <td><c:out value="${appointment.user.surname} "/></td>
                            <td><c:out value="${appointment.appointmentType} "/></td>
                            <td>
                                <a class="btn btn-primary" href="/viewAppointment/${appointment.id}"/> View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <br>
        <h3>Unanswered Questions</h3>
        <div class="card">
            <div class="card-body">
                <table class="table">
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Details</th>
                        <th scope="col">Status</th>
                        <th scope="col"></th>
                    </tr>
                    <c:forEach var="question" items="${adminQList}">
                        <tr>
                            <c:set var="status" scope="session" value="${question.status}"/>
                            <c:if test="${status == false}">
                                <td id="title"><c:out value="${question.title} "/></td>
                                <td><c:out value="${question.details} "/></td>
                                <c:set var="status" scope="session" value="${question.status}"/>
                                <c:if test="${status == false}">
                                    <td><c:out value="Not Answered"/></td>
                                </c:if>
                                <c:if test="${status == true}">
                                    <td><c:out value="Answered"/></td>
                                </c:if>
                                <td>
                                    <form method="get" action="/admin/faq/edit/${question.id}">
                                        <button class="btn btn-primary" type="submit">Edit</button>
                                    </form>
                                </td>
                            </c:if>
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