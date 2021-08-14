const barrierAjaxUrl = "profile/barriers/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
  ajaxUrl: barrierAjaxUrl,
  updateTable: function () {
    $.ajax({
      type: "GET",
      url: barrierAjaxUrl + "filter",
      data: $("#filter").serialize()
    }).done(updateTableByData);
  }
};

function enable(chkbox, id) {
  var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
  $.ajax({
    url: barrierAjaxUrl + id,
    type: "POST",
    data: "enabled=" + enabled
  }).done(function () {
    chkbox.closest("tr").attr("data-barrierExcess", enabled);
    successNoty(enabled ? "common.enabled" : "common.disabled");
  }).fail(function () {
    $(chkbox).prop("checked", !enabled);
  });
}

function actionBarrier(id, action) {
  $.ajax({
    url: barrierAjaxUrl+"action/" + id,
    type: "POST",
    data: "setAction=" + action
  }).done(
    successNoty(action ? "common.openBarrier" : "common.closeBarrier")
  ).fail(function () {
  });
}

function clearFilter() {
  $("#filter")[0].reset();
  $.get(barrierAjaxUrl, updateTableByData);
}

// http://api.jquery.com/jQuery.ajax/#using-converters
$.ajaxSetup({
  converters: {
    "text json": function (stringData) {
      var json = JSON.parse(stringData);
      if (typeof json === 'object') {
        $(json).each(function () {
          if (this.hasOwnProperty('dateTime')) {
            this.dateTime = this.dateTime.substr(0, 16).replace('T', ' ');
          }
        });
      }
      return json;
    }
  }
});

$(function () {
  makeEditable(
      $("#datatable").DataTable({
        "ajax": {
          "url": barrierAjaxUrl,
          "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
          {
            "data": "ip_address"
          },
          {
            "data": "enabled",
            "render": function (data, type, row) {
              if (type === "display") {
                return "<input type='checkbox' " + (data ? "checked" : "")
                    + " onclick='enable($(this)," + row.id + ");'/>";
              }
              return data;
            }
          },
          {
            "data": "address"
          },
          {
            "data": "name"
          },
          {
            "data": "description"
          },
          {
            "render": function (data, type, row) {
                return "<span class='fa fa-unlock' onclick='actionBarrier(" + row.id + ",true);'></span>"
              return data;
            }
          },
          {
            "render": function (data, type, row) {
              return "<span class='fa fa-lock' onclick='actionBarrier(" + row.id + ",false);'></span>"
              return data;
            }
          },
          {
            "render": renderEditBtn,
            "defaultContent": "",
            "orderable": false
          },
          {
            "render": renderDeleteBtn,
            "defaultContent": "",
            "orderable": false
          }
        ],
        "order": [
          [
            0,
            "desc"
          ]
        ],
        "createdRow": function (row, data, dataIndex) {
          $(row).attr("data-barrierExcess", data.state);
        }
      })
  );
});