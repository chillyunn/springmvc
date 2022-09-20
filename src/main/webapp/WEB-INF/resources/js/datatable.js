$(document).ready(function () {
    //사용자 조회 로직
    const table = $('#datatable').DataTable({
        pageLength: 5,
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
            {data: 'department'},
            {data: 'position'},
            {data: 'region'},
        ]
    });
    $('#datatable tbody').on('click', 'tr', function () {
        const row = $('#datatable').DataTable().row($(this)).data();

        //사용자 수정 모달에 존재하는 input 태그 값 입력
        $('#id').text(row.id);
        $('#memberId').attr('value',row.memberId);
        $('#name').attr('value',row.name);
        $('#password').attr('value',row.password);
        $('#department').attr('value',row.department);
        $('#position').attr('value',row.position);
        $('#region').attr('value',row.region);
        $('#memberUpdateModal').modal('show');
    });
    $('#memberUpdateModal').keydown(function (key){
        if(key.keyCode == 13){
            $("#update").click();
        }
    })
    //사용자 수정 로직
    $("#update").on('click',function (){
        const url = "/api/member/"+ $("#id").text();
        const member = JSON.stringify({
            memberId: $("#memberId").val(),
            name: $("#name").val(),
            password: $("#password").val(),
            department: $("#department").val(),
            position: $("#position").val(),
            region: $("#region").val()
        });
        $.ajax({
            type: "PUT",
            contentType: 'application/json',
            url: url,
            data: member,
            error: function (e){
                console.log(e);
            },
            success: function (){
                table.ajax.reload(null,false);
            }
        });
    })
    //사용자 삭제 로직
    $("#delete").on('click',function (){
        const url = "/api/member/" +$("#id").text();
        $.ajax({
            type: "DELETE",
            url:url,
            success: function (){
                table.ajax.reload(null,false);
            }
        });
    })
    //사용자 등록 로직
    $('#memberCreateModal').keydown(function (key){
        if(key.keyCode == 13){
            $('#c_confirm').click();
        }
    })
    $(function () {
        $("#c_confirm").on('click', function () {
            let member = JSON.stringify({
                memberId: $("#c_memberId").val(),
                name: $("#c_name").val(),
                password: $("#c_password").val(),
                department: $("#c_department").val(),
                position: $("#c_position").val(),
                region: $("#c_region").val()
            });
            $.ajax({
                type: "POST",
                contentType: 'application/json',
                url: "/api/member",
                data: member,
                error: function (e){
                    console.log(e);
                },
                success: function (){
                    table.ajax.reload(null,false);
                    $("#memberCreateModal").find('input[type=text]').each(function (){
                        $(this).val('');
                    })
                }
            });
        })
    })
});