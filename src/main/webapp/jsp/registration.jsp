<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--***************************************************--%>
    <%--                   мета Bootstrap                  --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <%--***************************************************--%>
    <%--                  магия Bootstrap                  --%>
    <%--***************************************************--%>
    <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>"
          rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <%--    css styles--%>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url(https://png.pngtree.com/element_origin_min_pic/16/12/09/77619c843826cf8c9184bcf626d14f49.jpg);
            font: 17px/23px 'Lucida Sans', sans-serif;
        }

        .register {
            padding-top: 70px;
        }
    </style>
    <script type="text/javascript">
        function passwordEqualsValidation() {
            var pass1 = document.getElementById('pass1');
            var pass2 = document.getElementById('pass2');
            var message = document.getElementById('confirmMessage');
            var goodColor = "#66cc66";
            var badColor = "#ff6666";
            var threshold = 3;
            if (pass1.value.length < threshold || pass2.value.length < threshold) {
                pass2.style.backgroundColor = "";
                message.innerHTML = "";
            } else {
                if (pass1.value === pass2.value) {
                    pass2.style.backgroundColor = goodColor;
                    message.style.color = goodColor;
                    message.innerHTML = "Passwords match.";
                } else {
                    pass2.style.backgroundColor = badColor;
                    message.style.color = badColor;
                    message.innerHTML = "Passwords do not match.";
                }
            }
        }

        function check_pass() {
            document.getElementById('submit').disabled = document.getElementById('pass1').value !== document.getElementById('pass2').value;
        }
    </script>
</head>

<body>
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/about"/>">About</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/"/>">Home</a></li>
                <li><a href="<c:url value="/login"/>">Log In</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="register">
    <form id="details" method="post" action="<c:url value="/registration"/>">
        <div class="container">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Registration
                                <small> enjoy with ByMeService...</small>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger" role="alert">
                                        ${param.error}
                                </div>
                            </c:if>
                            <form:form role="form">
                                <div class="form-group">
                                    <small>Username</small>
                                    <input required autofocus class="form-control" type="text" name="login" minlength="1" maxlength="30" placeholder="Enter your username">
                                    <div id="errLast"></div>
                                </div>
                                <%--валидация пароля--%>
                                <div class="form-group">
                                    <small>Password</small>
                                    <input required type="password" name="password" id="pass1" minlength="5" maxlength="30" class="form-control password-field" placeholder="Enter your password"
                                           onkeyup="passwordEqualsValidation(); return true;" onchange='check_pass();'/>
                                </div>
                                <div class="form-group">
                                    <small>Confirm Password</small>
                                    <input required type="password" id="pass2" class="form-control password-field" placeholder="Enter your password again" onkeyup="passwordEqualsValidation(); return false;"
                                           onchange='check_pass();'/>
                                    <span id="confirmMessage" class="confirmMessage"></span>
                                </div>
                                <div class="form-group">
                                    <small>Name</small>
                                    <input required type="text" name="name" class="form-control" placeholder="John Doe">
                                </div>
                                <div class="form-group">
                                    <small>Phone</small>
                                    <input required type="number" name="phoneNumber" class="form-control phone" placeholder="79381234567">
                                </div>
                                <div class="form-group">
                                    <small>Email</small>
                                    <input required type="text" name="email" class="form-control" placeholder="john.doe@example.com"/>
                                </div>
                                <div class="form-group">
                                    <select class="form-control" name="cityId">
                                        <c:forEach var="item" items="${cities}">
                                            <option value="${item.id}"${item.id == selected ? 'selected' : ''}>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <input type="submit" value="Register" class="btn btn-info btn-block" id="submit"
                                       disabled>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
