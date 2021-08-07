<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rac" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script src="resources/js/rac.common.js" defer></script>
<script src="resources/js/rac.users.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
<%--        @variable id="userTo" type="ru.ray_llc.rac.to.UserTo"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <h3>${userTo.name} <spring:message code="${register ? 'app.register' : 'app.profile'}"/></h3>
                <form:form class="form-group" modelAttribute="userTo" method="post" action="${register ? 'profile/register' : 'profile'}"
                           charset="utf-8" accept-charset="UTF-8" id="detailsForm">
                    <rac:inputField labelCode="user.name" name="name"/>
                    <rac:inputField labelCode="user.login" name="login"/>
                    <rac:inputField labelCode="user.email" name="email"/>
                    <rac:inputField labelCode="user.password" name="password" inputType="password"/>
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
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>