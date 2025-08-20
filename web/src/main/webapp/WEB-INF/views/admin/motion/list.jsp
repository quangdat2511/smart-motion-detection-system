<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Danh sách chuyển động</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<div class="container">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <a href="/admin/home">
                    Trang quản trị
                </a>
            </li>
            <li class="active">
                Danh sách các chuyển động
            </li>
        </ul>
    </div>

    <h2 class="text-center">Danh sách các chuyển động</h2>
    <p class="text-muted text-center">Phân trang bằng DisplayTag</p>

    <display:table name="${motionRequestDTO.listResult}" cellspacing="0" cellpadding="0"
                   requestURI="/admin/motion-list" partialList="true" sort="external"
                   size="${motionRequestDTO.totalItems}" defaultsort="2" defaultorder="ascending"
                   id="tableList" pagesize="${motionRequestDTO.maxPageItems}"
                   export="false"
                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                   style="margin: 3em 0 1.5em;">
        <display:column title="STT" class="text-center">
            ${motionRequestDTO.maxPageItems * (motionRequestDTO.page - 1) + tableList_rowNum}
        </display:column>
        <display:column headerClass="text-left" property="time" title="Thời điểm"/>
        <display:column headerClass="text-left" property="motionType" title="Loại chuyển động"/>
        <display:column title="Hình ảnh" headerClass="text-left">
            <c:if test="${not empty tableList.image}">
                <a href="#" data-toggle="modal" data-target="#imageModal"
                   data-img="${pageContext.request.contextPath}/img/${tableList.image}">
                    <img src="${pageContext.request.contextPath}/img/${tableList.image}"
                         alt="motion image"
                         style="max-width:120px; max-height:120px;"/>
                </a>
            </c:if>
        </display:column>
    </display:table>
</div>

<!-- Modal hiển thị ảnh lớn -->
<div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-body text-center">
                <img id="modalImage" src="" class="img-fluid" style="margin: 0 auto;"/>
            </div>
        </div>
    </div>
</div>


<script>
    $('#imageModal').on('show.bs.modal', function (event) {
        var link = $(event.relatedTarget);
        var imgSrc = link.data('img');
        $('#modalImage').attr('src', imgSrc);
    });
</script>

</body>
</html>
