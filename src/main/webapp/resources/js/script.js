// Замените на свой API-ключ
var token = "43c9cff37352abaa3ce9357be291b29f7c39bb32";
ymaps.ready(init);

var map;

function init () {

  map = new ymaps.Map("map", {
    center: [45.03912, 38.981502],
    zoom: 12
  }, {
    searchControlProvider: 'yandex#search'
  });

  // Обработка события, возникающего при щелчке
  // левой кнопкой мыши в любой точке карты.
  // При возникновении такого события показываем
  // текстовый адрес под картой.
  map.events.add('click', function (e) {
    var coords = e.get('coords');
    loadAddress(coords[0], coords[1]);
  });

  var accessor = map.cursors.push("arrow");
}

function loadAddress(lat, lon) {
  var promise = geolocate(lat, lon);
  promise
  .done(function(response) {
    if (response.suggestions.length) {
      showAddress(response.suggestions[0]);
    } else {
      $("#address").text("дом не найден");
    }
  })
  .fail(function(jqXHR, textStatus, errorThrown) {
    console.log(textStatus);
    console.log(errorThrown);
  });
}


function setLableFromEditBarriers() {
  var x = document.getElementById("longitude");
  var y = document.getElementById("latitude");
  if (typeof x  != "undefined" && typeof y  != "undefined")
  {
    if (x.value != null && y.value != null) {
      ymaps.ready(function () {
        var myPlacemark = new ymaps.Placemark([y.value, x.value], {}, {
          preset: 'islands#pinkDotIcon'
        });
        map.geoObjects.add(myPlacemark);
      })
    }
  }

}

function setLable(lat, lon, nbr_auto) {
  ymaps.ready(function(){
    var myPlacemark = new ymaps.Placemark([lat, lon], {
      iconCaption: nbr_auto
    }, {
      preset: 'islands#pinkDotIcon'
    });
    map.geoObjects.add(myPlacemark);
  })
}

function showAddress(suggestion) {
  var address = suggestion.value;

  var fld = document.getElementById("address");
  var x = document.getElementById("longitude");
  var y = document.getElementById("latitude");

  if (typeof x  != "undefined" && typeof y  != "undefined" && typeof fld  != "undefined")
  {
    if (x.value != null && y.value != null) {
      fld.value = address;
      x.value = suggestion.data.geo_lon;
      y.value = suggestion.data.geo_lat;
      x.setAttribute("readonly", "true");
      y.setAttribute("readonly", "true");
    }
  }

}

function geolocate(lat, lon) {
  var serviceUrl = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/geolocate/address";
  var request = {
    "lat": lat,
    "lon": lon
  };
  var params = {
    type: "POST",
    contentType: "application/json",
    headers: {
      "Authorization": "Token " + token
    },
    data: JSON.stringify(request)
  };
  return $.ajax(serviceUrl, params);
}

