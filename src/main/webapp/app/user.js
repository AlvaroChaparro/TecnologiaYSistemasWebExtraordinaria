function UserViewModel() {
	self = this;
	self.ws = null;

	this.email = ko.observable();
	this.userName = ko.observable();
	this.pwd = ko.observable();
	
	this.miTurno=ko.observable(false);
	this.turnoDeOponente=ko.observable(false);
	
	this.tablero = ko.observable(new Tablero(ko));
	this.nombreOponente = ko.observable();
	this.misPuntos = ko.observable(0);
	this.puntosOponente = ko.observable(0);
	this.secuenciasPropias = ko.observableArray([]);
	this.secuenciasOponente = ko.observableArray([]);

	this.showRegLog = ko.observable(true);
	this.showTablero = ko.observable(false);
	this.showSalaDeEspera = ko.observable(false);
	this.messageSalaDeEspera = ko.observable("");
	
	this.message = ko.observable();
	
    self.timer = ko.observable();
    // Cuando se acaban las letras se activa
    // y se deja una ultima jugada a cada jugador
    // antes de acabar
    self.ultimaJugada = false;

    self.minutes = ko.computed( function() {
        return Math.floor(self.timer() / 60) % 60;
    }, self);

    self.seconds = ko.computed( function() {
        return self.timer() % 60;
    }, self);


	setInterval(function() {
		if (self.timer() !== null){
			var newTimer = self.timer() -1;
			if(newTimer == 0){
				document.getElementById("btnLlamarLetras").click();
				document.getElementById("btnPasarTurno").click();
			}else{
				self.timer(newTimer);
			}
//	        self.timer(newTimer == 0 ? document.getElementById("btnPasarTurno").click() : newTimer);
		}
    }, 1000);

	
	this.nuevaPartida = function() {
		var info = {
			action : "Nueva partida"
		};
		var data = {
			data : info,
			url : "solicitarPartida",
			type : "post",
			success : partidaOK,
			error : error
		};
		$.ajax(data);
	}
	
	function partidaOK(respuesta) {
		$("#message").attr("style", "color:blue");
		self.message(respuesta);
		respuesta=JSON.parse(respuesta);
		if (respuesta.idPartida)
			sessionStorage.idPartida = respuesta.idPartida;
		self.ws = new WebSocket("ws://" + window.location.host + "/wsServer");
		self.ws.onopen = function(event) {
			$("#btnNuevaPartida").hide();
			$("#btnUnirAPartida").hide();
			if (respuesta.type == "PARTIDA LISTA") {
				var mensaje = {
					type : "INICIAR PARTIDA",
					idPartida : respuesta.idPartida
				};
				self.ws.send(JSON.stringify(mensaje));
			} else if (respuesta.type == "PARTIDA CREADA") {
				$("#message").attr("style", "color:blue");
				self.messageSalaDeEspera(self.userName() + ", espera a que llegue un oponente");
			}	
		}
		self.ws.onerror = function(event) {
			$("#message").attr("style", "color:red");
			self.message("Error");
		}
		self.ws.onclose = function(event) {
			$("#message").attr("style", "color:red");
			self.message("WebSocket cerrado");
		}
		self.ws.onmessage = function(event) {
			var jso = event.data;
			jso = JSON.parse(jso);
			$("#message").attr("style", "color:blue");
			self.message(event.data);
			if (jso.type == "START") {
				var r = (jso.turno ? "Tienes " : "No tienes ") + "el turno. Tus letras son: " + jso.letras;
				$("#btnJugar").attr("disabled", !jso.turno);
				$("#btnPasarTurno").attr("disabled", !jso.turno);
				$("#btnNuevasLetras").attr("disabled", !jso.turno);
				$("#btnMezclar").attr("disabled", !jso.turno);
				$("#btnLlamarLetras").attr("disabled", !jso.turno);
				if(jso.turno){
					self.timer(120);
				}else{
					self.timer(null);
				}				
				
				self.miTurno(jso.turno? ">" : "");
				self.turnoDeOponente(jso.turno ? "" : ">");
				self.showSalaDeEspera(false);
				self.showTablero(true);
				self.showRegLog(false);
				self.nombreOponente(jso.oponente);
				$("#message").attr("style", "color:blue");
				self.message(r);
				for (var i=0; i<jso.letras.length; i++)
					self.tablero().panel.push(jso.letras[i]);
				$('#divPanelButtons *').prop('disabled', !jso.turno);
			} else if (jso.type == "resultado") {
				if (jso.invalid.length != 0){
					$("#message").attr("style", "color:red");
					self.message(JSON.stringify(jso.invalid) + " is invalid");
					var invalid = jso.invalid;
					for (var i=0; i<invalid.length; i++) {
						secuencia=new Secuencia(invalid[i].sequence, 0, false);
						self.secuenciasPropias.push(secuencia);
					}
					self.tablero().llamarLetras();
				}else if (jso.exceptions.length != 0){
					$("#message").attr("style", "color:red");
					self.message(JSON.stringify(jso.exceptions));
				}else {
					$("#btnJugar").attr("disabled", !jso.turno);
					$("#btnPasarTurno").attr("disabled", !jso.turno);
					$("#btnLlamarLetras").attr("disabled", !jso.turno);
					$("#btnNuevasLetras").attr("disabled", !jso.turno);
					$("#btnMezclar").attr("disabled", !jso.turno);
					self.tablero().casillasJugada = [];

					if (jso.turno) {
						self.timer(120);
						self.miTurno(">");
						self.turnoDeOponente("");
						var puntos=0;
						var secuencia;
						var valid = jso.valid;
						for (var i=0; i<valid.length; i++) {
							puntos = puntos + valid[i].points;
							if (valid[i].sequence.length>1) {
								secuencia=new Secuencia(valid[i].sequence, valid[i].points, true);
								self.secuenciasOponente.push(secuencia);
							}
						}
						self.puntosOponente(self.puntosOponente() + puntos);
						actualizarTablero(jso.tablero);
						// Des/Activar el panel de letras
						$('#divPanelButtons *').prop('disabled', !jso.turno);
						
						// Control de letras restantes
						if(!jso.quedanLetras){
							$("#btnNuevasLetras").attr("disabled", true);
							$("#message").attr("style", "color:red");
							self.message(self.message()+"\nNo quedan mas letras. Ultimo movimiento.");
							if(self.ultimaJugada){
								terminarPartida();
							}else{
								self.ultimaJugada = true;
							}
						}else{
							$("#btnNuevasLetras").attr("disabled", false);
						}
					} else { 
						self.timer(null);
						self.miTurno("");
						self.turnoDeOponente(">");
						var valid = jso.valid;
						var puntos=0;
						var secuencia;
						for (var i=0; i<valid.length; i++) {
							puntos = puntos + valid[i].points;
							if (valid[i].sequence.length>1) {
								secuencia=new Secuencia(valid[i].sequence, valid[i].points, true);
								self.secuenciasPropias.push(secuencia);
							}
						}
						self.misPuntos(self.misPuntos() + puntos);
						self.tablero().casillasJugada=[];
						if (jso.letrasNuevas)
							for (var i=0; i<jso.letrasNuevas.length; i++)
								self.tablero().panel.push(jso.letrasNuevas[i]);
						actualizarTablero(jso.tablero);
						// Des/Activar el panel de letras
						$('#divPanelButtons *').prop('disabled', !jso.turno);
						
						// Control de letras restantes
						if(!jso.quedanLetras){
							$("#btnNuevasLetras").attr("display", "none");
							$("#message").attr("style", "color:red");
							self.message(self.message()+"\nNo quedan mas letras. Ultimo movimiento");
						}
					}
				}
			} else if (jso.type=="VICTORIA") {
				alert("HAS GANADO!");
				$.get("/salir", function() {
					window.location.reload();			
				});
			} else if (jso.type=="DERROTA") {
				alert("HAS PERDIDO!");
				$.get("/salir", function() {
					window.location.reload();			
				});
			} else if (jso.type=="EMPATE") {
				alert("HAS EMPATADO!");
				$.get("/salir", function() {
					window.location.reload();			
				});
			}
		}
	}
	
	function terminarPartida() {
		var msg = {
			type : "TERMINAR",
			idPartida : sessionStorage.idPartida,
			puntosA : self.misPuntos(),
			puntosB : self.puntosOponente()
		};
		self.ws.send(JSON.stringify(msg));
	}
	
	function actualizarTablero(tablero) {
		if (tablero) {
			var cont = 0;
			for (var i=0; i<15; i++) {
				for (var j=0; j<15; j++) {
					if (tablero[cont]!='-') {
						self.tablero().casillas()[i][j].letter(tablero[cont]);
						self.tablero().casillas()[i][j].provisional=false;
					}
					cont++;
				}
			}
		}
	}
	
	this.unirAPartida = function() {
		var info = {
			action : "Unir a partida"
		};
		var data = {
			data : info,
			url : "solicitarPartida",
			type : "post",
			success : partidaOK,
			error : error
		};
		$.ajax(data);
	}
	
	this.getUsers = function() {
		$.get("listaUsuarios", mostrarUsuarios);
	}
	
	function mostrarUsuarios(respuesta) {
		$("#message").attr("style", "color:blue");
		self.message(JSON.stringify(respuesta));
	}
	
	this.login = function() {
		var info = {
			userName : $("#loginUserName").val(),
			pwd : this.pwd(),
			withEmail : ($("#loginUserName").val().indexOf("@")!=-1)
		};
		var data = {
			data : info,
			url : "/login",
			type : "post",
			success : loginOk,
// success : self.loginOk,
			error : error
		};
		$.ajax(data);
	}
	
	this.salir = function() {
		$.get("/salir", function() {
			window.location.reload();			
		});
	}
	
	this.register = function() {
		var info = {
			email : $("#inputEmail").val(),
			userName : $("#inputUserName").val(),
			pwd1 : $("#inputPwd1").val(),
			pwd2 : $("#inputPwd2").val()
		};
		var data = {
			data : info,
			url : "register",
			type : "post",
			success : registerOk,
			error : error
		};
		$.ajax(data);		
	}
	
	function registerOk() {
		self.userName($("#inputUserName").val());
		self.email($("#inputEmail").val());
		self.pwd($("#inputPwd1").val());
		$("#message").attr("style", "color:blue");
		self.message("Register OK");
	}
	
	function loginOk(){
// this.loginOk() = function() {
		self.showSalaDeEspera(true);
		self.showTablero(false);
		self.showRegLog(false);
		$("#message").attr("style", "color:blue");
		self.messageSalaDeEspera("Bienvenid@" + self.userName());
	}
	
	function error(response) {
		$("#message").attr("style", "color:red");
		self.message(response.responseText);
	}
	
	if (sessionStorage.logueado && sessionStorage.emailGoogle){
		self.userName(sessionStorage.emailGoogle);
		self.loginOk();
		sessionStorage.removeItem("logueado");
		sessionStorage.removeItem("emailGoogle");
	}
}

class Tablero {	
	constructor(ko) {
		this.casillasNoKO = new Array();
		for (var i=0; i<15; i++) {
			this.casillasNoKO.push(new Array());
			for (var j=0; j<15; j++)
				this.casillasNoKO[i][j]=new Casilla(ko, this, i, j);
		}
		var tp=[[0, 2], [0, 12], [2, 0], [2, 14], [12, 0], [12, 14], [14, 2], [14, 12]];
		for (var i=0; i<tp.length; i++) {
			var coords = tp[i];
			this.casillasNoKO[coords[0]][coords[1]].letter("TP");
			this.casillasNoKO[coords[0]][coords[1]].clazz("scrabble-td triple-word");
		}
		tp=[[0, 4], [0, 10], [1, 1], [1, 13], [2, 6], [2, 8], [3, 3], [3, 11], [4, 0], [4, 14], [5, 5], [5, 9], [6, 2], [6, 12], [8, 2], [8, 12], [9, 5], [9, 9], 
			[10, 0], [10, 14], [11, 3], [11, 11], [12, 6], [12, 8], [13, 1], [13, 13], [14, 4], [14, 10]];
		for (var i=0; i<tp.length; i++) {
			var coords = tp[i];
			this.casillasNoKO[coords[0]][coords[1]].letter("TL");
			this.casillasNoKO[coords[0]][coords[1]].clazz("scrabble-td triple-letter");
		}
		tp=[[1, 5], [1, 9], [3, 7], [5, 1], [5, 13], [7,3], [7, 11], [9, 1], [9, 13], [11, 7], [13, 5], [13, 9]];
		for (var i=0; i<tp.length; i++) {
			var coords = tp[i];
			this.casillasNoKO[coords[0]][coords[1]].letter("DP");
			this.casillasNoKO[coords[0]][coords[1]].clazz("scrabble-td double-word");
		}
		tp=[[2, 2], [2, 12], [4, 6], [4, 8], [6, 4], [6, 10], [8, 4], [8, 10], [10, 6], [10, 8], [12, 2], [12, 12]];
		for (var i=0; i<tp.length; i++) {
			var coords = tp[i];
			this.casillasNoKO[coords[0]][coords[1]].letter("DL");
			this.casillasNoKO[coords[0]][coords[1]].clazz("scrabble-td double-letter");
		}
		this.casillasNoKO[7][7].letter("★");
		this.casillas=ko.observableArray(this.casillasNoKO);
		this.casillasJugada = [];
		this.panel = ko.observableArray([]);
		this.casillaSeleccionada = null;
	}
	
	seleccionar(letra) {
		if (this.casillaSeleccionada == null) {
			alert("No has seleccionado la casilla");
			return;
		}
		for (var i=0; i<this.panel().length; i++) {
			if (this.panel()[i]==letra) {
				this.panel.splice(i, 1);
				this.casillaSeleccionada.letter(letra);
				this.casillaSeleccionada=null;
				break;
			}
		}
	}
	
	pasarTurno() {
		var msg = {
			type : "PASO DE TURNO",
			idPartida : sessionStorage.idPartida
		};
		self.ws.send(JSON.stringify(msg));
	}
	
	mezclarPanel() {
		for (var i = 0; i < 50; i++) {
			var posicion = Math.floor(Math.random() * 7);
			self.tablero().panel.push(self.tablero().panel.splice(posicion,1)[0]);
		}
	}
	
	llamarLetras() {
		console.log(this.casillasJugada);
		var numeroLetras = this.casillasJugada.length;
		var ids = [];
		for (var i=0; i<numeroLetras; i++) {
			ids[i] = this.casillasJugada[i].row+
					','+this.casillasJugada[i].column;
		}
		ids.forEach(position => document.getElementById(position).click());
	}
	
	rendirse() {
		var msg = {
			type : "ABANDONO",
			idPartida : sessionStorage.idPartida
		};
		self.ws.send(JSON.stringify(msg));
		alert("TE HAS RENDIDO");
		$.get("/salir", function() {
			window.location.reload();			
		});
	}
	
	jugar() {
		console.log(this.casillasJugada);
		var casillas = [];
		for (var i=0; i<this.casillasJugada.length; i++) {
			casillas.push({
				row : this.casillasJugada[i].row,
				col : this.casillasJugada[i].column,
				letter : this.casillasJugada[i].letter()
			});
		}
		var msg = {
			type : "MOVIMIENTO",
			idPartida : sessionStorage.idPartida,
			casillas : casillas
		};
		self.ws.send(JSON.stringify(msg));
		
// Despues de un error se mezcla la jugada erronea
// con la siguiente, por lo que hay que limpiar
// el array de casillas jugadas
// UPDATE: este cambio deja de ser necesario al
// arreglar la validacion de la primera jugada
//		self.tablero().casillasJugada = [];
		
// console.log(this);
	}
	
	nuevasLetras() {
		var sizePanel = self.tablero().panel().length;
		for(var i=0; i<sizePanel; i++){
			self.tablero().panel.splice(0,1)[0];
		}
		var msg = {
			type : "CAMBIO DE LETRAS",
			idPartida : sessionStorage.idPartida,
			longitudPanel : sizePanel
		};
		self.ws.send(JSON.stringify(msg));
	}
}

class Casilla {
	constructor(ko, tablero, row, column) {
		this.tablero = tablero;
		this.letter = ko.observable('');
		this.clazz = ko.observable("scrabble-td");
		this.provisional = true; 
		this.row = row;
		this.column = column;
	}
	
	seleccionar() {
		if (this.provisional && this.letter()!='' && this.letter()!='★' && this.letter()!='TP' && 
				this.letter()!='DP' && this.letter()!='TL' && this.letter()!='DL') {
			this.tablero.panel.push(this.letter());
			this.letter('');
			var pos = this.tablero.casillasJugada.indexOf(this);
			this.tablero.casillasJugada.splice(pos, 1);
			return;
		} else if (this.provisional) {
			this.tablero.casillaSeleccionada = this;
			this.tablero.casillasJugada.push(this);
		}
	}
}

class Secuencia {
	constructor(texto, puntos, valida) {
		this.texto=ko.observable(texto);
		this.puntos=ko.observable(puntos);
		this.color=ko.observable(valida ? "green" : "red");
	}
} 

var user = new UserViewModel();
ko.applyBindings(user);