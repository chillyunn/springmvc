$(function () {
    // 데이터테이블 초기화
    const table = initializeDatatable();
    var row = $('#datatable').DataTable().row($(this)).data();
    $('#datatable tbody').on('click', 'tr', function () {
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
        deleteMember(table);
    })
    //사용자 검색
    $("#btnSearch").on("click", function () {
        table.draw();
    });
    //사용자 등록 클릭
    $("#btnModal").on("click", function () {
        createMemberModal();
    })

    //엔터키로 사용자 등록
    $('#memberCreateModal').keydown(function (key) {
        if (key.keyCode == 13)
            $('#btnCreate').click();
    })
    //엔터키로 사용자 수정
    $('#memberUpdateModal').keydown(function (key) {
        if (key.keyCode == 13)
            $("#update").click();
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

    $("#memberCreateModal").find('input[type=text]').each(function () {
        $(this).val('');
    })
    $("#btnUpdate").hide();
    $("#btnDelete").hide();
    $("#btnCreate").show();
}

function updateMemberModal(row) {

    $("#memberModal").modal('show');
    $('#memberModal').on('show.bs.modal', function () {

        //사용자 수정 모달에 존재하는 input 태그 값 입력
        $('#id').text(row.id);
        $('#memberId').val(row.memberId);
        $('#name').val(row.name);
        $('#password').val(row.password);
        $('#department').val(row.department);
        $('#position').val(row.position);
        $('#region').val(row.region);
    });
    $("#btnUpdate").show();
    $("#btnDelete").show();
    $("#btnCreate").hide();
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
            $("#memberCreateModal").find('input[type=text]').each(function () {
                $(this).val('');
            })
        }
    });
}

function deleteMember(table) {
    console.log($("#id").text());
    $.ajax({
        type: "DELETE",
        url: "/api/member/" + $("#id").text(),
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