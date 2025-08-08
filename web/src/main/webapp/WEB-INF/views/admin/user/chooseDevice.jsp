<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Chọn thiết bị</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
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
                <li class="active">Chọn thiết bị</li>
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
                    <div id="selectDeviceForm">
                        <form:form id="formSelectDevice" class="form-horizontal" name="formSelectDevice">
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    Nhập ID thiết bị
                                </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="deviceId" name="deviceId"
                                           placeholder="ID thiết bị...">
                                </div>
                            </div>
                            <!-- Btn -->
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="button" class="btn btn-primary btn-bold" value="Chọn thiết bị" id="btnSelectDevice"/>
                                    <button type="button" class="btn btn-danger" onclick="window.history.back();">
                                        Hủy thao tác
                                    </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('#btnSelectDevice').click(function () {
                $('#formSelectDevice').submit();
            });
        });

        $(function() {
            $("form[name='formSelectDevice']").validate({
                rules: {
                    deviceId: "required"
                },
                messages: {
                    deviceId: "Vui lòng nhập ID thiết bị"
                },
                submitHandler: function(form) {
                    var enteredId = $('#deviceId').val();
                    selectDevice(enteredId);
                }
            });
        });

        function selectDevice(deviceId) {
            $.ajax({
                url: '/api/device/choose',
                type: 'PUT',
                data: { deviceId: deviceId },
                success: function (response) {
                    alert(response);
                },
                error: function (xhr) {
                    alert('Lỗi khi chọn thiết bị: ' + xhr.responseText);
                }
            });
        }
    </script>
</div>
</body>
</html>
