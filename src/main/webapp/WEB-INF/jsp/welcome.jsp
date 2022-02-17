<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style><%@include file="../css/welcome.css"%></style>
<html>
<head>
    <title>Welcome</title>
</head>
<div class="header-blue">
    <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
        <div class="container-fluid"><a class="navbar-brand" href="/">HSE Vaccination</a></div>
        <a class="btn btn-light action-button mr-2" role="button" href="/login">Log In</a> <a class="btn btn-light action-button" role="button" href="/registration">Signup</a>
    </nav>
    <div class="container hero">
        <div class="row">
            <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
                <h1>HSE Vaccination Portal</h1>
                <p>The HSE provides public health and social care services to everyone living in Ireland
                    To fight the COVID pandemic, HSE is providing an online reservation system
                    for citizens aged 18 years and over who would like to receive their first COVID vaccination (1st or 2nd dose).<br>
                </p>
                <a href="/registration" class="btn btn-light btn-lg action-button">Register an Account<i class="fa fa-long-arrow-right ml-2"></i></a>
            </div>
            <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block syringe-holder">
                <div class="syringe-mockup"> <img class="syringe" src="https://www.pinclipart.com/picdir/big/80-803622_hypodermic-needle-medicine-syringe-clip-art-wheel-heraldry.png"> <!-- <div class="screen">	</div>	-->
                </div>
            </div>
        </div>
    </div>
</div>

</html>

