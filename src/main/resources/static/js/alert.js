var Toast;

// sweet alert pesan sukses
function success(message) {
    toast();

    Toast.fire({
        icon: 'success',
        title: message
    });
}

// sweet alert pesan error
function error(message) {
    toast();
    Toast.fire({
        icon: 'error',
        title: message
    });
}

// sweet alert confirm
function question(question, message, confirmText, action) {
    Swal.fire({
        title: question,
        showCancelButton: true,
        confirmButtonText: `${confirmText}`,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            success(message);
            action();
        }
    });
}

function toast() {
    Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })
}