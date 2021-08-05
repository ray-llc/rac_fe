<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://rac.ray_llc.ru/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/suggestions.css" />

    <script src="https://code.jquery.com/jquery-3.0.0.js"></script>
    <script src="resources/js/jquery.suggestions.js"></script>
    <script src="resources/js/token.js"></script>
    <script src="resources/js/code.js"></script>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
<section class="container">
    <h3>Новая заявка</h3>
    <h5>Укажите адрес</h5>
    <input id="suggestions" name="suggestions" type="text"
           value="Краснодар фрунзе 143" />
    <p>
    <h5>Госномер спец.транспорта</h5>
        <input id="gosnumber" name="gosnumber" type="text" />
    <p>
    <h5>Ответственный</h5>
        <input id="phone" name="phone" type="text"/>
    <p>
    <p>
        <a class="btn btn-outline-primary mr-1" id="fixData"><spring:message code="app.addcase"/></a>
    </p>
</section>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
