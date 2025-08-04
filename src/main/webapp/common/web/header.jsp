<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaweb.security.utils.SecurityUtils" %>
<nav class="navbar-expand-lg navbar-dark fixed-top">
		<div class="row navbar">
			<div class="col-12 col-md-3">
				<div class="logo">
					<a href="">
						<img src="https://funix.edu.vn/wp-content/uploads/2023/06/internet-of-thing-iot-4.jpg"
							 alt=""
							 width="100" height="50">
					</a>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<security:authorize access="isAnonymous()">
						<li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a></li>
						<li class="nav-item"><a class="nav-link" href="<c:url value='/register'/>">Đăng ký</a></li>
					</security:authorize>
						<li class="nav-item"><a class="nav-link" href="/admin/home">ADMIN</a></li>
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