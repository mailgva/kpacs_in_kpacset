<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en-US">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<jsp:include page="fragments/gridSection.jsp"/>
<jsp:include page="fragments/scriptConst.jsp"/>

<script>
	function populateGrid(appData) {
		console.log(appData);
		const grid = new dhx.Grid("grid", {
			columns: [
				{width: 40, id: "id", header: [{text: "ID"}]},
				{
					width: 150,
					id: "title",
					header: [{text: "Title"}]},
				{
					width: 560,
					id: "description",
					header: [{text: "Description"}]}
			],
			data: appData.kpacs,
			selection: "row",
		});
		setHeaderName(appData.title);
	}

	function setHeaderName(title) {
		let element = document.getElementById("header-name");
		let text = element.textContent || element.innerText;
		element.innerHTML = text + title;
	}
</script>
<%--<jsp:include page="fragments/scriptModalForm.jsp"/>--%>
</body>
</html>