<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Hệ thống phát hiện chuyển động thông minh</title>
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
<div class="container mt-4">

    <!-- Tiêu đề trang -->
    <div class="text-center mb-4">
        <h1 class="text-primary">🌟 Trang chủ</h1>
        <h4 class="text-secondary">Hệ thống phát hiện chuyển động thông minh</h4>
        <p class="lead mt-3">
            Chào mừng bạn đến với hệ thống!
            Dưới đây là hướng dẫn nhanh để bạn có thể sử dụng các chức năng chính của website.
        </p>
        <hr>
    </div>

    <!-- Hướng dẫn bên trái -->
    <div class="mb-4">
        <h4 class="section-title">📌 Ở thanh điều hướng bên trái, bạn có thể thấy:</h4>

        <div class="card mb-3">
            <div class="card-header bg-info text-white">1.1 Giám sát và điều khiển</div>
            <div class="card-body">
                <p>Quan sát hình ảnh và thời điểm của chuyển động gần nhất.</p>
                <p>Bật/tắt buzzer, hiển thị thông điệp lên LCD, quay servo đến góc mong muốn.</p>
            </div>
        </div>

        <div class="card mb-3">
            <div class="card-header bg-info text-white">1.2 Danh sách chuyển động</div>
            <div class="card-body">
                <p>Xem danh sách các chuyển động kèm hình ảnh đã ghi nhận.</p>
            </div>
        </div>

        <div class="card mb-3">
            <div class="card-header bg-info text-white">1.3 Danh sách tài khoản (chỉ dành cho quản trị viên)</div>
            <div class="card-body">
                <p>Xem, tìm kiếm, thêm, sửa, xóa tài khoản và reset mật khẩu.</p>
            </div>
        </div>
    </div>

    <!-- Hướng dẫn bên phải -->
    <div class="mb-4">
        <h4 class="section-title">📌 Ở thanh điều hướng bên phải, bạn có thể thấy:</h4>

        <div class="card mb-3">
            <div class="card-header bg-info text-white">2.1 Thông tin tài khoản</div>
            <div class="card-body">
                <p>Hiển thị tên đăng nhập, tên đầy đủ và cho phép chỉnh sửa tên.</p>
            </div>
        </div>

        <div class="card mb-3">
            <div class="card-header bg-info text-white">2.2 Đổi mật khẩu</div>
            <div class="card-body">
                <p>Thay đổi mật khẩu với 3 bước: nhập mật khẩu hiện tại, mật khẩu mới và xác nhận mật khẩu mới.</p>
            </div>
        </div>

        <div class="card mb-3">
            <div class="card-header bg-info text-white">2.3 Chọn thiết bị</div>
            <div class="card-body">
                <p>Chọn thiết bị bạn muốn giám sát và điều khiển.
                    Chỉ một thiết bị có thể được giám sát tại một thời điểm.</p>
            </div>
        </div>

        <div class="card mb-3">
            <div class="card-header bg-info text-white">2.4 Thoát</div>
            <div class="card-body">
                <p>Đăng xuất khỏi hệ thống bằng nút "Thoát".</p>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
