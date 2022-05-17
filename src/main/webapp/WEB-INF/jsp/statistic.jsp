<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<style>
    <%@include file="../css/welcome.css"%>
    .scrolled-down {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }

    .scrolled-up {
        transform: translateY(0);
        transition: all 0.3s ease-in-out;
    }

    table {
        margin: 0 auto;
        border-collapse: collapse;
        overflow: hidden;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }

    th, td {
        padding: 15px;
        color: white;
        text-align: center;
        font-size: large;
        font-weight: bold;
    }

    td {
        background-color: rgba(255, 255, 255, 0.2);
    }

    th {
        background-color: #55608f;
        text-align: left;
    }

    body {
        background: linear-gradient(45deg, #49a09d, #5f2c82);
    }

    .vaccine-table th, td {
        font-size: medium;
    }
</style>
<html>
<head>
    <title>Statistics</title>
</head>
<div class="autohide header-blue">
    <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
        <div class="container-fluid"><a class="navbar-brand" href="/">HSE Vaccination</a></div>
        <div id="navbarSupportedContent" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${role == '[USER]' || role == '[ADMIN]'}">
                        <li class="nav-item"><a href="/logout"
                                                class="nav-link text-uppercase font-weight-bold text-white">Logout</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a href="/login"
                                                class="nav-link text-uppercase font-weight-bold text-white">Login</a>
                        </li>
                        <li class="nav-item"><a href="/registration"
                                                class="nav-link text-uppercase font-weight-bold text-white">Register</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </nav>
    <div class="container hero">
        <div class="row">
            <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
                <h1>HSE Vaccination Statistics</h1>
                <p>Data visualisation for vaccination. The statistics and graphs in this section are built
                    using confirmed figures provided by the Health Service Executive (HSE),
                    and are updated following official release of new data.<br>
                </p>
            </div>
            <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block syringe-holder">
                <div class="syringe-mockup"><img class="syringe"
                                                 src="https://www.pinclipart.com/picdir/big/80-803622_hypodermic-needle-medicine-syringe-clip-art-wheel-heraldry.png">
                    <!-- <div class="screen">	</div>	-->
                </div>
            </div>
        </div>

        <div class="mt-5 text-center">
            <h1 class="text-white mb-5">Aggregated Statistics</h1>

            <div class="mt-5 text-center">
                <div class="container">
                    <table>
                        <thead>
                        <tr>
                            <th>Highest Vaccinated Nationality</th>
                            <th>Highest Vaccinated Age Group</th>
                            <th>Registered Males</th>
                            <th>Registered Females</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${mostCommonNationality}</td>
                            <td>${mostCommonAgeGroup}</td>
                            <td>${registeredMales}</td>
                            <td>${registeredFemales}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="container mt-5">
                <table class="vaccine-table">
                    <thead>
                    <tr>
                        <th>First Pfizer Vaccine Count</th>
                        <th>Second Pfizer Vaccine Count</th>
                        <th>First Moderna Vaccine Count</th>
                        <th>Second Moderna Vaccine Count</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${firstVaccinePfizerCount}</td>
                        <td>${secondVaccinePfizerCount}</td>
                        <td>${firstVaccineModernaCount}</td>
                        <td>${secondVaccineModernaCount}</td>
                    </tr>
                    </tbody>
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
