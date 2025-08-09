<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Hướng dẫn đăng nhập & đăng ký</title>
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
        <h1 class="text-primary">🌟 Hệ thống phát hiện chuyển động thông minh</h1>
        <h4 class="text-secondary">Hướng dẫn đăng nhập & đăng ký</h4>
        <p class="lead mt-3">
            Chào mừng bạn!
            Dưới đây là hướng dẫn nhanh để bắt đầu sử dụng hệ thống.
        </p>
        <hr>
    </div>

    <!-- Hướng dẫn -->
    <div class="mb-4">
        <h4 class="section-title">🔑 Đăng nhập</h4>
        <div class="card mb-3">
            <div class="card-body">
                <ol>
                    <li>Nhấn nút <b>Đăng nhập</b> ở góc trên bên phải.</li>
                    <li>Nhập tên đăng nhập và mật khẩu đã được cấp.</li>
                    <li>Nhấn nút <b>Đăng nhập</b> để vào hệ thống.</li>
                </ol>
            </div>
        </div>

        <h4 class="section-title">📝 Đăng ký tài khoản</h4>
        <div class="card mb-3">
            <div class="card-body">
                <ol>
                    <li>Nhấn nút <b>Đăng ký</b> ở trang đăng nhập.</li>
                    <li>Điền đầy đủ thông tin: họ tên, tên đăng nhập, mật khẩu.</li>
                    <li>Nhấn đăng ký để tạo tài khoản, vai trò mặc định sẽ là OPERATOR(người vận hành)</li>
                </ol>
            </div>
        </div>

        <h4 class="section-title">❓ Quên mật khẩu</h4>
        <div class="card">
            <div class="card-body">
                <p>Liên hệ quản trị viên để được reset lại mật khẩu.</p>
            </div>
        </div>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
