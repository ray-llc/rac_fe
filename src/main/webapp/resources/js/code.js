(function() {
    $(function() {
        Token.init();

        var token = "43c9cff37352abaa3ce9357be291b29f7c39bb32",
            type = "ADDRESS",
            $suggestions = $("#suggestions"),
            $name = $("#name"),
            $email = $("#email"),
            $party = $("#party"),
            $outward = $("#outward"),
            $fixDataButton = $("#fixData"),
            $region = $("#region"),
            $city = $("#city"),
            $street = $("#street"),
            $house = $("#house"),
            $geo_lat = $("#geo_lat"),
            $geo_lon = $("#geo_lon");


        // просто подсказки
        var suggestionsInstance = $suggestions.suggestions({
            token: token,
            type: type,
            hint: false,
            addon: "clear",
            noSuggestionsHint: false,
            onInvalidateSelection: function() {
                console.log("ON INVALIDATE SELECTION");
            }
        });

        $fixDataButton.on("click", function() {
            $suggestions.suggestions().fixData();
        });

        $name.suggestions({
            token: token,
            type: "NAME"
        });

        $geo_lat.suggestions({
            token: token,
            type: "GEO_LAT"
        });

        $geo_lon.suggestions({
            token: token,
            type: "GEO_LON"
        });

        $email.suggestions({
            token: token,
            type: "EMAIL"
        });

        $outward.suggestions({
            token: token,
            type: "FNS_UNIT"
        });

        // регион
        $region.suggestions({
            token: token,
            type: type,
            hint: false,
            bounds: "region-area"
        });

        // город и населенный пункт
        $city.suggestions({
            token: token,
            type: type,
            hint: false,
            bounds: "city-settlement",
            constraints: $region
        });

        // улица
        $street.suggestions({
            token: token,
            type: type,
            hint: false,
            bounds: "street",
            constraints: $city
        });

        // дом
        $house.suggestions({
            token: token,
            type: type,
            hint: false,
            bounds: "house",
            noSuggestionsHint: false,
            constraints: $street
        });

        $("#url").suggestions({
            token: token,
            url:
                "https://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address1",
            type: type,
            hint: false,
            bounds: "city"
        });

        $("#city-729").suggestions({
            token: token,
            type: type,
            hint: false,
            bounds: "city"
        });

        // sug-798
        var $sug798 = $("#sug-798");
        $sug798.suggestions({
            type: "ADDRESS"
        });
        $sug798.suggestions().setSuggestion({
            value: $sug798.val(),
            data: {}
        });

        $party.suggestions({
            token: token,
            type: "PARTY",
            onSelect: function(suggestion) {
                console.log(suggestion);
            }
        });
    });
})();
