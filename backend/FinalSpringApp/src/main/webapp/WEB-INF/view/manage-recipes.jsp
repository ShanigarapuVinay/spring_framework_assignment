<%@ include file="common/headers.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container mt-5">
    <h1 class="mb-4">Manage Recipes for ${restaurant.name}</h1>

    <form action="${pageContext.request.contextPath}/restaurants/update-recipes" method="post">
        <input type="hidden" name="restaurantId" value="${restaurant.id}" />

        <h2 class="mb-4">Select Recipes:</h2>
        <div class="list-group mb-4">
            <c:forEach var="recipe" items="${allRecipes}">
                <div class="form-check mb-2">
                    <input class="form-check-input" type="checkbox" name="recipeIds" value="${recipe.id}"
                        <c:forEach var="selectedRecipe" items="${restaurant.recipes}">
                            <c:if test="${selectedRecipe.id == recipe.id}">
                                checked
                            </c:if>
                        </c:forEach> />
                    <label class="form-check-label">
                        ${recipe.name} - ${recipe.cuisine}
                        <span class="badge bg-success ms-2">${recipe.rating}</span>
                    </label>
                </div>
            </c:forEach>
        </div>

        <button type="submit" class="btn btn-primary">Update Recipes</button>
    </form>

    <a href="${pageContext.request.contextPath}/restaurants/${restaurant.id}/recipes" class="btn btn-secondary mt-3">Back to Recipes</a>
</div>
<%@ include file="common/footers.jspf" %>

