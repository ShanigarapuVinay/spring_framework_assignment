<%@ include file="common/headers.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h1>Recipes</h1>
    <table class="table">
        <thead>
            <tr>
                <th>Name</th>
                <th>Cuisine</th>
                <th>Rating</th>
                <th>Reviews</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${recipes}" var="recipe">
                <tr>
                    <td>${recipe.name}</td>
                    <td>${recipe.cuisine}</td>
                    <td>${recipe.rating}</td>
                    <td><a href="${recipe.id}/reviews"
                           class="btn btn-info">View</a></td>
                    <td><a href="update-recipe/${recipe.id}" class="btn btn-success">Update</a></td>
                    <td><a href="delete-recipe/${recipe.id}"
                           onclick="if (!(confirm('Are you sure!! This process is inevitable'))) return false"
                           class="btn btn-danger">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="add-recipe" class="btn btn-success">Add Recipe</a>
</div>
<%@ include file="common/footers.jspf" %>
