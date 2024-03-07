<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="container" align="center">
				<h2>
					<a href="showAllBooks">Display All Books</a>
				</h2>
				<sec:authorize access="hasRole('ADMIN')">
				<h2>
					<a href="showAllUsers">Display All Users</a>
				</h2>
				</sec:authorize>
			</div>
	</div>
	<c:import url="myfooter.jsp" />
</body>
</html>