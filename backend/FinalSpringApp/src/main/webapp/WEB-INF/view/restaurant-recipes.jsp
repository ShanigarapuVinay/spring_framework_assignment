<%@ include file="common/headers.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container mt-5">
    <h1 class="mb-4">Recipes at ${restaurant.name}</h1>
    <div class="mb-3">
        <h4>Location: <span class="text-muted">${restaurant.location}</span></h4>
        <h4>Rating: <span class="text-muted">${restaurant.rating}</span></h4>
    </div>

    <h2 class="mb-4">Recipes:</h2>

    <!-- Check if restaurant object or recipes list is null -->
    <c:choose>
        <c:when test="${restaurant == null || restaurant.recipes == null || restaurant.recipes.isEmpty()}">
            <div class="alert alert-warning">
                No recipes are available for this restaurant.
            </div>
        </c:when>
        <c:otherwise>
            <ul class="list-group mb-4">
                <c:forEach var="recipe" items="${restaurant.recipes}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span>${recipe.name} - ${recipe.cuisine}</span>
                        <span class="badge bg-success">${recipe.rating}</span>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/restaurants/${restaurant.id}/manage-recipes" class="btn btn-primary">Manage Recipes</a>
</div>
<%@ include file="common/footers.jspf" %>
