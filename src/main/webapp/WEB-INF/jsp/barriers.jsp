<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://rac.ray_llc.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/rac.common.js" defer></script>
<script type="text/javascript" src="resources/js/rac.barriers.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h4 class="text-center"><spring:message code="barriers.title"/></h4>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>
        <div class="card border-dark">
            <div class="card-body px-3">
                <form id="filter">
                    <div class="row">
                        <div class="col-1">
                            <label for="ip_address"><spring:message code="barriers.ip_address"/></label>
                        </div>
                        <div class="col-2">
                            <input class="form-control" name="ip_address" id="ipaddress" autocomplete="off">
                        </div>
                        <div class="col-1">
                            <label for="address"><spring:message code="barriers.address"/></label>
                        </div>
                        <div class="col-8">
                            <input class="form-control" name="address" id="addres" autocomplete="off">
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-outline-danger" onclick="clearFilter()">
                    <span class="fa fa-remove"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button class="btn btn-outline-primary" onclick="ctx.updateTable()">
                    <span class="fa fa-filter"></span>
                    <spring:message code="barriers.filter"/>
                </button>
            </div>
        </div>
        <br/>
        <button class="btn btn-outline-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <table class="table table-hover " id="datatable">
            <thead>
            <tr>
                <th><spring:message code="barriers.ip_address"/></th>
                <th><spring:message code="barriers.active"/></th>
                <th><spring:message code="barriers.address"/></th>
                <th><spring:message code="barriers.name"/></th>
                <th><spring:message code="barriers.description"/></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
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
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="state" name="state">

                    <div class="form-group">
                        <div class="row">
                            <div class="col-2">
                                <label for="ip_address" class="col-form-label"><spring:message code="barriers.ip_address"/></label>
                            </div>
                            <div class="col-10">
                                <input type="text" class="form-control" id="ip_address" name="ip_address" placeholder="<spring:message code="barriers.ip_address"/>">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <div class="col-2">
                                <label for="longitude" class="col-form-label"><spring:message code="barriers.longitude"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" class="form-control" id="longitude" name="longitude" placeholder="<spring:message code="barriers.longitude"/>">
                            </div>
                            <div class="col-2">
                                <label for="latitude" class="col-form-label"><spring:message code="barriers.latitude"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" class="form-control" id="latitude" name="latitude" placeholder="<spring:message code="barriers.latitude"/>">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address" class="col-form-label"><spring:message code="barriers.address"/></label>
                        <input type="text" class="form-control" id="address" name="address" placeholder="<spring:message code="barriers.address"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message code="barriers.name"/></label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="barriers.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message code="barriers.description"/></label>
                        <input type="text" class="form-control" id="description" name="description" placeholder="<spring:message code="barriers.description"/>">
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
                <button type="button" class="btn btn-primary" onclick="save()">
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
    <jsp:param name="page" value="barriers"/>
</jsp:include>
</html>