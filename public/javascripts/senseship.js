/**************************************************************/
/*				GLOBAL VARIABLES							  */
/**************************************************************/

var projectMapMarker;
var projectMap;

/**************************************************************/
/*				MAP FUNCTIONS								  */
/**************************************************************/

/**
 * Creates a new Leaflet map with the passing parameters
 * @param latitude
 * @param longitude
 */
function createMap(latitude, longitude, zoom) {

	var map = new L.Map('mapSelector');
	var cloudmadeUrl = 'http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', cloudmadeAttribution = 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery &copy; <a href="http://cloudmade.com">CloudMade</a>', cloudmade = new L.TileLayer(cloudmadeUrl, {
		attribution : cloudmadeAttribution,
		maxZoom : 18
	});

	var center = new L.LatLng(latitude, longitude);
	// geographical point (longitude and latitude)
	map.setView(center, zoom).addLayer(cloudmade);

	return map;
}

/**
 * Event for the action of clicking on a map
 */
function onMapClick(e) {

	//Define options for the marker
	var options = {
		'clickable' : true,
		'draggable' : true
	}

	//Remove marker if there is already one created
	if(projectMapMarker != null) {
		this.removeLayer(projectMapMarker);
	}

	//Create marker
	var marker = new L.Marker(e.latlng, options);

	//Add it to the map
	this.addLayer(marker);

	//Set the marker in the global variable
	projectMapMarker = marker

	//Update the result div
	searchForAddres(e.latlng.lat, e.latlng.lng);
}

/**
 * Fixes problem with maps being drawn in hidden divs
 * @param {Object} options {String latitude, String longitude}
 */
function createMapforEditProject(options) {
	defaultOptions = {
		'longitude' : 'foo-default',
		'latitude' : 'bar-default',
		'zoom' : 13
	}

	if( typeof options == 'object') {
		options = $.extend(defaultOptions, options);
	} else {
		options = defaultOptions;
	}
	var map = createMap(options.latitude, options.longitude, options.zoom);

	return map;
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
	var url = "http://nominatim.openstreetmap.org/reverse?format=json";
	url = url + "&lat=" + latitude;
	url = url + "&lon=" + longitude;


	$.getJSON(url, {}, function(data){
		printMapResult(data)
	})
}

function printMapResult(response) {
	$("#mapResultAddress").html(response.display_name);
}

function searchAddress(){
	var url = " http://nominatim.openstreetmap.org/search?format=json";
	
	var address = $("#userAddress").val();
	var addressParts = address.split(" ");

	_.each(addressParts,function(param){
		url = url+"&"+param;
	})
	
	
	$.getJSON(url, {}, function(data){
		printMapResult(data)
	})
}

