<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--***************************************************--%>
    <%--             мета Bootstrapp                       --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>registration</title>
    <%--***************************************************--%>
    <%--            магия Bootstrapp                       --%>
    <%--***************************************************--%>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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
</head>

<body>
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">Home</a></li>
                <li><a href="/registration">Registration</a></li>
                <li></li>
            </ul>
        </div>
    </div>
</div>

<div class="register">
    <form id="details" method="post" action="/login">
        <div class="container">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Login</h3>
                        </div>
                        <div class="panel-body">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger" role="alert">
                                    Invalid username or password.
                                </div>
                            </c:if>
                            <form role="form">
                                <div class="form-group">
                                    <input required
                                            class="form-control"
                                            type="text"
                                            name="login"
                                            placeholder="login">

                                    <div id="errLast"></div>
                                </div>
                                <div class="form-group">
                                    <input required
                                            type="password"
                                            name="password"
                                            class="form-control password-field"
                                            placeholder="password"/>
                                </div>
                                <input type="submit" value="LogIn" class="btn btn-info btn-block">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
