<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Danh sách chuyển động</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2 class="text-center">Danh sách các chuyển động</h2>
    <p class="text-muted text-center">Phân trang bằng DisplayTag</p>
        <display:table name="${motionDTO.listResult}" cellspacing="0" cellpadding="0"
                       requestURI="/admin/motion-list" partialList="true" sort="external"
                       size="${motionDTO.totalItems}" defaultsort="2" defaultorder="ascending"
                       id="tableList" pagesize="${motionDTO.maxPageItems}"
                       export="false"
                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                       style="margin: 3em 0 1.5em;">
            <display:column title="STT" class="text-center">
                ${motionDTO.maxPageItems * (motionDTO.page - 1) + tableList_rowNum}
            </display:column>
            <display:column headerClass="text-left" property="time" title="Thời đểm"/>
            <display:column headerClass="text-left" property="motionType" title="Loại chuyển động"/>
            <display:column headerClass="text-left" property="image" title="Hình ảnh"/>
        </display:table>

</div>

</body>
</html>
