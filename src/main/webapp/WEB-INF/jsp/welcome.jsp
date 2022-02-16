<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<html>
<head>
    <title>Welcome</title>
</head>
<header>
    <h1 class="text-center">HSE Vaccination Portal</h1>
</header>
<body>
<div class="container">
    <div class="card align-items-center m-2">
        <div class="card-body">

            <div class="text-center">
                <h1>Register an Account</h1>
            </div>
            <div class="d-flex justify-content-center">
                <h2>
                    <a href="/new" class="btn btn-primary">Create Account</a>
                </h2>
            </div>
        </div>
    </div>

    <div class="card align-items-center m-2">
        <div class="card-body">
            <form class="align-items-center">
                <h1>Login to Existing Account</h1>
                <div class="form-group row">
                    <div class="col">
                        <label for="email" class="col-form-label">Email</label>
                    </div>
                    <div class="col">
                        <input type="text" name="email" size="45"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col">
                        <label for="password" class="col-form-label">Password</label>
                    </div>
                    <div class="col">
                        <input type="text" name="password" size="45"/>
                    </div>
                </div>
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>