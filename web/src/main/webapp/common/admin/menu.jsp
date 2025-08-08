<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
    <script type="text/javascript">
        try{ace.settings.loadState('sidebar')}catch(e){}
    </script>
<ul class="nav nav-list">
    <!-- Nút: Quản lí thao tác -->
    <li class="">
        <a href="/admin/dashboard-list">
            <i class="bi bi-cpu-fill"></i>
            <span class="menu-text">Giám sát & Điều khiển</span>
        </a>
    </li>
    <!-- Nút: Quản lí các chuyển động -->
    <li class="">
        <a href="${pageContext.request.contextPath}/admin/motion-list?deviceId=1">
            <i class="bi bi-person-walking"></i>
            <span class="menu-text">Danh sách các chuyển động</span>
        </a>
    </li>

    <!-- Nút: Quản lý tài khoản (chỉ hiện khi có quyền MANAGER) -->
    <security:authorize access="hasRole('MANAGER')">
        <li class="">
            <a href="/admin/user-list">
                <i class="bi bi-people-fill"></i>
                <span class="menu-text">Danh sách các tài khoản</span>
            </a>
        </li>
    </security:authorize>
</ul>


    <div class="sidebar-toggle sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>