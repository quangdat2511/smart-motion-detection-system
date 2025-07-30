<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh sách chuyển động</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style>
        .table-container {
            margin: 40px auto;
            width: 85%;
        }
        .text-center {
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container table-container">
    <div class="page-header text-center">
        <h2>Danh sách các chuyển động</h2>
        <p class="text-muted">Hiển thị thời gian, loại chuyển động và hình ảnh</p>
    </div>

    <table class="table table-bordered table-hover">
        <thead>
        <tr class="info text-center">
            <th>STT</th>
            <th>Thời điểm</th>
            <th>Loại chuyển động</th>
            <th>Hình ảnh</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-center">
            <td>1</td>
            <td>21h55p, 12/7/2025</td>
            <td>Chuyển động của người</td>
            <td><a href="img/image1.jpg" target="_blank">Click vào để xem</a></td>
        </tr>
        <tr class="text-center">
            <td>2</td>
            <td>10h0p, 10/7/2025</td>
            <td>Chuyển động khác</td>
            <td><a href="img/image1.jpg" target="_blank">Click vào để xem</a></td>
        </tr>
        <tr class="text-center">
            <td>3</td>
            <td>22h3p, 9/7/2025</td>
            <td>Chuyển động khác</td>
            <td><a href="img/image1.jpg" target="_blank">Click vào để xem</a></td>
        </tr>
        <tr class="text-center">
            <td>4</td>
            <td>00h0p, 1/7/2025</td>
            <td>Chuyển động khác</td>
            <td><a href="img/image1.jpg" target="_blank">Click vào để xem</a></td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
