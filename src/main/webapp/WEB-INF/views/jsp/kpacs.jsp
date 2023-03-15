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
						width: 150,
						id: "title",
						header: [{text: "Title"}, {content: "inputFilter"}]},
					{
						width: 380,
						id: "description",
						header: [{text: "Description"}, {content: "inputFilter"}]},
					{
						width: 120,
						id: "creationDate",
						header: [{text: "Creation Date"}, {content: "selectFilter"}]
					},
					{width: 60, id: "deleteIcon", header: [{text: "Delete"}], sortable: false, htmlEnable: true}
				],
				data: appData,
				selection: "row",
			});
		}
	</script>

	<div id="form-modal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">&times;</span>
				<h2>New K-PAC</h2>
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
					<div aria-label="tab-content-value" data-cell-id="description"
						 class="dhx_layout-cell dhx_form-element"
						 style="flex: 0 0 auto;">
						<div class="dhx_form-group  dhx_form-group--inline">
							<label for="description" class="dhx_label " style="width: 120px; max-width: 100%;">Description</label>
							<div class="dhx_input__wrapper">
								<div class="dhx_input__container">
									<input type="text" data-dhx-id="description" id="description" placeholder=""
										   name="description" class="dhx_input "
										   autocomplete="off" aria-label="title"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer" id="modal-footer">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="fragments/scriptModalForm.jsp"/>
</body>
</html>