<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Chuyển động gần nhất và thao tác với output</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div class="page-header">
    <h1>
        Lịch sử chuyển động
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            hiển thị chuyển động và hình ảnh
        </small>
    </h1>
</div>

<!-- Hiển thị chuyển động -->
<div class="row">
    <h2 class="text-center">Khu vực hiển thị input</h2>
    <div class="col-sm-6 col-sm-offset-2">
        <div class="panel panel-default">
            <div class="panel-heading text-center" style="font-weight: bold;">
                Thời gian: <fmt:formatDate value="<%= new java.util.Date() %>" pattern="dd/MM/yyyy HH:mm:ss"/>
            </div>
            <div class="panel-body text-center">
                <img src="img/image1.jpg" alt="Motion Image" class="img-responsive" style="margin: auto; max-height: 300px;">
            </div>
        </div>
    </div>
</div>

<!-- Khu vực điều khiển OUTPUT -->
<!-- Khu vực điều khiển OUTPUT + Chatbot -->
<div class="container" style="margin-top: 150px;">
    <h2 class="text-center">Khu vực thao tác với Output</h2>
    <div class="row">
        <!-- Cột: Điều khiển Output -->
        <div class="col-sm-12">
            <div class="row">
                <!-- LED Control -->
                <div class="col-sm-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">Điều khiển LED</div>
                        <div class="panel-body text-center">
                            <button class="btn btn-success" id="btnLedOn">Bật LED</button>
                            <button class="btn btn-danger" id="btnLedOff">Tắt LED</button>
                        </div>
                    </div>
                </div>
                <!-- Buzzer Control -->
                <div class="col-sm-3">
                    <div class="panel panel-warning">
                        <div class="panel-heading text-center">Điều khiển Buzzer</div>
                        <div class="panel-body text-center">
                            <button class="btn btn-success" id="btnBuzzerOn">Bật Buzzer</button>
                            <button class="btn btn-danger" id="btnBuzzerOff">Tắt Buzzer</button>
                        </div>
                    </div>
                </div>
                <!-- LCD Control -->
                <div class="col-sm-3">
                    <div class="panel panel-info">
                        <div class="panel-heading text-center">Gửi nội dung lên LCD</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <input type="text" id="lcdText" class="form-control" placeholder="Nhập nội dung hiển thị">
                            </div>
                            <div class="text-center">
                                <button id="btndisplayMessageOnLcd" class="btn btn-primary">Gửi lên LCD</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Cột: Chatbot -->
                <div class="col-sm-3">
                    <div class="panel panel-success">
                        <div class="panel-heading text-center">Chatbot hỗ trợ</div>
                        <div class="panel-body" style="height: 300px; overflow-y: auto;">
                            <div id="chatArea" style="height: 220px; border: 1px solid #ccc; padding: 10px; overflow-y: auto; background: #f9f9f9;">
                                <p><strong>Bot:</strong> Xin chào! Bạn cần hỗ trợ gì?</p>
                            </div>
                            <div class="input-group">
                                <input type="text" id="chatInput" class="form-control" placeholder="Nhập tin nhắn...">
                                <span class="input-group-btn">
                            <button class="btn btn-success" id="sendChatBtn">Gửi</button>
                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>


<!-- Xử lý AJAX -->
<script>
    function sendOutputForLedOrBuzzer(endpoint) {
        $.ajax({
            url: endpoint,
            type: 'POST',
            success: function(response) {
                alert(response); // vì đây là text/plain
            },
            error: function(xhr) {
                alert(xhr.responseText); // lấy nội dung lỗi dạng text
            }
        });
    }

    $('#btnLedOn').click(function () {
        sendOutputForLedOrBuzzer("/api/led/on");
    });

    $('#btnLedOff').click(function () {
        sendOutputForLedOrBuzzer("/api/led/off");
    });

    $('#btnBuzzerOn').click(function () {
        sendOutputForLedOrBuzzer("/api/buzzer/on");
    });

    $('#btnBuzzerOff').click(function () {
        sendOutputForLedOrBuzzer("/api/buzzer/off");
    });

    $('#btndisplayMessageOnLcd').click(function () {
        const text = $('#lcdText').val();
        if (!text) {
            alert('Vui lòng nhập nội dung hiển thị.');
        }
        else {
            $.ajax({
                url: '/api/lcd',
                type: 'POST',
                data: { message: text }, //
                success: function(response) {
                    alert(response); // vì đây là text/plain
                },
                error: function(xhr) {
                    alert(xhr.responseText); // lấy nội dung lỗi dạng text
                }
            });
        }
    });

    $('#sendChatBtn').click(function () {
        const userMsg = $('#chatInput').val();
        if (userMsg.trim() === "") return;

        $('#chatArea').append('<p><strong>Bạn:</strong> ' + userMsg + '</p>');

        // Giả lập phản hồi
        let botReply = "Tôi chưa được lập trình phản hồi nội dung này.";
        if (userMsg.toLowerCase().includes("led")) {
            botReply = "Để bật/tắt LED, hãy nhấn các nút ở khu vực Điều khiển LED.";
        } else if (userMsg.toLowerCase().includes("buzzer")) {
            botReply = "Bạn có thể bật/tắt Buzzer bằng các nút tương ứng.";
        } else if (userMsg.toLowerCase().includes("lcd")) {
            botReply = "Nhập nội dung vào ô và nhấn gửi để hiển thị trên LCD.";
        }

        setTimeout(() => {
            $('#chatArea').append('<p><strong>Bot:</strong> ' + botReply + '</p>');
            $('#chatArea').scrollTop($('#chatArea')[0].scrollHeight);
        }, 500);

        $('#chatInput').val('');
    });
</script>

</body>
</html>
