<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<html>
<head>
    <title>HSE Detailed Question</title>
</head>
<style>
    textarea {
        resize: none;
        width: 373px;
    }

    header {
        margin-bottom: 30px;
    }
</style>
<body>
    <div class="container">
    <header class="header">
        <nav class="navbar navbar-expand-lg fixed-top py-3">
            <div class="container"><a href="/" class="navbar-brand text-uppercase font-weight-bold">HSE Vaccination</a>
                <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler navbar-toggler-right"><i class="fa fa-bars"></i></button>
                <div id="navbarSupportedContent" class="collapse navbar-collapse">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a href="/faq" class="nav-link text-uppercase font-weight-bold">Visit FAQ</a></li>
                        <li class="nav-item"><a href="/faq/create" class="nav-link text-uppercase font-weight-bold">Create New</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
</div><br/><br/><br/>

<div class="container">
    <h2> Quesion Information: </h2>
    <div class="card">
        <div class="card-body">
            <table class="center">
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="display_question_title" size="45" readonly
                           value="${displayQuestion.title}"
                    />
                </td>
            </tr>
            <tr>
                <th>Details: </th>
                <td>
                    <textarea name="display_question_details" rows="5" readonly>${displayQuestion.details}</textarea>
                </td>

            </tr>
            <tr>
                <th>Answer: </th>
                <td>
                    <textarea name="display_question_answers"  rows="5" readonly>${displayQuestion.answers}</textarea>
                </td>
            </tr>
            <tr>
                <th>Status: </th>
                <td>
                    <input type="text" name="display_question_status" size="45" readonly
                           value="${displayQuestion.status}">
                </td>
            </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
