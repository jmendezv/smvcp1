$(document).ready(function() {

});

$(document).ready(function() {
    $(".dropdown-toggle").dropdown();
});

$(function() {
	$("#about_dialog").dialog({
		autoOpen : false,
		modal : true,
		width : 650,
		height : 350,
		resize : true,
		show : {
			effect : "blind",
			delay : 100,
			duration : 1000
		},
		hide : {
			/* fade */
			effect : "explode",
			duration : 1000
		}
	});

	$("#about_opener").click(function() {
		$("#about_dialog").dialog("open");
	});
});

$(function() {
	$("#help_dialog").dialog({
		autoOpen : false,
		modal : true,
		width : 650,
		height : 350,
		resize : true,
		show : {
			effect : "blind",
			delay : 100,
			duration : 1000
		},
		hide : {
			/* fade */
			effect : "explode",
			duration : 1000
		}
	});

	$("#help_opener").click(function() {
		$("#help_dialog").dialog("open");
	});
});

$(function() {
	$("#unsubscribe").click(function(e) {
		e.preventDefault();
		var targetUrl = $(this).attr("href");

		$("#confirm_dialog").dialog({
			buttons : {
				"Confirma" : function() {
					window.location.href = targetUrl;
				},
				"Cancela" : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#confirm_dialog").dialog("open");
	});
});

$(function() {
	$("#admin_service_dialog").dialog({
		autoOpen : false,
		modal : true,
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});

	$("#admin_service").click(function(e) {
		$("#admin_service_dialog").delay(100).dialog("open");
	});
});





/*
 * $('#map_canvas').gmap().bind('init', function(ev, map) {
 * $('#map_canvas').gmap('addMarker', {'position': '57.7973333,12.0502107',
 * 'bounds': true}).click(function() { $('#map_canvas').gmap('openInfoWindow',
 * {'content': 'Hello World!'}, this); }); });
 */
/*
 * $('#map_canvas').gmap('addMarker', { 'position' : '57.7973333,12.0502107',
 * 'bounds' : true }).click(function() { $('#map_canvas').gmap('openInfoWindow', {
 * 'content' : 'Hello World!' }, this); });
 */
