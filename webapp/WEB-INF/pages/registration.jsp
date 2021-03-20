<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="all">
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->
        <h2 class="active"> Sign Up </h2>
        <form method="post">
            <input type="text" id="firstName" class="fadeIn second" name="firstName" placeholder="First Name">
            <input type="text" id="lastName" class="fadeIn third" name="lastName" placeholder="Last Name">
            <input type="text" id="email" class="fadeIn second" name="email" placeholder="example@email.com">
            <input type="text" id="phoneNumber" class="fadeIn third" name="phoneNumber"
                   placeholder="+38(099)333-33-33">
            <input type="text" id="password" class="fadeIn second" name="password" placeholder="Password">
            <input type="text" id="confirmPassword" class="fadeIn third" name="confirmPassword"
                   placeholder="Confirm Password">
            <input type="submit" class="fadeIn fourth" value="Sing Up">
            <h4>Already have an account? <h4 class="underlineHover"><a href="login">Login</a></h4></h4>
        </form>
    </div>
</div>

</body>
</html>