<%@ include file="common/headers.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Restaurants</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Location</th>
				<th>Rating</th>
				<th>Recipes</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${restaurants}" var="restaurant">
				<tr>
					<td>${restaurant.name }</td>
					<td>${restaurant.location }</td>
					<td>${restaurant.rating }</td>
					<td><a href="${restaurant.id}/recipes"
                    	class="btn btn-info">View</a></td>
					<td><a href="update-restaurant/${restaurant.id}"
						class="btn btn-success">Update</a></td>
					<td><a href="delete-restaurant/${restaurant.id}"
					       onclick="if (!(confirm('Are you sure!! This process is inevitable'))) return false"
						class="btn btn-warning">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-restaurant" class="btn btn-success">Add Restaurant</a>
</div>
<%@ include file="common/footers.jspf"%>
