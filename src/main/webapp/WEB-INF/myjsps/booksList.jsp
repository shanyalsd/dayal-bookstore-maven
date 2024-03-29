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
						<th>Book ID</th>
						<th>Book Name</th>
						<th>Author</th>	
						<th>Price</th>
						<th>Category</th>
						<th>Publications</th>
						<sec:authorize access="hasRole('ADMIN') or hasRole('ROLE_STOREKEEPER')">
						<th colspan="2" align="center"><form:form
								action="addEditBookForm">
								<input type="hidden" name="bookId" value="0" />
								<input type="submit" value=" Add New Book "
									class="btn btn-success btn-lg" />
							</form:form></th>
							</sec:authorize>
					</tr>
					<c:forEach var="mybook" items="${MyBooksList}">
						<tr>
							<td><a href="viewBook?bookId=${mybook.bid }"> ${mybook.bid }
							</a></td>
							<td>${mybook.bname }</td>
							<td>${mybook.author }</td>
							<td>${mybook.price }</td>
							<td>${mybook.category }</td>
							<td>${mybook.pub }</td>
							<sec:authorize access="hasRole('ADMIN') or hasRole('ROLE_STOREKEEPER')">
							<td><form:form action="addEditBookForm">
									<input type="hidden" name="bookId" value="${mybook.bid }" />
									<input type="submit" value=" Edit "
										class="btn btn-primary btn-lg" />
								</form:form></td>
								</sec:authorize>
								<sec:authorize access="hasRole('ADMIN')">
							<td><form:form action="deleteBook">
									<input type="hidden" name="bookId" value="${mybook.bid }" />
									<input type="submit" value=" Delete "
										class="btn btn-danger btn-lg" />
								</form:form></td>
								</sec:authorize>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
</body>
</html>