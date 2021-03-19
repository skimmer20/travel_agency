<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration</title>
</head>
<body>
<h2 class="title">Sign Up</h2>
<form method="post">
    <table>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" placeholder="First Name"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" placeholder="Last Name"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" placeholder="example@email.com"/></td>
        </tr>
        <tr>
            <td>Phone number:</td>
            <td><input type="text" name="phoneNumber" placeholder="+38(098)-123-45-67"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Confirm Password:</td>
            <td><input type="password" name="confirmPassword"/></td>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Sign Up"/></td></tr>
    </table>
</form>
</body>
</html>