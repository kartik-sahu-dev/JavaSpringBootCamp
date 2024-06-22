
<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<div class="container">
	<h1>Your todos :</h1>

	<table class="table">
		<thead>
			<th>Desciption</th>
			<th>Target Date</th>
			<th>IsDone</th>
			<th></th>
			<th></th>
		</thead>
		<tbody>

			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.description}</td>
					<td>${todo.targetDate}</td>
					<td>${todo.done}</td>
					<td><a href="delete-todo?id=${todo.id}"
						class="btn btn-warning">Delete </td>
					<td><a href="update-todo?id=${todo.id}"
						class="btn btn-success">Update </td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add Todo</a>
</div>

<%@ include file="common/footer.jspf"%>
