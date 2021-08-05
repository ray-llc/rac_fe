<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://rac.ray_llc.ru/functions" %>
<>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css"/>
</head>
<body>

<script type="text/javascript" src="resources/js/rac.common.js" defer></script>
<script type="text/javascript" src="resources/js/rac.tasks.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h4 class="text-center"><spring:message code="app.task"/></h4>
        <p/>
        <div class="row">
            <div class="col-sm-8">
                <table class="table table-hover " id="datatable">
                    <thead>
                    <tr>
                        <th><spring:message code="tasks.address"/></th>
                        <th><spring:message code="tasks.number_auto"/></th>
                        <th><spring:message code="tasks.phone"/></th>
                        <th><spring:message code="tasks.registered"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="col-sm-3">
                <img src="https://static-maps.yandex.ru/1.x/?l=map&pt=37.604,55.3,pmywl100~37.605,55.6,pmrdl100~37.604,55.1,pmywl88~37.605,55.9,pmrdl88"/>

            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
