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

    <title>FCO - Register</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/css/sb-admin-2.css"/>" rel="stylesheet">

</head>

<body class="bg-gradient-dark">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0 bg-gradient-light">
            <!-- Nested Row within Card Body -->
            <div class="row">
<%--                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>--%>
                <div class="col-lg-5"><img width="100%" src="<c:url value="/resources/img/boeing_777_300er_566378.jpg"/>" alt=""></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <c:if test="${not empty message}">
                            <h3 style="color: red">${message}</h3>
                        </c:if>
                        <c:if test="${not empty passwordMessage}">
                            <h3 style="color: red">${passwordMessage}</h3>
                        </c:if>
                        <form:form class="user" method="post" modelAttribute="appUser">
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <form:input path="firstName" class="form-control form-control-user"
                                                placeholder="First Name"/>
                                </div>
                                <div class="col-sm-6">
                                    <form:input path="lastName" class="form-control form-control-user"
                                                placeholder="Last Name"/>
                                </div>
                            </div>
                            <div class="form-group" style="color: red">
                                <form:errors path="firstName">First name is mandatory!</form:errors>
                            </div>
                            <div class="form-group" style="color: red">
                                <form:errors path="lastName">Last name is mandatory!</form:errors>
                            </div>
                            <div class="form-group">
                                <form:input path="email" type="email" class="form-control form-control-user"
                                            placeholder="Email Address"/>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <form:input path="password" type="password" class="form-control form-control-user"
                                                placeholder="Password"/>
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user" name="rePassword"
                                           placeholder="Repeat Password">
                                </div>
                            </div>
                            <div class="form-group" style="color: red">
                                <form:errors path="password">Password is mandatory!</form:errors>
                            </div>
                            <button type="submit" class="btn btn-success btn-user btn-block">Register Account</button>

                            <%--                            <hr>--%>
                            <%--                            <a href="index.html" class="btn btn-google btn-user btn-block">--%>
                            <%--                                <i class="fab fa-google fa-fw"></i> Register with Google--%>
                            <%--                            </a>--%>
                            <%--                            <a href="index.html" class="btn btn-facebook btn-user btn-block">--%>
                            <%--                                <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook--%>
                            <%--                            </a>--%>
                        </form:form>
                        <hr>
<%--                        <div class="text-center">--%>
<%--                            <a class="small" href="forgot-password.html">Forgot Password?</a>--%>
<%--                        </div>--%>
                        <div class="text-center">
                            <a class="small" style="color: black" href="/login">Already have an account? Login!</a>
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
