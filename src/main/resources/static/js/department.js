var department = {};
var deptId;

    console.log(getToken());

$(document).ready(function () {
    var table= $('#table').DataTable({
        ajax : {
            url : 'http://localhost:8081/department',
            dataSrc : '',
            beforeSend: addRequestHeader()
        },
        "columns": [
            {
                "name": "Id",
                "data": "id"
            },
            {
                "name": "Department Name",
                "data": "name"
            },
            {
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
              <div class="d-flex justify-content-center">
                  <a class="btn btn-info" href="#" onclick="detail(${data})"><i class="fa fa-eye" aria-hidden="true"></i></a>
                  <a class="btn btn-info" href="#" onclick="edit(${data})"><i class="far fa-edit"></i></a>
                  <a class="btn btn-info" href="#" onclick="deleteById(${data})"><i class="fa fa-trash" aria-hidden="true"></i></a>
              </div>
            `;
                }
            }
        ]
    });
    // setInterval(function () {
    //     data.ajax.reload(null, false);
    // }, 2000);
    submit();
});

// var data = $('#table').DataTable({
//     ajax: {
//         url: 'http://localhost:8081/department',
//         dataSrc: ''
//     },
//     "columns": [
//         {"targets": 0,
//             "data": "id",
//             "render": function (data, type, row, meta) {
//                 return `<div class="row center"><div class="col">${data}</div></div>`;
//             }
//         },
//         {"targets": 1,
//             "data": "name",
//             "render": function (data, type, row, meta) {
//                 return `<div class="row center"><div class="col">${data}</div></div>`;
//             }
//         },
//         {"targets": 2,
//             "data": "id",
//             "render": function (data, type, row, meta) {
//                 return `<div class="action-button">
//                         <button
//                             class="btn btn-sm btn-primary"
//                             data-bs-toggle="modal"
//                             data-bs-target="#departmentModal"
//                             onclick="detail(${data})"
//                         >
//                             <i class="fa fa-sm fa-eye"></i>
//                         </button>
//                         <button class="btn btn-sm btn-warning text-white"
//                             data-bs-toggle="modal"
//                             data-bs-target="#departmentModal"
//                             onclick="edit(${data})"
//                         >
//                             <i class="fa fa-sm fa-edit"></i>
//                         </button>
//                         <button class="btn btn-sm btn-danger"
//                             onclick="deleteById(${data})"
//                         >
//                             <i class="fa fa-sm fa-trash"></i>
//                         </button>
//                     </div>`;
//             }
//         }
//     ]
// });

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if($('.input-data').val()){
            if(deptId){
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/department/${deptId}` ,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        console.log(data);
                        success('department update');
                        $('#table').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            }else{
                var _this = this;
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/department/`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        success('department created');
                        $('#table').DataTable().ajax.reload(null, false);
                    }
                });
            }
            $('.modal').modal('hide');
            // setInterval('refreshPage()', 1000);
        }else{
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    })
}

// function getAll() {
//     $.ajax({
//         url: '/department/get-all',
//         type: 'GET',
//         dataType: 'json',
//         success: (res) => {
//             let row = null;
//             res.forEach((data) => {
//                 row += `<tr>
//                             <td>${data.id}</td>
//                             <td>${data.name}</td>
//                             <td>
//                             <div class="action-button">
//                                 <button
//                                     class="btn btn-sm btn-primary"
//                                     data-bs-toggle="modal"
//                                     data-bs-target="#departmentModal"
//                                     onclick="detail(${data.id})"
//                                  >
//                                         <i class="fa fa-sm fa-eye"></i>
//                                     </button>
//                                     <button class="btn btn-sm btn-warning text-white"
//                                             data-bs-toggle="modal"
//                                             data-bs-target="#departmentModal"
//                                             onclick="edit(${data.id})"
//                                             >
//                                         <i class="fa fa-sm fa-edit"></i>
//                                     </button>
//                                     <button class="btn btn-sm btn-danger"
//                                             onclick="deleteById(${data.id})"
//                                             >
//                                         <i class="fa fa-sm fa-trash"></i>
//                                     </button>
//                                 </div>
//                             </td>
//                         </tr>`;
//             });
//
//             $('tbody').html(row);
//             dataTable();
//         }
//     });
// }

function detail(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(true);
}

function setValue() {
    department.name = $('#deptname').val();
}

function edit(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(false);
}

function create() {
    department={};
    deptId=null;
    setForm({});
    disabledForm(false);
}

// function create() {
//     setForm({});
//     disabledForm(false);
//
//     $('form').submit((e) => {
//         e.preventDefault();
//         setValue();
//         $.ajax({
//             type: "POST",
//             url: `/department/save`,
//             contentType: 'application/json',
//             data: JSON.stringify(department),
//             dataType: 'json',
//             success: (data) => {
//                 $(".modal").modal('hide');
//                 success('department created');
//                 location.reload();
//             }
//         });
//     });
// }

// function edit(id) {
//     getById(id);
//     disabledForm(false);
//
//     $('form').submit((e) => {
//         e.preventDefault();
//         setValue();
//         $.ajax({
//             type: "PUT",
//             url: `/department/${id}`,
//             contentType: 'application/json',
//             data: JSON.stringify(department),
//             dataType: 'json',
//             success: (data) => {
//                 $(".modal").modal('hide');
//                 success('department created');
//                 location.reload();
//             }
//         });
//     });
// }

function deleteById(id) {
    question("Do you want to delete this department?", "department deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/department/${id}`,
            dataType: 'json',
            success: (data) => {
                success('department deleted');
                $('#table').DataTable().ajax.reload(null, false);
            },
            error: (data) => {
                error("department failed to delete");
            }
        });
    });
}

function getById(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8081/department/${id}`,
        dataType: 'json',
        beforeSend: addRequestHeader(),
        success: (data) => {
            deptId = id;
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

function logout() {
    $.ajax({
        type: "POST",
        url: '/department/logout',
        success: (data) => {
            success("you've been logout!");
            window.location.href = 'http://localhost:8080/logout';
        }
    })
}
