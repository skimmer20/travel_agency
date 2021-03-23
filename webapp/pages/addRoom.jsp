<jsp:useBean id="hotel" scope="request" type="javax.xml.stream.util.StreamReaderDelegate"/>
<%--
  Created by IntelliJ IDEA.
  User: yuriismac
  Date: 3/22/21
  Time: 3:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>Manager page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="css/addHotel.css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>

<div class="wrapper d-flex align-items-stretch">
    <nav id="sidebar">
        <div class="custom-menu">
            <button type="button" id="sidebarCollapse" class="btn btn-primary">
                <i class="fa fa-bars"></i>
                <span class="sr-only">Toggle Menu</span>
            </button>
        </div>
        <h1><a href="index.jsp" class="logo">Travel Agency</a></h1>
        <ul class="list-unstyled components mb-5">
            <li>
                <a href="manager"><span class="fa fa-home mr-3"></span> Homepage</a>
            </li>
            <li class="active">
                <a href="add-hotel"><span class="fa fa-hotel mr-3"></span>Add Hotels</a>
            </li>
            <li>
                <a href="#"><span class="fa fa-sticky-note mr-3"></span> Statistics</a>
            </li>
        </ul>

    </nav>
    <div id="content" class="p-4 p-md-5 pt-5">
        <div class="singleHotel" id="singleHotel">
            <h2 class="mb-4">Name of the hotel: ${hotel.name}</h2>
            <p>Rating: ${hotel.rating}/5</p>
            <p>Country: ${hotel.country}</p>
            <p>City: ${hotel.city}</p>
            <p>Count of rooms: ${hotel.room_count}</p>
        </div>
        <form method="post" id="myForm">
            <h2 class="mb-4">Adding Room</h2>
            <div class="form-group">
                <label for="roomType">Type</label>
                <input type="text" class="form-control type" id="roomType" name="roomType" aria-describedby="type"
                       placeholder="Enter a type of room">
            </div>
            <div class="wifi">
                Wifi: &#160;
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="wifi" id="wifi1"
                           value="true">
                    <label class="form-check-label" for="wifi1">yes</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="wifi" id="wifi2"
                           value="false">
                    <label class="form-check-label" for="wifi2">no</label>
                </div>
            </div>
            <div class="breakfast">
                Breakfast: &#160;
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="breakfast" id="breakfast1"
                           value="true">
                    <label class="form-check-label" for="breakfast1">yes</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="breakfast" id="breakfast2"
                           value="false">
                    <label class="form-check-label" for="breakfast2">no</label>
                </div>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" step="0.01" class="form-control price" name="price" id="price"
                       aria-describedby="price"
                       placeholder="Enter a price of room">
            </div>
            <button type="submit" class="btn btn-primary btn-lg addroom" id="saveRoom">Save</button>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
