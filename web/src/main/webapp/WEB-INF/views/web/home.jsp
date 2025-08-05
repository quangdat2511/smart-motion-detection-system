<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Hệ thống phát hiện chuyển động thông minh</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS (CDN hoặc thay bằng link nội bộ nếu cần) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center mb-4 text-primary">Hệ thống phát hiện chuyển động thông minh</h1>

    <div class="card mb-4">
        <div class="card-header bg-info text-white">1.1 Giới thiệu sản phẩm</div>
        <div class="card-body">
            <p>Hệ thống phát hiện chuyển động thông minh là một sản phẩm IoT, giúp giám sát các khu vực và gửi cảnh báo ngay khi phát hiện chuyển động. Thiết bị sử dụng cảm biến PIR và vi điều khiển ESP32 để xử lý dữ liệu và đưa ra phản hồi như: bật còi báo động, gửi thông báo qua mạng.</p>
            <p>Sản phẩm có thể áp dụng tại nhà ở, văn phòng, lớp học, kho hàng, hành lang ký túc xá... mang lại giải pháp đơn giản, hiệu quả và tiết kiệm chi phí.</p>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header bg-info text-white">1.2 Sự cần thiết</div>
        <div class="card-body">
            <p>An ninh luôn là vấn đề quan trọng, nhưng nhiều hệ thống giám sát hiện nay có chi phí cao, lắp đặt phức tạp và thiếu tính linh hoạt. Với công nghệ IoT, hệ thống giám sát chuyển động thông minh giúp người dùng dễ dàng phát hiện và xử lý kịp thời các tình huống bất thường.</p>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header bg-info text-white">1.3 Các chức năng chính</div>
        <div class="card-body">
            <ul>
                <li>Phát hiện chuyển động và gửi thông báo qua website, email, và điện thoại kèm hình ảnh.</li>
                <li>Bật còi báo động từ xa nếu phát hiện có xâm nhập nghi ngờ.</li>
                <li>Phân biệt chuyển động có phải do con người gây ra không.</li>
                <li>Truy xuất lịch sử chuyển động và hình ảnh ghi nhận được.</li>
                <li>Hiển thị thông tin lên màn hình LCD: giờ, thông báo ngắn, hình ảnh trang trí.</li>
                <li>Tự bật đèn hỗ trợ camera khi thiếu ánh sáng để chụp rõ hơn.</li>
            </ul>
        </div>
    </div>
</div>

<!-- Bootstrap JS (CDN) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
