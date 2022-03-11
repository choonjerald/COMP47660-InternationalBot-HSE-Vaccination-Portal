<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<html>
<head>
    <title>HSE Vaccination Ask a Question</title>
</head>
<style>
    .error {
        color: red;
        font-weight: bold;
    }
    textarea {
        resize: none;
        width: 373px;
    }
</style>
<body>
<div class="container">
    <h1> Create a Forum</h1>
    <h2> Fill out the question form: </h2>
    <div class="card">
        <div class="card-body">
            <sf:form action="/faq/create" modelAttribute="question" method="post">
                <div class="form-group row">
                    <label for="title" class="col-sm-2 col-form-label">Title / Subject: </label>
                    <div class="col-sm-5">
                        <input type="text" name="title" size="45"
                               value="<c:out value='${question.title}' />"
                        />
                    </div>
                    <div class="col">
                        <sf:errors path="title" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="details" class="col-sm-2 col-form-label">Details: </label>
                    <div class="col-sm-5">
                        <textarea name="details" rows="5"
                              value="<c:out value='${question.details}' />">
                        </textarea>
                    </div>
                    <div class="col">
                        <sf:errors path="details" cssClass="error"/>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </sf:form>
        </div>
    </div>
</div>
</body>
</html>
