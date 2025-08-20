<!DOCTYPE html>
<html lang="vi">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử chuyển động & Điều khiển Output</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .panel-heading {
            font-weight: bold;
        }
        .panel-body input, .panel-body button {
            margin-top: 5px;
        }
        img {
            max-height: 200px;
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb">
            <li>
                <a href="/admin/home">
                    Trang quản trị
                </a>
            </li>
            <li class="active">
                <%--<spring:message code="label.user.list"/>--%>
                Giám sát & Điều khiển
            </li>
        </ul>
        <!-- /.breadcrumb -->
    </div>
    <div class="page-header text-center">
        <h1>Chuyển động gần nhất</h1>
    </div>

    <!-- Hiển thị chuyển động -->
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            Thời gian: <span id="currentTime"></span>
        </div>
        <div class="panel-body text-center">
            <h4 id="motionStatusText" style="color: green;">Đang tải trạng thái...</h4>
            <img id = "motionImage" src="" alt="Motion Image" class="img-responsive center-block">
        </div>
    </div>

    <!-- Khu vực điều khiển OUTPUT -->
    <div class="row text-center">
        <!-- Buzzer -->
        <div class="col-sm-3">
            <div class="panel panel-warning">
                <div class="panel-heading">Buzzer</div>
                <div class="panel-body">
                    <button class="btn btn-success btn-block" id="btnBuzzerOn">Bật</button>
                    <button class="btn btn-danger btn-block" id="btnBuzzerOff">Tắt</button>
                </div>
            </div>
        </div>

        <!-- LCD -->
        <div class="col-sm-3">
            <div class="panel panel-info">
                <div class="panel-heading">LCD</div>
                <div class="panel-body">
                    <input type="text" id="lcdText" class="form-control" placeholder="Tối đa 32 ký tự" maxlength="32">
                    <button class="btn btn-primary btn-block" id="btnDisplayMessageOnLcd">Gửi</button>
                </div>
            </div>
        </div>

        <!-- Servo -->
        <div class="col-sm-3">
            <div class="panel panel-danger">
                <div class="panel-heading">Servo</div>
                <div class="panel-body">
                    <input type="number" id="servoAngle" class="form-control" placeholder="Góc 0-180" min="0" max="180">
                    <button class="btn btn-danger btn-block" id="btnSetServoAngle">Quay</button>
                </div>
            </div>
        </div>
        <!-- Servo -->
        <div class="col-sm-3">
            <div class="panel panel-warning">
                <div class="panel-heading">PushSafer</div>
                <div class="panel-body">
                    <button class="btn btn-success btn-block" id="btnPushSaferOn">Bật thông báo</button>
                    <button class="btn btn-danger btn-block" id="btnPushSaferOff">Tắt thông báo</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    let lastFilename = null; // lưu ảnh cũ

    function updateLatestImage() {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/image",
            method: "GET",
            success: function(filename) {
                if (filename) {
                    if (filename !== lastFilename) {
                        // ảnh mới khác ảnh cũ -> cập nhật
                        $("#motionImage").attr(
                            "src",
                            "${pageContext.request.contextPath}/img/" + filename + "?t=" + new Date().getTime()
                        );

                        $("#motionStatusText").text("Có chuyển động").css("color", "red");
                        updateTimeNow();

                        // lưu lại ảnh hiện tại để so sánh lần sau
                        lastFilename = filename;
                    } else {
                        // ảnh vẫn giống cũ -> không cập nhật thời gian nữa
                        console.log("⚠️ Ảnh không đổi, bỏ qua updateTimeNow()");
                    }
                } else {
                    $("#motionStatusText").text("Không có chuyển động").css("color", "green");
                    lastFilename = null; // reset
                }
            },
            error: function() {
                console.log("❌ Không lấy được ảnh mới nhất!");
            }
        });
    }
    $('#btnDisplayMessageOnLcd').click(() => {
        const message = $('#lcdText').val().trim();
        if (!message) {
            alert("Vui lòng nhập nội dung hiển thị!");
            return;
        }

        $.ajax({
            url: '/api/lcd',   // API ở backend
            type: 'POST',
            data: { message: message }, // truyền message như @RequestParam
            success: function (response) {
                alert(response); // hiển thị thông báo từ backend
            },
            error: function (xhr) {
                alert("❌ Lỗi: " + xhr.responseText);
            }
        });
    });
    // Gọi ngay khi load trang
    updateLatestImage();
    // Cập nhật ảnh mỗi 1 giây
    setInterval(updateLatestImage, 1000);
    function updateTimeNow() {
        const now = new Date();
        const formattedTime = now.toLocaleString('vi-VN', {
            hour12: false,
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        });
        $('#currentTime').text(formattedTime);
    }

    function sendOutput(endpoint, method = 'PUT', data = null) {
        $.ajax({
            url: endpoint,
            type: method,
            data: data,
            success: function (response) {
                alert(response);
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    }
    $('#btnBuzzerOn').click(() => sendOutput('/api/buzzer/on'));
    $('#btnBuzzerOff').click(() => sendOutput('/api/buzzer/off'));
    $('#btnPushSaferOn').click(() => sendOutput('/api/pushsafer/on'));
    $('#btnPushSaferOff').click(() => sendOutput('/api/pushsafer/off'));
    $('#btnSetServoAngle').click(() => {
        const angle = parseInt($('#servoAngle').val());
        if (isNaN(angle) || angle < 0 || angle > 180) {
            alert('Vui lòng nhập số hợp lệ trong khoảng 0-180.');
            return;
        }
        sendOutput('/api/servo/' + angle);
    });
</script>
</body>
</html>
