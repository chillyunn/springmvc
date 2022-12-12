$(function () {
    initializeGroupTree();
    initializeAgentTable();
    addGroupTreeEventListener();
    addAgentTableEventListener();
    addTabEventListener();
    addPolicyTableEvnetListner();
});

function getGroupList() {
    let result;
    $.ajax({
        type: "GET",
        url: "api/groups",
        dataType: "json",
        async: false,
        timeout: 30000,
        error: function (request, status, error) {
            console.log("error" + error);
        },
        success: function (data) {
            result = changeParentValueToHash(data);
        }
    });
    return result;
}

function initializeGroupTree() {
    const groupTree = $('#groupTree').jstree({
        core: {
            data: getGroupList()
        },
    })
    groupTree.bind("select_node.jstree", function (event, data) {
        // if (getSelectedNodeId() === data.node.id){
        //     data.instance.deselect_node(data.node);
        // }
        $('#agentTable').DataTable().destroy();
        initializeAgentTable();
    })
}

function initializeAgentTable() {
    $('#agentTable').DataTable({
        serverSide: true,
        processing: true,
        searching: false,
        ajax: {
            url: 'api/agents',
            type: 'POST',
            data: function (d) {
                d.searchType = "",
                    d.searchValue = "",
                    d.group = getSelectedNodeName() ? getSelectedNodeName() : null
            }
        },
        columns: [
            {data: 'name'},
            {data: 'groupName'},
            {data: 'ip'},
            {data: 'mac'},
            {data: 'createdAt'},
            {data: 'modifiedAt'},
        ]
    });
}

function refreshGroupTree() {
    $('#groupTree').jstree(true).settings.core.data = getGroupList();
    $('#groupTree').jstree(true).refresh();
}

function refreshPolicyTable() {
    $('#policyTable').DataTable().ajax.reload();
}

function refreshAgentTable() {
    $('#agentTable').DataTable().ajax.reload();
}

function createGroup() {
    const group = JSON.stringify({
        name: $("#inputGroupName").val(),
        parentName: $("#inputParentGroupName").val(),
        sort: $("#inputSortValue").val()
    });
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        url: "/api/group",
        data: group,
        error: function (e) {
            console.log(e);
        },
        success: function () {
            refreshGroupTree();
        }
    })
}

function updateGroup(id) {
    const group = JSON.stringify({
        name: $("#inputGroupName").val(),
        parentName: $("#inputParentGroupName").val(),
        sort: $("#inputSortValue").val()
    });
    $.ajax({
        type: "PUT",
        contentType: 'application/json',
        url: "/api/group/" + id,
        data: group,
        error: function (e) {
            console.log(e);
        },
        success: function () {
            refreshGroupTree();
        }
    })
}

function initializeGroupCreationModal() {
    showGroupModalAndChangeLabel("그룹 추가");
    $("#groupModal").find('input[type=text]').each(function () {
        $(this).val('');
    })
    $("#inputParentGroupName").val(getSelectedNodeName());

    $("#btnConfirmUpdate")?.attr('id', 'btnConfirmCreation');
}

function initializeGroupUpdateModal() {
    showGroupModalAndChangeLabel("그룹 수정");

    $("#inputGroupName").val(getSelectedNodeName());
    $("#inputParentGroupName").val(getSelectedNodeParentName());
    $("#inputSortValue").val(getSelectedNodeSort());

    $("#btnConfirmCreation")?.attr('id', 'btnConfirmUpdate');
}

function initializeAgentCreationModal() {
    showAgentModalAndChangeLabel("에이전트 추가")
    $("#agentModal").find('input[type=text]').each(function () {
        $(this).val('');
    })
    $("#inputAgentGroupName").val(getSelectedNodeName());
    $("#btnConfirmUpdateAgent")?.attr('id', 'btnConfirmCreateAgent');
}

function initializeAgentUpdateModal() {
    showAgentModalAndChangeLabel("에이전트 수정");
    const row = $("#agentTable").DataTable().row('.selected').data();
    $("#inputAgentName").val(row?.name);
    $("#inputAgentGroupName").val(row?.groupName);
    $("#inputIpValue").val(row?.ip);
    $("#inputMacValue").val(row?.mac);
    $("#btnConfirmCreateAgent")?.attr('id', 'btnConfirmUpdateAgent');
}

function getSelectedNodeId() {
    return $('#groupTree').jstree('get_selected', true)[0]?.id;
}

function getSelectedNodeName() {
    return $('#groupTree').jstree('get_selected', true)[0]?.text;
}

function getSelectedNodeParentName() {
    const parentId = $('#groupTree').jstree('get_selected', true)[0].parent

    return $('#groupTree').jstree(true).get_node(parentId)?.text;
}

function getSelectedNodeSort() {
    return $('#groupTree').jstree('get_selected', true)[0]?.original.sort;
}

function deleteGroup() {
    const id = getSelectedNodeId();
    $.ajax({
        type: "DELETE",
        contentType: 'application/json',
        url: "/api/group/" + id,
        error: function (e) {
            console.log(e);
        },
        success: function () {
            refreshGroupTree();
        }
    })
}

function deleteAgent(id) {
    $.ajax({
        type: "DELETE",
        contentType: 'application/json',
        url: "/api/agent/" + id,
        error: function (e) {
            console.log(e);
        },
        success: function () {
            refreshAgentTable();
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

function showGroupModalAndChangeLabel(label) {
    $("#groupModal").modal('show');
    $("#groupModalLabel").html(label)
}

function showAgentModalAndChangeLabel(label) {
    $("#agentModal").modal('show');
    $("#agentModalLabel").html(label);
}

function addGroupTreeEventListener() {
    $('#btnShowGroupCreationModal').on('click', function () {
        initializeGroupCreationModal();

        $('#btnConfirmCreation').off('click');
        $('#btnConfirmCreation').on('click', function () {
            createGroup();
        })
    });
    $('#btnShowGroupUpdateModal').on('click', function () {
        initializeGroupUpdateModal();
        $('#btnConfirmUpdate').off('click');
        $('#btnConfirmUpdate').on('click', function () {
            const id = getSelectedNodeId();
            updateGroup(id);
        })
    });
    $('#btnDeleteGroup').on('click', function () {
        deleteGroup();
    });
}

function createAgent() {
    const agent = JSON.stringify({
        name: $("#inputAgentName").val(),
        groupName: $("#inputAgentGroupName").val(),
        ip: $("#inputIpValue").val(),
        mac: $("#inputMacValue").val()
    });
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        url: "api/agent",
        data: agent,
        error: function (e) {
            console.log(e);
        },
        success: function () {
            refreshAgentTable();
        }
    })
}

function updateAgent(id) {
    const agent = JSON.stringify({
        name: $("#inputAgentName").val(),
        groupName: $("#inputAgentGroupName").val(),
        ip: $("#inputIpValue").val(),
        mac: $("#inputMacValue").val()
    });
    $.ajax({
        type: "PUT",
        contentType: 'application/json',
        url: "api/agent/" + id,
        data: agent,
        error: function (e) {
            console.log(e);
        },
        success: function () {
            refreshAgentTable();
        }
    })
}

function getSelectedRowId() {
    return $("#agentTable").DataTable().row('.selected').data().id;
}

function addAgentTableEventListener() {
    $('#btnShowAgentCreationModal').on('click', function () {
        initializeAgentCreationModal();
        $('#btnConfirmCreateAgent').off('click');
        $('#btnConfirmCreateAgent').on('click', function () {
            createAgent();
        });
    });
    $('#btnShowAgentUpdateModal').on('click', function () {
        initializeAgentUpdateModal();
        $('#btnConfirmUpdateAgent').off('click');
        $('#btnConfirmUpdateAgent').on('click', function () {

            updateAgent(getSelectedRowId());
        });
    });
    $('#btnDeleteAgent').on('click', function () {
        deleteAgent(getSelectedRowId());
    })
    $('#agentTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#agentTable').DataTable().$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
};

function initializePolicyTable() {
    $('#policyTable').DataTable({
        columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0,
            defaultContent: ''
        }],
        select: {
            style: 'os',
            selector: 'td:first-child'
        },
        order: [[1, 'asc']],
        serverSide: true,
        processing: true,
        searching: false,
        ajax: {
            url: 'api/policies',
            type: 'POST',
            data: function (d) {
                d.searchType = "",
                    d.searchValue = ""
            }
        },
        columns: [
            {data: null},
            {data: 'id'},
            {data: 'name'},
            {data: 'content'},
            {data: 'createdAt'},
            {data: 'modifiedAt'},
        ],
        initComplete: function () {
            $('#checkAll').prop("checked", false);
            $('#checkAll').click(function () {
                if ($(this).prop("checked")) {
                    $('#policyTable').DataTable().rows().select();
                } else {
                    $('#policyTable').DataTable().rows().deselect();
                }
            })
        },
        rowId: function (a) {
            return a.id;
        }
    });
}

function addPolicyTableEvnetListner() {
    $('#btnDeletePolicy').on('click', function () {
        const ids = JSON.stringify($('#policyTable').DataTable().rows('.selected').ids().toArray());
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: "/api/policies/delete",
            data: ids,
            error: function (e) {
                console.log(e);
            },
            success: function () {
                refreshPolicyTable();
            }
        })
    })
    $('#groupSelectTree').on("select_node.jstree", function (e, data) {
        $('#policyTargetSelectModal').modal('hide');

        $('#groupNameTable').DataTable().row.add({
            "name": data.node.text
        }).draw();
    });

    $('#btnConfirmCreatePolicy').on('click', function () {
        const groups = [];
        const agents = [];
        for (const i of $('#groupNameTable').DataTable().rows().data().toArray()) {
            groups.push(i.name);
        }
        for (const i of $('#agentNameTable').DataTable().rows().data().toArray()) {
            agents.push(i.name);
        }
        const policy = JSON.stringify({
            name: $("#inputPolicyName").val(),
            content: $('#policyContent').val(),
            groups: groups,
            agents: agents,
        });
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: "/api/policy",
            data: policy,
            error: function (e) {
                console.log(e);
            },
            success: function () {
                refreshPolicyTable();
            }
        })
    });
    $('#agentNameTable').on('click', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#agentNameTable').DataTable().$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    })
    $('#groupNameTable').on('click', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $('#groupNameTable').DataTable().$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    })
    $('#btnDeselectGroup').on('click', function () {
        if ($('#groupNameTable').hasClass('selected')) {
            $('#groupNameTable').DataTable().row('.selected').remove().draw(false);
        }
    })
    $('#btnDeselectAgent').on('click', function () {
        if ($('#agentNameTable').hasClass('selected')) {
            $('#agentNameTable').DataTable().row('.selected').remove().draw(false);
        }
    })
    $('#btnShowAgentTableModal').on('click', function () {
        $('#policyTargetSelectModal').modal('show');
        $('#agentSelectTableWrapper').css('display', 'block');
        //$('#groupSelectTree').jstree('destroy');
        $('#groupSelectTree').css('display', 'none');
        $('#agentSelectTable').DataTable({
            serverSide: true,
            processing: true,
            searching: false,
            destroy: true,
            ajax: {
                url: 'api/agents',
                type: 'POST',
                data: function (d) {
                    d.searchType = "",
                        d.searchValue = "",
                        d.group = getSelectedNodeName() ? getSelectedNodeName() : null
                }
            },
            columns: [
                {data: 'name'},
                {data: 'groupName'},
            ]
        });
        $('#agentSelectTable tbody').on('click', 'tr', function () {
            $('#policyTargetSelectModal').modal('hide');
            $('#agentSelectTable tbody').off('click');
            const data = $('#agentSelectTable').DataTable().row(this).data();
            $('#agentNameTable').DataTable().row.add({
                "name": data.name
            }).draw();

        });
    });
    $('#btnShowPolicyCreateModal').on('click', function () {
        $("#policyModal").modal('show');
        $("#inputPolicyName").val('');
        $('#policyContent').val('');

        $("#groupNameTable").DataTable({
            searching: false,
            ordering: false,
            info: false,
            paging: false,
            destroy: true,
            columns: [
                {data: 'name'}
            ]
        })
        $("#groupNameTable").DataTable().clear().draw();
        $('#groupNameTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $("#groupNameTable").DataTable().$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $('#btnDeselectGroup').click(function () {
            $("#groupNameTable").DataTable().row('.selected').remove().draw(false);
        });
        $("#agentNameTable").DataTable({
            searching: false,
            ordering: false,
            info: false,
            paging: false,
            destroy: true,
            columns: [
                {data: 'name'}
            ]
        });
        $("#agentNameTable").DataTable().clear().draw();
        $('#agentNameTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $("#agentNameTable").DataTable().$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $('#btnDeselectAgent').click(function () {
            $("#agentNameTable").DataTable().row('.selected').remove().draw(false);
        });
        $("#policyModalLabel").html("정책 추가");
    });

    $('#btnShowGroupTreeModal').on('click', function () {
        $('#policyTargetSelectModal').modal('show');
        $('#agentSelectTableWrapper').css('display', 'none');
        $('#groupSelectTree').css('display', 'block');
        $('#groupSelectTree').jstree({
            core: {
                data: getGroupList()
            },
        })
    });
};

function addTabEventListener() {
    $('a[data-bs-target="#organization-tab-pane"]').on('shown.bs.tab', function () {
        $('#policyTable').DataTable().destroy();
    });
    $('a[data-bs-target="#policy-tab-pane"]').on('shown.bs.tab', function () {
        initializePolicyTable();
    });
};