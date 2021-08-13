var dataLogin = {};

function loginProcess() {
    dataLogin.username = $('#username').val();
    dataLogin.password = $('#password').val();

    console.log(dataLogin.username);

    $.ajax({
        url: 'login',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(dataLogin),
        contentType: 'application/json',
        success: (data) => {
            console.log(data);
            location.href="/dashboard"
        },
        error: function (xhr, err, e) {
            alert("Username or Password Invalid");
        }
    })
}