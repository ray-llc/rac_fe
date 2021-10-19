<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://rac.ray_llc.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/rac.common.js" defer></script>
<script type="text/javascript" src="resources/js/rac.tasks.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron pt-4">
    <div class="container">
        <h4 class="text-center"><spring:message code="tasks.title"/></h4>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>
        <br/>
        <button class="btn btn-outline-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <table class="table table-hover " id="datatable">
            <thead>
            <tr>
                <th class="col-4"><spring:message code="tasks.address"/></th>
                <th class="col-1"><spring:message code="tasks.number_auto"/></th>
                <th class="col-1"><spring:message code="tasks.phone"/></th>
                <th class="col-2"><spring:message code="tasks.registered"/></th>
                <th class="col-1"></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
<%--                    <input type="hidden" id="id" name="id">--%>
<%--                    <input type="hidden" id="enabled" name="enabled">--%>
<%--                        <input type="hidden" id="name" name="name" value="инциндент">--%>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-2">
                                <label for="longitude" class="col-form-label"><spring:message
                                        code="tasks.longitude"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" class="form-control" id="longitude"
                                       name="longitude"
                                       placeholder="<spring:message code="tasks.longitude"/>">
                            </div>
                            <div class="col-2">
                                <label for="latitude" class="col-form-label"><spring:message
                                        code="tasks.latitude"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" class="form-control" id="latitude"
                                       name="latitude"
                                       placeholder="<spring:message code="tasks.latitude"/>">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address" class="col-form-label"><spring:message
                                code="tasks.address"/></label>
                        <input type="text" class="form-control" id="address" name="address"
                               placeholder="<spring:message code="tasks.address"/>">
                    </div>

                    <div class="form-group">
                        <label for="number_auto" class="col-form-label"><spring:message
                                code="tasks.number_auto"/></label>
                        <input type="text" class="form-control" id="number_auto" name="number_auto"
                               placeholder="<spring:message code="tasks.number_auto"/>">
                    </div>

                    <div class="form-group">
                        <label for="phone" class="col-form-label"><spring:message
                                code="tasks.phone"/></label>
                        <input type="text" class="form-control" id="phone" name="phone"
                               placeholder="<spring:message code="tasks.phone"/>">
                    </div>
                    <div id="map" style="width: 470px; height: 370px"></div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"
                        onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()" id="addButton">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<script src='https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=7821f2d1-fcc4-42b5-af8e-dfecc28a3beb'></script>
<script src="resources/js/script.js"></script>

<jsp:include page="fragments/footer.jsp"/>

</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="tasks"/>
</jsp:include>

</html>
