package edu.uclm.esi.apalabreitor.apalabreitor.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uclm.esi.apalabreitor.apalabreitor.web.controllers.WebController;

public class Match {
	private String id;
	private User playerA;
	private User playerB;
	private User jugadorConElTurno;
	private Board board;
	private boolean ultimaJugadaA = false;
	private boolean ultimaJugadaB = false;

	public Match() {
		this.id = UUID.randomUUID().toString();
	}

	public void setPlayerA(User user) {
		this.playerA = user;
	}

	public void setPlayerB(User playerB) {
		this.playerB = playerB;
	}

	public String getId() {
		return id;
	}

	public void start(String idSession) {
		// Para testear
		this.jugadorConElTurno = this.playerA;
		
//		this.jugadorConElTurno = new Random().nextBoolean() ? this.playerA : this.playerB;
		this.board = new Board();

		try {
			JSONObject jsaA = new JSONObject();
			jsaA.put("type", "START");
			jsaA.put("letras", this.board.getLetters(7));
			jsaA.put("turno", jugadorConElTurno == playerA ? true : false);
			jsaA.put("oponente", playerB.getUserName());
			this.playerA.sendMessage(jsaA.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {

		}
		try {
			JSONObject jsaB = new JSONObject();
			jsaB.put("type", "START");
			jsaB.put("letras", this.board.getLetters(7));
			jsaB.put("turno", jugadorConElTurno == playerB ? true : false);
			jsaB.put("oponente", playerA.getUserName());
			this.playerB.sendMessage(jsaB.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {

		}
	}

	public void playerPlays(String idSession, JSONArray jsaJugada) throws Exception {
		ResultadoJugada resultado;
		User player = this.playerA.getSession().getId().equals(idSession) ? playerA : playerB;
		if (player != this.jugadorConElTurno) {
			resultado = new ResultadoJugada();
			resultado.addException("No tienes el turno");
			player.sendMessage(resultado);
		} else {
			ArrayList<JSONObject> jugada = new ArrayList<>();
			for (int i = 0; i < jsaJugada.length(); i++)
				jugada.add(jsaJugada.getJSONObject(i));
			resultado = this.board.movement(jugada);
			if (resultado.getExceptions().isEmpty() && resultado.invalid().isEmpty()) {
				resultado.setTurno(false);
				player.sendMessage(resultado);
				User contrincante = this.playerA == player ? playerB : playerA;
				resultado.ocultarLetras();
				resultado.setTurno(true);
				resultado.setTablero(board.toString());
				contrincante.sendMessage(resultado);
				cambiarTurno();
			} else {
				player.sendMessage(resultado);
				// Quitamos letras de la cadena invalida
				this.board.quitarLetras(jugada);
			}
		}
	}

	private void cambiarTurno() {
		this.jugadorConElTurno = (this.playerA == this.jugadorConElTurno ? this.playerB : this.playerA);
	}

	public void pasarTurno() {
		// Checkea que tiene turno
		cambiarTurno();
		ResultadoJugada resultado = new ResultadoJugada();
		resultado.setTurno(true);
		if(this.board.anyLettersLeft()) {
			resultado.setQuedanLetras(true);
		}else {
			resultado.setQuedanLetras(false);
		}
		// TODO Esta excepcion puede darse porque se ha perdido la conexion y hay que
		// ver como manejarlo
		try {
			this.jugadorConElTurno.sendMessage(resultado);
			resultado.setTurno(false);
			User player2 = (this.playerA == this.jugadorConElTurno ? this.playerB : this.playerA);
			player2.sendMessage(resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void nuevasLetras(int numeroDeLetras, String idSession) throws Exception {
		User player = this.playerA.getSession().getId().equals(idSession) ? playerA : playerB;
		User contrincante = this.playerA == player ? playerB : playerA;
		ResultadoJugada resultado = new ResultadoJugada();
		if (player != this.jugadorConElTurno) {
			resultado.addException("No es tu turno");
			player.sendMessage(resultado);
		} else {
			String letras = this.board.getLetters(numeroDeLetras);
			if(letras.length() < 7) {
				resultado.setQuedanLetras(false);
			}
			resultado.setLetrasNuevas(letras);
			player.sendMessage(resultado);
			resultado.setTurno(false);
			cambiarTurno();
			resultado.ocultarLetras();
			resultado.setTurno(true);
			resultado.setTablero(board.toString());
			contrincante.sendMessage(resultado);
		}
	}
	
	public void abandono(String idSession) throws JSONException {
		User perdedor = this.playerA.getSession().getId().equals(idSession) ? playerA : playerB;
		User ganador = this.playerA == perdedor ? playerB : playerA;
		JSONObject jsaGanador = new JSONObject();
		jsaGanador.put("type", "VICTORIA");
		try {
			ganador.sendMessage(jsaGanador.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void terminar(String idSession, int puntosA, int puntosB) throws JSONException {
		User player1 = this.playerA.getSession().getId().equals(idSession) ? playerA : playerB;
		User player2 = this.playerA == player1 ? playerB : playerA;
		JSONObject jsaPlayer1 = new JSONObject();
		JSONObject jsaPlayer2 = new JSONObject();
		if(puntosA > puntosB) {
			jsaPlayer1.put("type", "VICTORIA");
			jsaPlayer2.put("type", "DERROTA");
		}else if(puntosA < puntosB) {
			jsaPlayer1.put("type", "DERROTA");
			jsaPlayer2.put("type", "VICTORIA");
		} else if (puntosA == puntosB) {
			jsaPlayer1.put("type", "EMPATE");
			jsaPlayer2.put("type", "EMPATE");
		}
		try {
			player1.sendMessage(jsaPlayer1.toString());
			player2.sendMessage(jsaPlayer2.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
