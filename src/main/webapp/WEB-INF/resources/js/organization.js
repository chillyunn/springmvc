$(function (){
    getGroupList();

    $('#btnCreateGroup').on('click',function(){
        createGroup();
    })
})

function getGroupList(){
    $.ajax({
        type: "GET",
        url:"api/groups",
        dataType: "json",
        timeout:30000,
        error : function (request,status,error){
            console.log("error"+error);
        },
        success: function (data){
            changeParentValueToHash(data);
            initializeGroupTree(data)
        }
    });
}
function initializeGroupTree(data){
    $('#groupTree').jstree({
        core:{
            data:data
        },
    })
}
function createGroup() {
    const group=JSON.stringify({
        name:$("#inputGroupName").val(),
        parentName:$("#inputSarentGroupName").val(),
        sort:$("#inputSortValue").val()
    });
    $.ajax({
        type:"POST",
        contentType: 'application/json',
        url:"/api/group",
        data:group,
        error:function(e){
            console.log(e);
        },
        success: function(){
            // $('#organization-tree').jstree(true).settings.core.data=data.group
            // $('#organization-tree').jstree(true)

        }
    })
}
function changeParentValueToHash(data) {
    for (let i in data) {
        if (data[i].parent == null) {
            data[i].parent = "#"
        }
    }
}