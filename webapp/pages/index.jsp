<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/a.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/footer.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/pages/css/searchform.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/result.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<c:choose>
    <c:when test="${userLogged=='yes'}">
        <jsp:include page="authheader.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp"/>
    </c:otherwise>
</c:choose>

<section class="home">
</section>
<div style="height: 1000px" class="content">
    <div class="s002"> <!-- This form templates was made by Colorlib (https://colorlib.com) -->
        <form method="post" action="hotelByCityAndDate">
            <fieldset>
                <legend>SEARCH HOTEL</legend>
            </fieldset>
            <div class="inner-form">
                <div class="input-field first-wrap">
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
                        </svg>
                    </div>
                    <input id="search" type="text" placeholder="City" name="city"/>
                </div>
                <div class="input-field second-wrap">
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                        </svg>
                    </div>
                    <input class="datepicker" id="depart" type="text" placeholder="Jun-05-2021" name="fromDate"/>
                </div>
                <div class="input-field third-wrap">
                    <div class="icon-wrap">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                        </svg>
                    </div>
                    <input class="datepicker" id="return" type="text" placeholder="Jun-15-2021" name="toDate"/>
                </div>
                <div class="input-field fifth-wrap">
                    <button class="btn-search" type="submit">SEARCH</button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>



