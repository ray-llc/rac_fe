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
        "data": "address"
      },
      {
        "data": "name"
      },
      {
        "data": "description"
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