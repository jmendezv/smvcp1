$(document).ready(function() {

});

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

/* tooltips */

$(document).ready(function() {
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
														min : 8,
														max : 16,
														message : "La logintud ha de estar entre 8 y 16 caracteres"
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
														min : 8,
														max : 16,
														message : "La logintud ha de estar entre 8 y 16 caracteres"
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
													emailAddress:{
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
														min : 8,
														max : 16,
														message : "La logintud ha de estar entre 8 y 16 caracteres"
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
														min : 8,
														max : 16,
														message : "La logintud ha de estar entre 8 y 16 caracteres"
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
