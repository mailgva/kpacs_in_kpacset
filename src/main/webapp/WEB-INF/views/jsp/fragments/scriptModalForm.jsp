<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
  const modal = document.getElementById("form-modal");
  const span = document.getElementsByClassName("close")[0];
  const gridFooter = new dhx.Form("grid-footer", {
    cols: [
      {
        id: "button_add",
        type: "button",
        name: "button_add",
        text: "Add",
        size: "medium",
        view: "flat",
        color: "primary"
      }
    ]
  });
  gridFooter.getItem("button_add").events.on("click", function(events) {
    modal.style.display = "block";
  });

  span.onclick = function() {
    modal.style.display = "none";
  }
  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }

  const modalFooter = new dhx.Form("modal-footer", {
    cols: [
      {
        id: "button_create",
        type: "button",
        name: "button_create",
        text: "Create",
        size: "medium",
        view: "flat",
        color: "primary"
      }
    ]
  });
  modalFooter.getItem("button_create").events.on("click", function(events) {
    modal.style.display = "none";
    createAppItem(DATA_URL);
  });
</script>