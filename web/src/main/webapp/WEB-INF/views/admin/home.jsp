<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>Trang chủ</title> -->
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <!-- Breadcrumb -->
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li class="active">Trang quản trị</li>
            </ul>
        </div>

        <!-- Page Content -->
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12 text-center mb-4">
                    <h1 class="text-primary font-weight-bold">
                        Trang Quản Trị Hệ Thống
                    </h1>
                    <p class="text-muted">
                        Bạn đang ở khu vực quản lý hệ thống.
                        Tại đây bạn có thể thực hiện các chức năng theo quyền hạn của mình..
                    </p>
                    <a href="${pageContext.request.contextPath}/trang-chu" class="btn btn-primary">
                        Quay lại Trang chủ
                    </a>
                </div>

                <div class="col-xs-12">
                    <!-- Nội dung quản trị đặt ở đây -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap CSS -->
<%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">--%>

<%--<!-- Font Awesome -->--%>
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">--%>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>