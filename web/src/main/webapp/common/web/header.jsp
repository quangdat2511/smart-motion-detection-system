<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaweb.security.utils.SecurityUtils" %>
<nav class="navbar-expand-lg navbar-dark fixed-top">
		<div class="row navbar">
			<div class="col-12 col-md-3">
				<div class="logo">
					<a href="">
						<img src="https://karofi.karofi.com/karofi-com/2022/06/internet-van-vat-iot.jpg" alt="" class="img-fluid" style="max-width: 100px;">
					</a>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<security:authorize access="isAnonymous()">
						<li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a></li>
						<li class="nav-item"><a class="nav-link" href="<c:url value='/register'/>">Đăng ký</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="nav-item"><a class="nav-link" href="/admin/home">Trang quản trị</a></li>
					</security:authorize>

					<security:authorize access="isAuthenticated()">
						<li class="nav-item">
							<a class="nav-link" href="#">Xin chào <%=
								SecurityUtils.getPrincipal().getUsername()
							%></a>
						</li>
						<li class="nav-item"><a class="nav-link" href="<c:url value='/logout'/>">Thoát</a></li>
					</security:authorize>

				</ul>
			</div>
		</div>
</nav>