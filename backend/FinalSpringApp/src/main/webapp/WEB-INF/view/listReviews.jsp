<%@ include file="common/headers.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container mt-5">
    <h1 class="mb-4">Reviews of ${recipe.name}</h1>
    <div class="mb-3">
        <h4>Cuisine: <span class="text-muted">${recipe.cuisine}</span></h4>
        <h4>Rating: <span class="text-muted">${recipe.rating}</span></h4>
    </div>

    <h2 class="mb-4">Reviews:</h2>

    <c:choose>
        <c:when test="${recipe == null || recipe.reviews == null || recipe.reviews.isEmpty()}">
            <div class="alert alert-warning">
                No reviews are available for this recipe.
            </div>
        </c:when>
        <c:otherwise>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
                <c:forEach var="review" items="${recipe.reviews}">
                    <div class="col">
                        <div class="card p-3" style="border-radius: 8px; background-color: #f9f9f9; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                            <div class="card-body">
                                <div class="d-flex justify-content-between mb-1">
                                    <span class="fw-bold">${review.name}</span>
                                    <span class="badge bg-success">${review.rating}</span>
                                </div>
                                <div class="mb-3">
                                    <span class="text-muted">${review.comment}</span>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <a href="../${recipe.id}/update-review/${review.id}"
                                        class="btn btn-sm btn-warning">Update</a>
                                    <a href="../${recipe.id}/delete-review/${review.id}"
                                        class="btn btn-sm btn-danger"
                                        onclick="if (!(confirm('Are you sure!! This process is inevitable'))) return false">Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>

    <a href="../${recipe.id}/add-review" class="btn btn-primary mt-5">Add Review</a>
</div>

<%@ include file="common/footers.jspf" %>
