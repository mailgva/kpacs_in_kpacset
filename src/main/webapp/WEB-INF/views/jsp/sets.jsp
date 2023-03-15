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
			const grid = new dhx.Grid("grid", {
				columns: [
					{width: 40, id: "id", header: [{text: "ID"}]},
					{
						width: 650,
						id: "title",
						header: [{text: "Title"}, {content: "inputFilter"}]},
					{width: 60, id: "deleteIcon", header: [{text: "Delete"}],
						sortable: false, htmlEnable: true}
				],
				data: appData,
				selection: "row",
			});
			grid.events.on("cellDblClick", function (row, column, e) {
				window.open("set/"+row.id, '_blank').focus();
			});
		}

		function populateModalGrid(appData) {
			const modalGrid = document.getElementById("modal-grid");
			modalGrid.replaceChildren();

			new dhx.Grid("modal-grid", {
				columns: [
					{width: 40, id: "id", header: [{text: "ID"}]},
					{width: 170, id: "title", header: [{text: "Title"}]},
					{width: 400, id: "description",	header: [{text: "Description"}]},
					{width: 80, id: "selected", header: [{text: "Select"}],
						htmlEnable: true}
				],
				data: appData
			});
		}
	</script>

	<div id="form-modal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">&times;</span>
				<h2>New K-PAC Set</h2>
			</div>
			<form id="form">
				<div class="modal-body" style="margin-top: 30px">
					<div aria-label="tab-content-value" data-cell-id="title" class="dhx_layout-cell dhx_form-element"
						 style="flex: 0 0 auto;">
						<div class="dhx_form-group  dhx_form-group--inline">
							<label for="title" class="dhx_label " style="width: 120px; max-width: 100%;">Title</label>
							<div class="dhx_input__wrapper">
								<div class="dhx_input__container">
									<input type="text" data-dhx-id="title" id="title" placeholder="" name="title"
										   class="dhx_input "
										   autocomplete="off" aria-label="title"></div>
							</div>
						</div>
					</div>
				</div>

				<div style="height: 300px; width: 96%; margin: auto" id="modal-grid"></div>
				<div class="modal-footer" id="modal-footer"></div>
			</form>
		</div>
	</div>

	<script>
		function loadKPacs() {
			const xhr = new XMLHttpRequest();
			xhr.open("GET", "data/kpacs/");
			xhr.send();
			xhr.responseType = "json";
			xhr.onload = () => {
				if (xhr.readyState == 4 && xhr.status == 200) {
					let appData = xhr.response;
					appData.forEach(
							element => element['selected'] =
									'<input type="checkbox" id="' + CHK_PREFIX + element.id + '"/>');
					populateModalGrid(appData);
				} else {
					console.log(`Error: ${xhr.status}`);
				}
			}
		}
		document.addEventListener("DOMContentLoaded", function () {loadKPacs()});
	</script>

	<jsp:include page="fragments/scriptModalForm.jsp"/>
</body>
</html>