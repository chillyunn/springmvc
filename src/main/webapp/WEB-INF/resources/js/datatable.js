$(document).ready(function () {
    const table = $('#datatable').DataTable({
        pageLength: 3,
        bLengthChange: false,
        ajax: {
            url: '/api/members',
            type: 'POST',
        },
        columns: [
            {data: 'id'},
            {data: 'memberId'},
            {data: 'name'},
            {data: 'password'},
            {data: 'position'},
            {data: 'region'},
            {data: 'department'},
        ]
    });
    $('#datatable tbody').on('click', 'tr', function () {
        const row = $('#datatable').DataTable().row($(this)).data();
        $('#memberUpdateModal').modal('show', function () {
            modal.find('.modal-body #memberId').text(row.memberId);
        });

    });
    $(function () {
        $("#insertRow").on("click", function () {

        })
    })


    $(function () {
        $("#c_confirm").on('click', function () {

            const member = {
                "memberId": $("#c_memberId").val(),
                "name": $("#c_name").val(),
                "password": $("#c_password").val(),
                "department": $("#c_department").val(),
                "position": $("#c_position").val(),
                "region": $("#c_region").val()
            };
            console.log(member);
            $.ajax({
                type: "POST",
                contentType: 'application/json',
                url: "/api/member",
                data: JSON.stringify({member}),
                dataType:"json",
                error: function (e){
                    console.log(e);
                }
            });
        })
    })
});