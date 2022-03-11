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
                                                class="nav-link text-uppercase font-weight-bold">Logout</a></li>
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
        <div class="row">
            <h1>Aggregated statistics</h1>
        </div>

        <div class="row">
            <div class="column mr-5 ">
                <h2>Highest Vaccined Nationality</h2>
                <h3><c:out value="${mostCommonNationality}"/></h3>
            </div>

            <div class="column mr-5 ">
                <h2>Highest Vaccined Age group</h2>
                <h3><c:out value="${mostCommonAgeGroup}"/></h3>
            </div>

            <div class="column mr-5 ">
                <h2>Registered Males</h2>
                <h3><c:out value="${registeredMales}"/></h3>
            </div>

            <%--            can fill db with initial female data to not through out of bound error--%>
            <%--            <div class="column mr-5 ">--%>
            <%--                <h2>Registered Females</h2>--%>
            <%--                <h3><c:out value="${registeredFemales}" /></h3>--%>
            <%--            </div>--%>

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