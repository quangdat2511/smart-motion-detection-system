<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang Quản Trị</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .section-title {
            border-left: 5px solid #007bff;
            padding-left: 10px;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
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
                        Tại đây bạn có thể thực hiện các chức năng theo quyền hạn của mình.
                    </p>
                    <a href="${pageContext.request.contextPath}/trang-chu" class="btn btn-primary">
                        Quay lại Trang chủ
                    </a>
                </div>

                <!-- Nội dung hướng dẫn -->
                <div class="container mb-5">

                    <!-- Hướng dẫn bên phải (chuyển thành 1) -->
                    <div class="mb-4">
                        <h4 class="section-title">📌 Ở thanh điều hướng bên phải, bạn có thể thấy:</h4>

                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">1.1 Thông tin tài khoản</div>
                            <div class="card-body">
                                <p>Hiển thị tên đăng nhập, tên đầy đủ và cho phép chỉnh sửa tên.</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">1.2 Đổi mật khẩu</div>
                            <div class="card-body">
                                <p>Thay đổi mật khẩu với 3 bước: nhập mật khẩu hiện tại, mật khẩu mới và xác nhận mật khẩu mới.</p>
                            </div>
                        </div>
                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">1.3 Xem id của thiết bị hiện tại</div>
                            <div class="card-body">
                                <p>Vui lòng nhập đúng mật khẩu của tài khoản hiện tại để có thể xem id của thiết bị mà tài khoản này đang điều khiển</p>
                            </div>
                        </div>
                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">1.4 Chọn thiết bị</div>
                            <div class="card-body">
                                <p>Chọn thiết bị bạn muốn giám sát và điều khiển
                                    Chỉ một thiết bị có thể được giám sát tại một thời điểm.</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">1.5 Thoát</div>
                            <div class="card-body">
                                <p>Đăng xuất khỏi hệ thống bằng nút "Thoát".</p>
                            </div>
                        </div>
                    </div>

                    <!-- Hướng dẫn bên trái (chuyển thành 2) -->
                    <div class="mb-4">
                        <h4 class="section-title">📌 Ở thanh điều hướng bên trái, bạn có thể thấy:</h4>

                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">2.1 Giám sát và điều khiển</div>
                            <div class="card-body">
                                <p>Quan sát hình ảnh và thời điểm của chuyển động gần nhất.</p>
                                <p>Bật/tắt buzzer, hiển thị thông điệp lên LCD, quay servo đến góc mong muốn, bật tắt thông báo về điện thoại.</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">2.2 Danh sách chuyển động</div>
                            <div class="card-body">
                                <p>Xem danh sách các chuyển động kèm hình ảnh đã ghi nhận.</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-header bg-info text-white">2.3 Danh sách tài khoản (chỉ dành cho quản trị viên)</div>
                            <div class="card-body">
                                <p>Xem, tìm kiếm, thêm, sửa, xóa tài khoản và reset mật khẩu.</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12">
                    <!-- Nội dung quản trị đặt ở đây -->
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
