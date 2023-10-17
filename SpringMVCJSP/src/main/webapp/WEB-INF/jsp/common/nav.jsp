<c:set var="urlHome">
    <c:url value="${application.conextPath}/" />
</c:set>

<c:set var="urlAdd">
    <c:url value="${application.conextPath}/add" />
</c:set>



<div class="container">
    <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${urlHome}">Employees System</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active" aria-current="page" href="${urlHome}">Home</a>
                    <a class="nav-link" href="${urlAdd}">Add Employee</a>

                </div>
            </div>
        </div>
    </nav>
</div>