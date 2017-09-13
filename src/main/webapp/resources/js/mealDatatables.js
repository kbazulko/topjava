var ajaxUrl = "ajax/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});

function updateTable() {

    $.ajax({
        url : ajaxUrl + "filter",
        type : "GET",
        data : $("#filter").serialize(),
        success : updateMealTable
    });

}

function updateMealTable(data) {
    datatableApi.clear().rows.add(data).draw();
}

function clearFilter() {
    $("#filter")[0].reset();
    updateTable();
}