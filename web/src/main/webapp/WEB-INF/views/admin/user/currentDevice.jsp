<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thiết bị hiện tại</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {}
            </script>
            <ul class="breadcrumb">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/home">Trang quản trị</a>
                </li>
                <li class="active">Thiết bị hiện tại</li>
            </ul>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                            ${messageResponse}
                        </div>
                    </c:if>
                    <div id="currentDeviceForm">
                        <form:form id="formCurrentDevice" class="form-horizontal" name="formCurrentDevice">
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    Nhập mật khẩu
                                </label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="password" name="password"
                                           placeholder="Mật khẩu tài khoản...">
                                </div>
                            </div>
                            <!-- Btn -->
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="button" class="btn btn-success btn-bold" value="Xem thiết bị" id="btnCurrentDevice"/>
                                    <button type="button" class="btn btn-danger" onclick="window.history.back();">
                                        Hủy thao tác
                                    </button>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="userId"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('#btnCurrentDevice').click(function () {
                let password = $('#password').val().trim();

                if (password === "") {
                    alert("Vui lòng nhập mật khẩu");
                    return;
                }
                var userId = $('#userId').val();
                // Gọi API kiểm tra mật khẩu
                $.ajax({
                    url: '/api/user/check-password/' + userId,
                    type: 'GET',
                    data: { password: password },
                    success: function(res) {
                        // mật khẩu đúng
                        getCurrentDevice(password);
                    },
                    error: function(xhr) {
                        // mật khẩu sai
                        alert(xhr.responseText);
                    }
                });
            });
        });

        function getCurrentDevice(password) {
            $.ajax({
                url: '/api/device/current',
                type: 'GET',
                data: { password: password },
                success: function(response) {
                    alert(response);
                },
                error: function(xhr) {
                    alert("Lỗi khi kiểm tra thiết bị: " + xhr.responseText);
                }
            });
        }
    </script>

</div>
</body>
</html>
