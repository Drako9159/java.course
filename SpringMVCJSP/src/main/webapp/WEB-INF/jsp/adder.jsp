<%@ include file="common/header.jsp" %>

<%@ include file="common/nav.jsp" %>

<div class="container">
    <div class="text-left" style="margin: 30px">
        <h3>Add Employee</h3>
        <form action="/employees/add" modelAttribute="employeeForm" method="post">
            <div class="mb-3">
                <label for="nameEmployee" class="form-label">Name</label>
                <input type="text" class="form-control" id="nameEmployee" name="nameEmployee" aria-describedby="emailHelp" required="true">
            </div>
            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department">
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label">Salary</label>
                <input type="number" step="any" class="form-control" id="salary" name="salary">
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-warning btn-sm me-3">Add</button>
                <a href="/employees" class="btn btn-danger btn-sm">Return</a>
            </div>


        </form>
    </div>
</div>
<%@ include file="common/footer.jsp" %>
