<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FCO - Login</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/css/sb-admin-2.css"/>" rel="stylesheet">

</head>

<body class="bg-gradient-dark">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0 bg-gradient-light">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
<%--                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>--%>
                        <div class="col-lg-6"><img width="100%" src="<c:url value="/resources/img/5.jpg"/>" alt=""></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-dark mb-4">Login form</h1>
                                    <c:if test="${not empty param.error}">
                                        <h3 style="color: red">Wrong email or password!</h3>
                                    </c:if>
                                    <c:if test="${not empty access}">
                                        <h3 style="color: red">${access}</h3>
                                    </c:if>
                                </div>
                                <form:form class="user" method="post">
                                    <div class="form-group">
                                        <input type="text" name="username" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Enter Email Address...">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Password">
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <div class="form-group">
<%--                                        <div class="custom-control custom-checkbox small">--%>
<%--                                            <input type="checkbox" class="custom-control-input" id="customCheck">--%>
<%--                                            <label class="custom-control-label" for="customCheck">Remember Me</label>--%>
<%--                                        </div>--%>
                                    </div>
                                    <button type="submit" class="btn btn-success btn-user btn-block">Login</button>


<%--                                    <hr>--%>
<%--                                    <a href="index.html" class="btn btn-google btn-user btn-block">--%>
<%--                                        <i class="fab fa-google fa-fw"></i> Login with Google--%>
<%--                                    </a>--%>
<%--                                    <a href="index.html" class="btn btn-facebook btn-user btn-block">--%>
<%--                                        <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook--%>
<%--                                    </a>--%>
                                </form:form>
                                <hr>
<%--                                <div class="text-center">--%>
<%--                                    <a class="small" href="forgot-password.html">Forgot Password?</a>--%>
<%--                                </div>--%>
                                <div class="text-center">
                                    <a class="small" style="color:black;" href="/register">Create an Account!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/js/sb-admin-2.min.js"/>"></script>

</body>

</html>
