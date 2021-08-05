<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <div class="navbar-brand"><img src="resources/images/icon-meal.png"> <spring:message code="app.title"/></div>
        <form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
            <input class="form-control mr-1" type="text" placeholder="Email" name="username">
            <input class="form-control mr-1" type="password" placeholder="Password" name="password">
            <button class="btn btn-success" type="submit">
                <span class="fa fa-sign-in"></span>
            </button>
        </form>
    </div>
</nav>

<div class="jumbotron py-0">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message"><spring:message code="${param.message}"/></div>
        </c:if>
        <sec:authorize access="isAnonymous()">
            <div class="pt-2">
<%--                <a class="btn btn-lg btn-info mt-2" href="profile/register"><spring:message code="app.register"/> &raquo;</a>--%>
                <button type="submit" class="btn btn-lg btn-primary mt-2" onclick="login('user@yandex.ru', 'password')">
                    <spring:message code="app.login"/> User
                </button>
                <button type="submit" class="btn btn-lg btn-primary mt-2" onclick="login('admin@yandex.ru', 'password2')">
                    <spring:message code="app.login"/> Admin
                </button>
            </div>
        </sec:authorize>
        <div class="lead py-4"><spring:message code="app.stackTitle"/> <br>
          </div>
    </div>
</div>
<div class="container lead"><spring:message code="app.description"/>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">
  <c:if test="${not empty param.username}">
  setCredentials("${param.username}", "");
  </c:if>

  function login(username, password) {
    setCredentials(username, password);
    $("#login_form").submit();
  }
  function setCredentials(username, password) {
    $('input[name="username"]').val(username);
    $('input[name="password"]').val(password);
  }
</script>
</body>
</html>