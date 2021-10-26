<%--
  Created by IntelliJ IDEA.
  User: Alexandr.Yakubov
  Date: 21.10.2021
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import = "java.io.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://rac.ray_llc.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/rac.common.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <div class="col-12">
            <div id="map" style="width: 1100px; height: 800px"></div>
        </div>
        <table class="table table-hover" id="datatable">
            <thead>
            <tr>
                <th class="col-1"><spring:message code="barriers.ip_address"/></th>
                <th class="col-5"><spring:message code="barriers.address"/></th>
                <th class="col-3"><spring:message code="barriers.name"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<%
    // Set refresh, autoload time as 5 seconds
    response.setIntHeader("Refresh", 10);
%>

<script src='https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=7821f2d1-fcc4-42b5-af8e-dfecc28a3beb'></script>
<script type="text/javascript" src="resources/js/rac.barriers_map.js" defer></script>
<script src="resources/js/script.js"></script>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="barriers"/>
</jsp:include>
</html>