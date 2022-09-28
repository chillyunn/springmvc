$(function () {
    // 데이터테이블 초기화
    const table = initializeDatatable();
    $('#datatable tbody').on('click', 'tr', function () {
        const row = $('#datatable').DataTable().row($(this)).data();
        updateMemberModal(row);
    });
    //사용자 등록
    $("#btnCreate").on('click', function () {
        createMember(table);
    })
    //사용자 수정
    $("#btnUpdate").on('click', function () {
        updateMember(table);
    })
    //사용자 삭제
    $("#btnDelete").on('click', function () {
        const id = $('#id').text();
        deleteMember(table, id);
    })
    //사용자 검색
    $("#btnSearch").on("click", function () {
        table.draw();
    });
    //사용자 등록 클릭
    $("#btnModal").on("click", function () {
        createMemberModal();
    })
    //엔터키로 사용자 검색
    $('#search_value').keydown(function (key) {
        if (key.keyCode == 13)
            $("#btnSearch").click();
    })
});

function initializeDatatable() {
    const table = $('#datatable').DataTable({
        serverSide: true,
        processing: true,
        searching: false,
        lengthMenu: [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        ajax: {
            url: '/api/members/list',
            type: 'POST',
            data: function (d) {
                d.searchType = $("#search_type").val(),
                    d.searchValue = $("#search_value").val()
            }
        },
        columns: [
            //{data: 'id'},
            {data: 'memberId'},
            {data: 'name'},
            // {data: 'password'},
            {data: 'department'},
            {data: 'position'},
            {data: 'region'},
        ]
    });
    return table;
}

function createMemberModal() {
    $("#memberModal").modal('show');
    //모달 내부 input 값 초기화
    $("#memberModal").find('input[type=text]').each(function () {
        $(this).val('');
    })
    //수정,삭제 버튼 숨기고 등록 버튼 보이기
    $("#btnUpdate").hide();
    $("#btnDelete").hide();
    $("#btnCreate").show();
}

function updateMemberModal(row) {
    $('#id').text(row.id);
    $('#memberId').val(row.memberId);
    $('#name').val(row.name);
    $('#password').val(row.password);
    $('#department').val(row.department);
    $('#position').val(row.position);
    $('#region').val(row.region);

    //등록 버튼 숨기고 수정,삭제 버튼 보이기
    $("#btnUpdate").show();
    $("#btnDelete").show();
    $("#btnCreate").hide();
    $("#memberModal").modal('show');
}

function createMember(table) {
    const member = JSON.stringify({
        memberId: $("#memberId").val(),
        name: $("#name").val(),
        password: $("#password").val(),
        department: $("#department").val(),
        position: $("#position").val(),
        region: $("#region").val()
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
            $("#memberModal").find('input[type=text]').each(function () {
                $(this).val('');
            })
        }
    });
}

function deleteMember(table, id) {
    $.ajax({
        type: "DELETE",
        url: "/api/member/" + id,
        success: function () {
            table.ajax.reload(null, false);
        }
    });
}

function updateMember(table) {
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
}