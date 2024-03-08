<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Dayal's Bookstore</title>
<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="card">
		<c:import url="myheader.jsp" />
	</div>
	<br />
	<br />
	<div class="card-body">
			<c:import url="mymenu.jsp" />
			<br />
			<br />
			<div align="center" class="container">
				<form:form action="updateUserRoles" method="post"
					modelAttribute="myroles">
					<table class="table" style="font-size: 20px; font-weight: bold;">
						<tr>
							<td align="center" colspan="3">
									<h2>Update Roles</h2>
								</td>
						</tr>
						<tr>
							<td>User</td>
							<td><form:input path="username"
									class="form-control form-control-lg" disabled="true" /></td>
							<td><font color=red size=4><form:errors path="username" /></font></td>
						</tr>
						<tr>
							<td>Select Multiple Roles</td>
							<td>
								<form:select multiple="true" path="roles" class="form-select" aria-label="Multiple select example">
    									<form:options items="${rolesList}" ></form:options>
								</form:select>
							</td>
							<td><font color=red size=4><form:errors path="roles" /></font></td>
						</tr>
						<tr>
							<td align="center" colspan="3">
									<input type="submit" value="Update Roles"
										class="btn btn-primary btn-lg" />
									<form:hidden path="username" />
									<a href="showAllUsers" class="btn btn-primary btn-lg">Cancel</a>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	<c:import url="myfooter.jsp" />
</body>
</html>