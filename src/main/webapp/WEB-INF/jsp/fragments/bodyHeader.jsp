<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="barriers" class="navbar-brand"><img src="resources/images/icon-meal.png"> <spring:message
                    code="app.title"/></a>
        <sec:authorize access="isAuthenticated()">
            <form:form class="form-inline my-2" action="logout" method="post">
                <sec:authorize access="hasRole('ADMIN')">
                    <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
                </sec:authorize>
                <sec:authorize access="hasRole('USER')">
                    <a class="btn btn-info mr-1" href="addcase"><spring:message code="app.addcase"/></a>
                </sec:authorize>
                <a class="btn btn-info mr-1" href="tasks"><spring:message code="app.task"/></a>
                <a class="btn btn-info mr-1" href="barriers"><spring:message code="app.barriers"/></a>
                <a class="btn btn-info mr-1" href="map"><spring:message code="app.map"/></a>
                <%--                <a class="btn btn-lg btn-info mt-2" href="profile/register"><spring:message code="app.register"/> &raquo;</a>--%>
                <a class="btn btn-info mr-1" href="profile">${userTo.name} <spring:message code="app.profile"/></a>
                <button class="btn btn-primary my-1" type="submit">
                    <span class="fa fa-sign-out"></span>
                </button>
            </form:form>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <form:form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
                        <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                        <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                        <button class="btn btn-success" type="submit">
                            <span class="fa fa-sign-in"></span>
                        </button>
                    </form:form>
                </li>
                </sec:authorize>
<%--                <li class="nav-item dropdown">--%>
<%--                    <a class="dropdown-toggle nav-link my-1 ml-2" data-toggle="dropdown">${pageContext.response.locale}</a>--%>
<%--                    <div class="dropdown-menu">--%>
<%--                        <a class="dropdown-item" href="login?language=ru">Русский</a>--%>
<%--                        <a class="dropdown-item" href="login?language=en">English</a>--%>
<%--                    </div>--%>
<%--                </li>--%>
            </ul>
        </div>

    </div>
</nav>
