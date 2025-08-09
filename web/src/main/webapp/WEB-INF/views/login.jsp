<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container">
		<!-- <h1 class="form-heading">login Form</h1> -->
		<div class="login-form">
			<div class="main-div">
				<c:if test="${param.incorrectAccount != null}">
					<div class="alert alert-danger">	
							Username or password incorrect
					</div>
				</c:if>
				<c:if test="${param.accessDenied != null}">
					<div class="alert alert-danger">	
							You Not authorize
					</div>
				</c:if>
				<c:if test="${param.sessionTimeout != null}">
					<div class="alert alert-danger">
							Session Timeout
					</div>
				</c:if>
				<div class="container-fluid" >
					<section class="gradient-custom">
						<div class="page-wrapper">
							<div class="row d-flex justify-content-center align-items-center">
								<div class="col-12 col-md-8 col-lg-6 col-xl-5">
									<div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
										<div class="card-body p-5">
											<div class="mb-md-5 mt-md-4 pb-5 text-center">
												<h2 class="fw-bold mb-2 text-uppercase">Đăng nhập</h2>
												<p class="text-white-50 mb-5">Hãy nhập tên đăng nhập và mật khẩu!</p>
												<form action="j_spring_security_check" id="formLogin" method="post">
												<div class="form-outline form-white mb-4">
													<label class="form-label" for="userName">Tên đăng nhập</label>
													<input type="text" class="form-control" id="userName" name="j_username" placeholder="Tên đăng nhập">
												</div>

												<div class="form-outline form-white mb-4">
													<label class="form-label" for="password">Mật khẩu</label>
													<input type="password" class="form-control" id="password" name="j_password" placeholder="Mật khẩu">
												</div>


												<button type="submit" class="btn btn-primary" >Đăng nhập</button>
												</form>
												<div class="d-flex justify-content-center text-center mt-2 pt-1">
													<a href="#!" class="login-extension text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
													<a href="#!" class="login-extension text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
													<a href="#!" class="login-extension text-white"><i class="fab fa-google fa-lg"></i></a>
												</div>
											</div>
											<div class="text-center">
												<p class="mb-0 tex-center account">
													Chưa có tài khoản? <a href="register" class="text-white-50 fw-bold">Đăng ký</a>
												</p>
												<p class="mt-3">
													<a href="${pageContext.request.contextPath}/trang-chu" class="btn btn-light btn-sm">Quay về trang chủ</a>
												</p>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
</body>
</html>