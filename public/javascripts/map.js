/**************************************************************/
/*				GLOBAL VARIABLES							  */
/**************************************************************/

var OSM_ADDRESS_SEARCH_URL = "http://nominatim.openstreetmap.org/search?q=";
var OSM_GEOCODE_SEARCH_URL = "http://nominatim.openstreetmap.org/reverse?format=json";
var OSM_FINAL_PARAMS = "&format=json&limit=1&email=admin@senseship.org";
var OSM_DEFAULT_ZOOM = 16;

var projectMapMarker;
var projectMap;

/**************************************************************/
/*				MAP FUNCTIONS								  */
/**************************************************************/

function initMap(){
    var options = {
        'latitude' : '52.5015955',
        'longitude' : '13.4023271',
        'container' : "mapContainer",
        'zoom' : 13
    };
    createMap(options);
    projectMap.on('click', onMapClick);
}

/**
 * Creates a new Leaflet map with the passing parameters
 * @param latitude
 * @param longitude
 */
function createMap(options) {
	//Set default options
	defaultOptions = {
		'latitude' : '52.5015955',
		'longitude' : '13.4023271',
		'container' : 'mapContainer',
		'zoom' : OSM_DEFAULT_ZOOM
	};

	if( typeof options == 'object') {
		options = $.extend(defaultOptions, options);
	} else {
		options = defaultOptions;
	}

	projectMap = new L.Map(options.container);
	var cloudmadeUrl = 'http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', cloudmadeAttribution = 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery &copy; <a href="http://cloudmade.com">CloudMade</a>', cloudmade = new L.TileLayer(cloudmadeUrl, {
		attribution : cloudmadeAttribution,
		maxZoom : 18
	});

	var center = new L.LatLng(options.latitude, options.longitude);
	// geographical point (longitude and latitude)
	projectMap.setView(center, options.zoom).addLayer(cloudmade);
    var markerOptions = {
        'clickable' : false,
        'draggable' : false,
        'zoom' : OSM_DEFAULT_ZOOM
    };
    createMarker(options.latitude, options.longitude, markerOptions);
}

/**
 * Event for the action of clicking on a map
 */
function onMapClick(e) {

	createMarker(e.latlng.lat, e.latlng.lng);

	//Update the result div
	searchForAddres(e.latlng.lat, e.latlng.lng);
}

function createMarker(latitude, longitude, options) {
	//Define options for the marker
	var defaultOptions = {
		'clickable' : true,
		'draggable' : true,
		'zoom' : OSM_DEFAULT_ZOOM
	}

	if( typeof options == 'object') {
		options = $.extend(defaultOptions, options);
	} else {
		options = defaultOptions;
	}

	//Remove marker if there is already one created
	if(projectMapMarker != null) {
		projectMap.removeLayer(projectMapMarker);
	}

	var latlng = new L.LatLng(latitude, longitude)
	
	//Create marker
	projectMapMarker = new L.Marker(latlng, options);

	//Adds the update event for the marker dragging
	projectMapMarker.on('dragend', function(e){
		searchForAddres(e.target._latlng.lat, e.target._latlng.lng);
	});

	//Add it to the map
	projectMap.addLayer(projectMapMarker);
}

/**
 * Hack to fix the creation of map elements inside a hidden div
 * @param {Object} map
 * @param {Object} elementLauncher
 */
function fixMapInMenu(map, elementLauncher) {
	$("body").on('shown', elementLauncher, function() {
		L.Util.requestAnimFrame(map.invalidateSize, map, !1, map._container);
	});
}

function searchForAddres(latitude, longitude) {
	var url = OSM_GEOCODE_SEARCH_URL;
	url = url + "&lat=" + latitude;
	url = url + "&lon=" + longitude;
	url = url + OSM_FINAL_PARAMS;

	$.getJSON(url, {}, function(data) {
		printMapResult(data)
	});
}

function printMapResult(response) {
	console.log(response);
	$("#mapResultAddress").html(response.display_name);
    updateFormFields(response);
}

function processReverseGeoSearch(data) {

	printMapResult(data[0]);

    updateMap(data[0].lat, data[0].lon);
    updateFormFields(data[0]);
}

/**
 * Updates the form hidden inputs
 * @param response
 */
function updateFormFields(response){
    //latitude
    var lat = response.lat;
    $('#project\\.latitude').val(lat);

    //longitude
    var longitude = response.lon;
    $('#project\\.longitude').val(longitude);

    //address
    var displayAddress = response.display_name;
    $('#project\\.displayAddress').val(displayAddress);
}

/**
 * Updates the map with the current coordinates
 * @param latitude
 * @param longitude
 */
function updateMap(latitude, longitude){
    var latlng = new L.LatLng(latitude,longitude);
    projectMap.setView(latlng, OSM_DEFAULT_ZOOM);
    createMarker(latitude, longitude);
}

function searchAddress() {
	var url = OSM_ADDRESS_SEARCH_URL;

	var address = $("#userAddress").val();

	url = url + address + OSM_FINAL_PARAMS;
	url = encodeURI(url);

	console.log("url -> " + url);

	$.getJSON(url, {}, function(data) {
		processReverseGeoSearch(data);
	});

    return false;
}

