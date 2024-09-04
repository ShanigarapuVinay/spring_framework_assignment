<%@ include file="common/headers.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Enter Restaurant Details</h1>
	<form:form method="post" modelAttribute="restaurant">
		<fieldset class="mb-3">
			<form:label path="name">Name</form:label>
			<form:input type="text" path="name" />
			<form:errors path="name" cssClass="text-warning" />
		</fieldset>
		<fieldset class="mb-3">
			<form:label path="location">Location</form:label>
			<form:input type="text" path="location" />
			<form:errors path="location" cssClass="text-warning" />
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
