<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
			<div class="container">
				<table class="table table-striped table-bordered"
					style="font-size: 20px; font-weight: bold;">
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>	
						<th>Phone</th>
						<th>Active</th>
						<th>Roles</th>
						<!--<sec:authorize access="hasRole('ADMIN')">
						<th colspan="2" align="center"><form:form
								action="addEditUserForm">
								<input type="hidden" name="username" value="0" />
								<input type="submit" value=" Add New User "
									class="btn btn-success btn-lg" />
							</form:form></th>
							</sec:authorize>-->
					</tr>
					<c:forEach var="myuser" items="${MyUsersList}">
						<tr>
							<!--<td><a href="viewUser?username=${myuser.username }"> ${myuser.username }
							</a></td> -->
							<td>${myuser.firstname }</td>
							<td>${myuser.lastname }</td>
							<td>${myuser.email }</td>
							<td>${myuser.phone }</td>
							<td><c:choose><c:when test="${myuser.active=='1'}">Yes</c:when>    
    									<c:otherwise>No</c:otherwise></c:choose></td>
    						<td>${myuser.userRoles }</td>
							<!--<sec:authorize access="hasRole('ADMIN') or hasRole('ROLE_STOREKEEPER')">
							<td><form:form action="addEditBookForm">
									<input type="hidden" name="bookId" value="${myuser.username }" />
									<input type="submit" value=" Edit "
										class="btn btn-primary btn-lg" />
								</form:form></td>
								</sec:authorize>
								<sec:authorize access="hasRole('ADMIN')">
							<td><form:form action="deleteBook">
									<input type="hidden" name="bookId" value="${myuser.username }" />
									<input type="submit" value=" Delete "
										class="btn btn-danger btn-lg" />
								</form:form></td>
								</sec:authorize>-->
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
</body>
</html>