$(document).ready(function () {
    const table =$('#datatable').DataTable({
        pageLength:3,
        bLengthChange:false,
        ajax:{
            url: '/api/members',
            type: 'POST',
        },
        columns:[
            {data: 'id'},
            {data: 'memberId'},
            {data: 'name'},
            {data: 'password'},
            {data: 'position'},
            {data: 'region'},
            {data: 'department'},
        ]
    });
    $('#datatable tbody').on('click','tr',function (){
        const row = $('#datatable').DataTable().row($(this)).data();
        alert(row.id);
    });
    $(function (){
        $("#insertRow").on("click",function (){

        })
    })


});