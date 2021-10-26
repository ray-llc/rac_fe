// Замените на свой API-ключ
var token = "43c9cff37352abaa3ce9357be291b29f7c39bb32";
ymaps.ready(init);

var map;

function init () {

  map = new ymaps.Map("map", {
    center: [45.018633, 38.962237],
    zoom: 15
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

function setLable(lat, lon, nbr_auto, enable) {
  if(enable) {
    var prst = 'islands#darkGreenDotIcon';
  } else {
    var prst = 'islands#redDotIcon';
  }
  ymaps.ready(function(){
    var myPlacemark = new ymaps.Placemark([lat, lon], {
      iconCaption: nbr_auto
    }, {
      preset: prst
    });
    map.geoObjects.add(myPlacemark);
  })
}

function setBaloon(lat, lon, name, enable) {
  if(enable) {
    var set_color = '"color: green;"';
    var state = 'открыт';
  } else {
    var set_color = '"color: red;"';
    var state = 'закрыт';
  }
  ymaps.ready(function(){
    var myPlacemark = new ymaps.Placemark([lat, lon], {
      balloonContentHeader: '<span class="description" style='+set_color+' >'+name+'</span>',
      // Зададим содержимое основной части балуна.
      balloonContentBody: '<b>'+state+'</b>'
    });
    map.geoObjects.add(myPlacemark);
    myPlacemark.balloon.open();
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

