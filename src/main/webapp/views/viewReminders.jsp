<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Remly</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<c:if test="${not empty successMessage}">
    <div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
            ${successMessage}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        let successAlert = document.getElementById("successAlert");

        // Remove the alert div if it's empty (in case of refresh)
        if (successAlert && successAlert.innerText.trim() === "") {
            successAlert.remove();
        }

        // Hide success message after 5 seconds
        setTimeout(function () {
            if (successAlert) {
                successAlert.style.display = "none";
            }
        }, 5000);
    });
</script>



<H1>Success</H1>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
