/*
 * Local instalation
 * Content Delivery Network (CDN) based version
 */

// anonymous function
$(document).ready(function() {
	// Document Object Model (DOM) is ready
});

var f = function() {
}

jQuery(document).ready(f);

$(document).ready(function() {
	$(".dropdown-toggle").dropdown();
});

/*
 * $(function() { $("#about-dialog").dialog({ autoOpen : false, modal : true,
 * width : 650, height : 350, resize : true, show : { effect : "blind", delay :
 * 100, duration : 1000 }, hide : {
 * 
 * effect : "explode", duration : 1000 } });
 * 
 * $("#about-opener").click(function() { $("#about-dialog").dialog("open"); });
 * });
 */

/*
 * $(function() { $("#help-dialog").dialog({ autoOpen : false, modal : true,
 * width : 650, height : 350, resize : true, show : { effect : "blind", delay :
 * 100, duration : 1000 }, hide : { effect : "explode", duration : 1000 } });
 * 
 * $("#help-opener").click(function() { $("#help-dialog").dialog("open"); });
 * });
 */

/*
 * home: link unsubscribe si/no?
 */
$(function() {
	$("#unsubscribe").click(function(e) {
		e.preventDefault();
		// guarda la url de la etiqueta <a>
		var targetUrl = $(this).attr("href");
		// 46721
		$("#confirm-unsubscribe-dialog").dialog({
			resizable : true,
			modal : true,
			// height : 240,
			text : "whatever",
			show : {
				effect : "blind",
				duration : 1000
			},
			hide : {
				effect : "explode",
				duration : 1000
			},
			buttons : {
				"Confirma" : function() {
					window.location.href = targetUrl;
					$(this).dialog("close");
				},
				"Cancela" : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#confirm-unsubscribe-dialog").dialog("open");
	});
});

/*
 * TODO: edit: button delete confirm dialog
 */
$(function() {
	$("#btn-delete").click(function(event) {
		event.preventDefault();
		var target = event.target;
		// get containing form
		// var $form = $(this).closest('form');
		// var targetUrl = $form.attr("action");
		// var submitForm = $('#edit-form');
		// 46721
		$("#confirm-delete-user-dialog").dialog({
			resizable : true,
			modal : true,
			// height : 240,
			text : "whatever",
			show : {
				effect : "blind",
				duration : 1000
			},
			hide : {
				effect : "explode",
				duration : 1000
			},
			buttons : {
				"Confirma" : function() {
					// $('form#edit-form').submit();
					// submitForm.submit();
					// window.location.href = targetUrl;
					// $(event.relatedTarget).data('form')
					$(this).dialog("close");
				},
				"Cancela" : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#confirm-delete-user-dialog").dialog("open");
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

/* Must be activated using jquery. To enable all tooltips */

$(function() {
	$("[data-toggle='tooltip']").tooltip();
});

/* bootstrap login validator */

$(document).ready(function() {
	var validator = $("#form-login").bootstrapValidator({
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			username : {
				message : "Escribe tu email",
				validators : {
					notEmpty : {
						message : "Por favor, escribe tu email"
					},
					emailAddress : {
						message : "No es una email valido"
					}
				}
			},
			password : {
				message : "Escribe tu clave",
				validators : {
					notEmpty : {
						message : "Por favor, escribe tu clave"
					},
					stringLength : {
						min : 6,
						max : 15,
						message : "Debe tener entre 6 y 15 caracteres"
					},
					different : {
						field : "username",
						message : "la clave no puede ser igual que el email"
					}
				}
			}
		}
	});
});

/* bootstrap profile validator */

$(document).ready(function() {
	var validator = $("#form-profile").bootstrapValidator({
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			name : {
				message : "Escribe tu nombre",
				validators : {
					notEmpty : {
						message : "Por favor, escribe tu nombre"
					}
				}
			},
			city : {
				message : "Escribe tu ciudad",
				validators : {
					notEmpty : {
						message : "Por favor, escribe tu ciudad"
					},
					regexp : {
						message : "Ha de comenzar por mayuscula",
						regexp : "[A-Z]\\w+"
					}
				}
			},
			profession : {
				message : "Escribe tu profesion",
				validators : {
					notEmpty : {
						message : "Por favor, escribe tu profesion"
					}
				}
			},
			phone : {
				message : "Escribe tu telefono",
				validators : {
					notEmpty : {
						message : "Por favor, escribe tu telefono"
					},
					phone : {
						country : "ES",
						message : "El telefono no es valido"
					}
				}
			}
		}
	});
});

/* bootstrap changepwd validator */

$(document)
		.ready(
				function() {
					var validator = $("#form-changepwd")
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : "glyphicon glyphicon-ok",
											invalid : "glyphicon glyphico-remove",
											validating : "glyphicon glyphicon-refresh"
										},
										fields : {
											password : {
												message : "Escribe tu clave",
												validators : {
													notEmpty : {
														message : "Por favor, escribe tu clave"
													},
													stringLength : {
														min : 6,
														max : 12,
														message : "La logintud ha de estar entre 6 y 12 caracteres"
													}
												}
											},
											passwordConfirmation : {
												message : "Confirma tu clave",
												validators : {
													notEmpty : {
														message : "Por favor, confirma tu clave"
													},
													stringLength : {
														min : 6,
														max : 12,
														message : "La logintud ha de estar entre 6 y 12 caracteres"
													},
													identical : {
														field : "password",
														message : "La clave de confirmacion no es igual que la clave"
													}
												}
											}
										}
									});
				});

/* bootstrap register validator */

$(document)
		.ready(
				function() {
					var validator = $("#form-register")
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : "glyphicon glyphicon-ok",
											invalid : "glyphicon glyphico-remove",
											validating : "glyphicon glyphicon-refresh"
										},
										fields : {
											userName : {
												message : "Escribe tu email",
												validators : {
													notEmpty : {
														message : "Por favor, escribe tu email"
													},
													emailAddress : {
														message : "Por favor, escribe un email valido"
													}
												}
											},
											password : {
												message : "Escribe tu clave",
												validators : {
													notEmpty : {
														message : "Por favor, escribe tu clave"
													},
													stringLength : {
														min : 6,
														max : 12,
														message : "La logintud ha de estar entre 6 y 12 caracteres"
													}
												}
											},
											passwordConfirmation : {
												message : "Confirma tu clave",
												validators : {
													notEmpty : {
														message : "Por favor, confirma tu clave"
													},
													stringLength : {
														min : 6,
														max : 12,
														message : "La logintud ha de estar entre 6 y 12 caracteres"
													},
													identical : {
														field : "password",
														message : "La clave de confirmacion no es igual que la clave"
													}
												}
											}
										}
									});

				});

/* bootstrap contact validator */

$(document)
		.ready(
				function() {
					var validator = $("#form-contact")
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : "glyphicon glyphicon-ok",
											invalid : "glyphicon glyphico-remove",
											validating : "glyphicon glyphicon-refresh"
										},
										fields : {
											userName : {
												message : "Escribe tu email",
												validators : {
													notEmpty : {
														message : "Por favor, escribe tu email"
													},
													emailAddress : {
														message : "Por favor, escribe un email valido"
													}
												}
											},
											subject : {
												message : "Escribe el asunto",
												validators : {
													notEmpty : {
														message : "Por favor, el asunto"
													}
												/*
												 * , stringLength : { min : 8,
												 * max : 255, message : "La
												 * logintud ha de estar entre 8
												 * y 255 caracteres" }
												 */
												}
											},
											comments : {
												message : "Confirma tus comentarios",
												validators : {
													notEmpty : {
														message : "Por favor, tus comentarios"
													},
													stringLength : {
														min : 8,
														max : 255,
														message : "La logintud ha de estar entre 8 y 255 caracteres"
													}
												}
											}
										}
									});

				});

/* bootstrap resetpwd validator */

$(document).ready(function() {
	var validator = $("#form-resetpwd").bootstrapValidator({
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			userName : {
				message : "Escribe tu email",
				validators : {
					notEmpty : {
						message : "Por favor, escribe tu email"
					},
					emailAddress : {
						message : "No es una email valido"
					}
				}
			}
		}
	});
});

//$(document).ready(function() {
//
//	var noTime = $.fullCalendar.moment('2014-05-01');
//	var local = $.fullCalendar.moment('2014-05-01T12:00:00');
//	var utc = $.fullCalendar.moment.utc('2014-05-01T12:00:00');
//	var noTZ = $.fullCalendar.moment.parseZone('2014-05-01T12:00:00');
//
//	// page is now ready, initialize the calendar...
//
//	$('#calendar').fullCalendar({
//		customButtons : {
//			button1 : {
//				text : 'custom!',
//				click : function() {
//					alert('clicked the custom button!');
//				}
//			}
//		},
//		header : {
//			left : 'prevYear, title, nextYear, button1',
//			center : "month, agendaDay, agendaWeek",
//			// center: "month, basicDay, basicWeek, agendaDay, agendaWeek",
//			// center : 'timelineDay, timelineWeek, timelineMonth,
//			// timelineYear',
//			right : 'today prev,next'
//		},
//		firstDay : 1,
//		weekends : true,
//		// This option is useful for right-to-left languages such as Arabic and
//		// Hebrew.
//		isRTL : false,
//		hiddenDays : [],
//		// If true, the calendar will always be 6 weeks tall. If false, the
//		// calendar will have either 4, 5, or 6 weeks, depending on the month.
//		fixedWeekCount : false,
//		// Determines if week numbers should be displayed on the calendar.
//		weekNumbers : true,
//		businessHours : {
//			start : '09:00', // a start time (10am in this example)
//			end : '18:00', // an end time (6pm in this example)
//
//			dow : [ 1, 2, 3, 4, 5, 6 ]
//		// days of week. an array of zero-based day of week integers (0=Sunday)
//		// (Monday-Thursday in this example)
//		},
//		// integer also acceptable
//		// height: auto,
//		// width vs. height, this is the aspect ratio of the calendar's
//		// "content" area
//		aspectRatio : 1.35,
//		// Whether to automatically resize the calendar when the browser window
//		// resizes
//		handleWindowResize : true,
//		// A value of false (the default) will display all events as-is.
//		// A value of true will limit the number of events to the height of the
//		// day cell.
//		// An integer value will limit the events to a specific number of rows.
//		eventLimit : true, // for all non-agenda views
//		views : {
//			agenda : {
//				eventLimit : 6
//			// adjust to 6 only for agendaWeek/agendaDay
//			}
//		},
//		// Determines the action taken when the user clicks on a "more" link
//		// created by the eventLimit option. Other values "week" and "day"
//		eventLimitClick : "popover",
//		dayClick : function() {
//			alert('a day has been clicked!');
//		},
//		schedulerLicenseKey : 'CC-Attribution-NonCommercial-NoDerivatives',
//		// schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source'
//		// defaultView : 'timelineMonth',
//		// defaultView : 'timelineYear',
//		defaultView : 'agendaDay',
//		// defaultView: 'timelineDay',
//		// header : [ center, 'month,timelineFourDays' ],
//		views : {
//			timelineFourDays : {
//				type : 'timeline',
//				duration : {
//					days : 4
//				}
//			}
//		},
////		events : [ {
////			"id" : "1",
////			"resourceId" : "b",
////			"start" : "2015-12-06T10:00:00",
////			"end" : "2015-12-06T12:00:00",
////			"title" : "Clase XXXXXXXXX"
////		}, {
////			"id" : "2",
////			"resourceId" : "c",
////			"start" : "2015-12-07T05:00:00",
////			"end" : "2015-12-07T05:40:00",
////			"title" : "Clase YYYYYYYYYYYY"
////		}, ],
//		eventColor: '#378006',
//		// resourceColumns: [
//		// {
//		// labelText: 'First Name',
//		// field: 'fname'
//		// },
//		// {
//		// labelText: 'Last Name',
//		// field: 'lname'
//		// }
//		// ],
//		// resources: [
//		// {
//		// id: 'a',
//		// fname: 'John',
//		// lname: 'Smith'
//		// },
//		// {
//		// id: 'b',
//		// fname: 'Jerry',
//		// lname: 'Garcia'
//		// }
//		// ],
//		resources : [
//		// {
//		// id : 'a-1',
//		// title : 'Aula Magna'
//		// }, {
//		// id : 'a-2',
//		// title : 'Aula Alfa'
//		// }, {
//		// id : 'a-3',
//		// title : 'Aula Grande'
//		// }, {
//		// id : 'a-4',
//		// title : 'Aula Pequeña'
//		// }
//		],
//		editable : true,
//		eventDrop : function(event, delta, revertFunc) {
//
//			alert(event.title + " was dropped on " + event.start.format());
//
//			if (!confirm("Are you sure about this change?")) {
//				revertFunc();
//			}
//
//		},
//		slotWidth : 100,
//	});
//
//});
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
