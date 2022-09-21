$(document).ready(function () {
    //사용자 조회 로직
    const table = $('#datatable').DataTable({
        serverSide: true,
        processing: true,
        lengthMenu: [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        ajax: {
            url: '/api/members/pageable',
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
        let row = $('#datatable').DataTable().row($(this)).data();
        //사용자 수정 모달에 존재하는 input 태그 값 입력
        $('#id').text(row.id);
        $('#memberId').val(row.memberId);
        $('#name').val(row.name);
        $('#password').val(row.password);
        $('#department').val(row.department);
        $('#position').val(row.position);
        $('#region').val(row.region);
        $('#memberUpdateModal').modal('show');
    });
    //사용자 수정 로직
    $('#memberUpdateModal').keydown(function (key) {
        if (key.keyCode == 13)
            $("#update").click();
    })
    $("#update").on('click', function () {
        const url = "/api/member/" + $("#id").text();
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
            error: function (e) {
                console.log(e);
            },
            success: function () {
                table.ajax.reload(null, false);
            }
        });
    })
    //사용자 삭제 로직
    $("#delete").on('click', function () {
        const url = "/api/member/" + $("#id").text();
        $.ajax({
            type: "DELETE",
            url: url,
            success: function () {
                table.ajax.reload(null, false);
            }
        });
    })
    //사용자 등록 로직
    $('#memberCreateModal').keydown(function (key) {
        if (key.keyCode == 13)
            $('#c_confirm').click();
    })
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
            error: function (e) {
                console.log(e);
            },
            success: function () {
                table.ajax.reload(null, false);
                $("#memberCreateModal").find('input[type=text]').each(function () {
                    $(this).val('');
                })
            }
        });
    })
    //데이터테이블 기본 검색창 숨기기
    $("#datatable_filter").attr("hidden","hidden");
    //검색 로직
    $("#btnSearch").on("click",function (){
        //검색 후 재 검색시 이전에 검색했던 데이터 제거
        const numCols = table.columns().nodes().length;
        for(let i=0;i<numCols;i++)
            table.column(i).search('');

        const searchType = $("#search_type").val();
        const searchValue= $("#search_value").val();
        if(searchType == 0)
            table.columns([0,1,2,3,4,5,6]).search(searchValue).draw();
        else
            table.column(searchType).search(searchValue).draw();
    })
});