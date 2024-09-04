<%@ include file="common/headers.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Enter Your Review</h1>
	<form:form method="post" modelAttribute="review">
		<fieldset class="mb-3">
			<form:label path="name">Name</form:label>
			<form:input type="text" path="name" />
			<form:errors path="name" cssClass="text-warning" />
		</fieldset>
		<fieldset class="mb-3">
			<form:label path="comment">Comment</form:label>
			<form:input type="text" path="comment" />
			<form:errors path="comment" cssClass="text-warning" />
		</fieldset>
		<fieldset class="mb-3">
            <form:label path="rating">Rating</form:label>
            <form:input type="text" path="rating" />
            <form:errors path="rating" cssClass="text-warning" />
        </fieldset>

		<form:input type="hidden" path="id" />
		<input type="submit" class="btn btn-success" value="Submit">
	</form:form>

</div>
<%@ include file="common/footers.jspf"%>
