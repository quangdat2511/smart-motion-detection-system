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
    <div class="page-header text-center">
        <h1>Lịch sử chuyển động <small>hiển thị trạng thái và hình ảnh</small></h1>
    </div>

    <!-- Hiển thị chuyển động -->
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            Thời gian: <fmt:formatDate value="<%= new java.util.Date() %>" pattern="dd/MM/yyyy HH:mm:ss"/>
        </div>
        <div class="panel-body text-center">
            <h4 id="motionStatusText" style="color: green;">Đang tải trạng thái...</h4>
            <img src="${pageContext.request.contextPath}/img/image1.jpg" alt="Motion Image" class="img-responsive center-block">
        </div>
    </div>

    <!-- Khu vực điều khiển OUTPUT -->
    <div class="row text-center">
        <!-- LED -->
        <div class="col-sm-3">
            <div class="panel panel-primary">
                <div class="panel-heading">LED</div>
                <div class="panel-body">
                    <button class="btn btn-success btn-block" id="btnLedOn">Bật</button>
                    <button class="btn btn-danger btn-block" id="btnLedOff">Tắt</button>
                </div>
            </div>
        </div>

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
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
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

    $('#btnLedOn').click(() => sendOutput('/api/led/on'));
    $('#btnLedOff').click(() => sendOutput('/api/led/off'));
    $('#btnBuzzerOn').click(() => sendOutput('/api/buzzer/on'));
    $('#btnBuzzerOff').click(() => sendOutput('/api/buzzer/off'));

    $('#btnSetServoAngle').click(() => {
        const angle = parseInt($('#servoAngle').val());
        if (isNaN(angle) || angle < 0 || angle > 180) {
            alert('Vui lòng nhập số hợp lệ trong khoảng 0-180.');
            return;
        }
        sendOutput('/api/servo/' + angle);
    });
    function updateMotionStatus() {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/motion/status",
            method: "GET",
            success: function(status) {
                let color = (status === "Có chuyển động") ? "red" : "green";
                $("#motionStatusText").text(status).css("color", color);
            },
            error: function() {
                console.log("❌ Không lấy được trạng thái!");
            }
        });
    }

    // Gọi lần đầu ngay khi tải trang
    updateMotionStatus();
    // Gọi lại mỗi 1 giây
    setInterval(updateMotionStatus, 1000);
    $('#btnDisplayMessageOnLcd').click(() => {
        const message = $('#lcdText').val();
        if (!message) {
            alert('Vui lòng nhập nội dung.');
            return;
        }
        sendOutput('/api/lcd', 'POST', { message: message });
    });
</script>
</body>
</html>
