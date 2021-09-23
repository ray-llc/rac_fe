<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rac" tagdir="/WEB-INF/tags" %>
<%--<%@ taglib prefix="fn" uri="http://rac.ray_llc.ru/functions" %>--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel='stylesheet'
      href='https://cdn.jsdelivr.net/npm/suggestions-jquery@latest/dist/css/suggestions.min.css'>
<link rel="stylesheet" href="resources/css/style.css">


<body>

<jsp:include page="fragments/bodyHeader.jsp"/>


<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>

<div class="jumbotron pt-4">
    <section class="container">
        <div class="row">
            <div class="col-5 offset-3">
                <form:form class="form-group" modelAttribute="taskTo" method="post"
                           action="${'profile/tasks/'}"
                           charset="utf-8" accept-charset="UTF-8" id="detailsForm">

                    <h4>Создание заявки</h4>
                    <p>Ткните мышкой в дом на карте, чтобы увидеть адрес:</p>
                    <p><input type="text" id="address" name="address" value=""  placeholder="<spring:message code="tasks.address"/>" size="50"></p>
                    <p><input type="text" id="latitude" name="latitude" value="" placeholder="<spring:message code="tasks.latitude"/>">
                    <input type="text" id="longitude" name="longitude" value="" placeholder="<spring:message code="tasks.longitude"/>"></p>
                    <p><input type="text" id="number_auto" name="number_auto" placeholder="<spring:message code="tasks.number_auto"/>"></p>
                    <p><input type="text" id="phone" name="phone" placeholder="<spring:message code="tasks.phone"/>"></p>

                    <p><input type="hidden" id="name" name="name" value="name"></p>

                    <div class="text-right">
                        <a class="btn btn-secondary" href="#" onclick="window.history.back()">
                            <span class="fa fa-close"></span>
                            <spring:message code="common.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary" onclick="save()">
                            <span class="fa fa-check"></span>
                            <spring:message code="common.save"/>
                        </button>
                    </div>
                </form:form>
            </div>
            <div id="map" style="width: 1200px; height: 500px"></div>
        </div>
    </section>
</div>
<script src='https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=7821f2d1-fcc4-42b5-af8e-dfecc28a3beb'></script>
<script src="resources/js/script.js"></script>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>