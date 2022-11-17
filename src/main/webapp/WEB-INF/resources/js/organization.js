

$(function (){
    initializeGroupTree();

    $('#btnShowGroupCreationModal').on('click',function(){
        initializeGroupCreationModal();
        $('#btnConfirmCreation').on('click',function(){
            createGroup();
        })
    })
    $('#btnShowGroupUpdateModal').on('click',function(){
        initializeGroupUpdateModal();
        $('#btnConfirmUpdate').on('click',function(){
            const id = getSelectedNodeId();
            updateGroup(id);
        })
    })


    $('#btnDeleteGroup').on('click',function (){
        deleteGroup();
    })
})
function getGroupList(){
    let result;
    $.ajax({
        type: "GET",
        url:"api/groups",
        dataType: "json",
        async:false,
        timeout:30000,
        error : function (request,status,error){
            console.log("error"+error);
        },
        success: function (data){
            result= changeParentValueToHash(data);
        }
    });
    return result;
}
function initializeGroupTree(){
    $('#groupTree').jstree({
        core:{
            data:getGroupList()
        },
    })
}

function refreshGroupTree() {
    $('#groupTree').jstree(true).settings.core.data = getGroupList();
    $('#groupTree').jstree(true).refresh();
}

function createGroup() {
    const group=JSON.stringify({
        name:$("#inputGroupName").val(),
        parentName:$("#inputParentGroupName").val(),
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
            refreshGroupTree();
        }
    })
}
function updateGroup(id) {
    const group=JSON.stringify({
        name:$("#inputGroupName").val(),
        parentName:$("#inputParentGroupName").val(),
        sort:$("#inputSortValue").val()
    });
    $.ajax({
        type:"PUT",
        contentType: 'application/json',
        url:"/api/group/"+id,
        data:group,
        error:function(e){
            console.log(e);
        },
        success: function(){
            refreshGroupTree();
        }
    })
}
function initializeGroupCreationModal() {
    showModalAndChangeLabel("그룹 추가");
    $("#groupModal").find('input[type=text]').each(function () {
        $(this).val('');
    })
    $("#inputParentGroupName").val(getSelectedNodeName());

    $("#btnConfirmUpdate")?.attr('id', 'btnConfirmCreation');
}

function initializeGroupUpdateModal() {
    showModalAndChangeLabel("그룹 수정");

    $("#inputGroupName").val(getSelectedNodeName());
    $("#inputParentGroupName").val(getSelectedNodeParentName());
    $("#inputSortValue").val(getSelectedNodeSort());

    $("#btnConfirmCreation")?.attr('id', 'btnConfirmUpdate');
}
function getSelectedNodeId() {
    return $('#groupTree').jstree('get_selected', true)[0]?.id;
}
function getSelectedNodeName(){
    return $('#groupTree').jstree('get_selected', true)[0]?.text;
}
function getSelectedNodeParentName(){
    const parentId = $('#groupTree').jstree('get_selected', true)[0].parent

    return $('#groupTree').jstree(true).get_node(parentId)?.text;
}
function getSelectedNodeSort(){
    return $('#groupTree').jstree('get_selected', true)[0]?.original.sort;
}

function deleteGroup(){
    const id= getSelectedNodeId();
    $.ajax({
        type:"DELETE",
        contentType: 'application/json',
        url:"/api/group/"+id,
        error:function(e){
            console.log(e);
        },
        success: function(){
            refreshGroupTree();
        }
    })
}
function changeParentValueToHash(data) {
    for (let i in data) {
        if (data[i].parent == null) {
            data[i].parent = "#"
        }
    }
    return data;
}
function showModalAndChangeLabel(label) {
    $("#groupModal").modal('show');
    $("#groupModalLabel").html(label)
}