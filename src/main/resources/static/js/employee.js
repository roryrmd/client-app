var employee = {};

$(document).ready(function () {
    getAll();
    submit();
});

function getAll() {
    $.ajax({
        url: '/employees',
        type: 'GET',
        dataType: 'json',
        success: (res) => {
            let row = null;
            res.forEach((data) => {
                row += `<tr>
                            <td>${data.id}</td>
                            <td>${data.firstName}</td>
                            <td>${data.lastName}</td>
                            <td>${data.email}</td>
                            <td>${data.address}</td>
                            <td>${data.department}</td>
                            <td>${data.user}</td>
                            <td>${data.projects}</td>
                            <td>
                            <div class="action-button">
                                <button 
                                    class="btn btn-sm btn-primary" 
                                    data-bs-toggle="modal" 
                                    data-bs-target="#departmentModal"
                                    onclick="detail(${data.id})"
                                 >
                                        <i class="fa fa-sm fa-eye"></i>
                                    </button>
                                    <button class="btn btn-sm btn-warning text-white" 
                                            data-bs-toggle="modal" 
                                            data-bs-target="#departmentModal"
                                            onclick="edit(${data.id})"                                                    
                                            >
                                        <i class="fa fa-sm fa-edit"></i>
                                    </button>
                                    <button class="btn btn-sm btn-danger"
                                            onclick="deleteById(${data.id})"  
                                            >
                                        <i class="fa fa-sm fa-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>`;
            });

            $('tbody').html(row);
            dataTable();
        }
    });
}

function detail(id) {
    getById(id);

    disabledForm(true);
}

function setValue() {
    department.name = $('#deptname').val();
}

function create() {
    setForm({});
    disabledForm(false);

    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        $.ajax({
            type: "POST",
            url: `/departments/add`,
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: 'json',
            success: (data) => {
                $(".modal").modal('hide');
                success('department created');
                location.reload();
            }
        });
    });
}

function edit(id) {
    getById(id);
    disabledForm(false);

    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        $.ajax({
            type: "PUT",
            url: `/departments/${id}`,
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: 'json',
            success: (data) => {
                $(".modal").modal('hide');
                success('department created');
                location.reload();
            }
        });
    });
}

function deleteById(id) {
    question("Do you want to delete this department?", "department deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `/departments/${id}`,
            dataType: 'json',
            success: (data) => {
                success('department deleted');
                location.reload();
            }
        });
    });
}

function getById(id) {
    $.ajax({
        url: `/departments/${id}`,
        dataType: 'json',
        success: (data) => {
            department.name = data.name;
            setForm();
        }
    });
}

function setForm() {
    $('#deptname').val(department.name);
}

function disabledForm(isDisable) {
    $('#deptname').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}