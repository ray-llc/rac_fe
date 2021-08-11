// Замените на свой API-ключ
var token = "43c9cff37352abaa3ce9357be291b29f7c39bb32";

 // function StartInit () {
$(window).load(function(){
ymaps.ready(init);
 });

  var map;

function init () {
  map = new ymaps.Map("map", {
    center: [55.742245, 37.628056],
    zoom: 16
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

function showAddress(suggestion) {
  var address = suggestion.value;
  if (suggestion.data.postal_code) {
    // address = suggestion.data.postal_code + ", " + address;
  }
  $("#address").text(address);
  $("#longitude").text(suggestion.data.geo_lon);
  $("#latitude").text(suggestion.data.geo_lat);
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