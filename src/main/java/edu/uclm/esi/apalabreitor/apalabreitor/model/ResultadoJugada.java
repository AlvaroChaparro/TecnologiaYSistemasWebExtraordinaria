package edu.uclm.esi.apalabreitor.apalabreitor.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultadoJugada {
	private List<Cadena> valid;
	private List<Cadena> invalid;
	private List<String> exceptions;
	private String letrasNuevas;
	private boolean turno;
	private String tablero;
	private boolean quedanLetras = true;
	
	public boolean isQuedanLetras() {
		return quedanLetras;
	}

	public void setQuedanLetras(boolean quedanLetras) {
		this.quedanLetras = quedanLetras;
	}

	public ResultadoJugada() {
		this.valid=new ArrayList<>();
		this.invalid = new ArrayList<>();
		this.exceptions = new ArrayList<>();
	}

	public String getTablero() {
		return tablero;
	}

	public void setTablero(String tablero) {
		this.tablero = tablero;
	}

	public int getPoints() {
		int r=0;
		for (Cadena cadena : valid)
			r+=cadena.getPoints();
		return r;
	}
	
	public List<Cadena> getValid() {
		return valid;
	}
	
	public List<Cadena> getInvalid() {
		return invalid;
	}
	
	public List<String> getExceptions() {
		return exceptions;
	}

	public void addNotAccepted(Cadena cadena) {
		this.invalid.add(cadena);
	}

	public void addAccepted(Cadena cadena) {
		this.valid.add(cadena);
	}

	public boolean accepts(String... palabras) {
		for (String palabra : palabras) {
			if (!in(palabra, this.valid))
				return false;
		}
		return true;
	}	
	
	public boolean notAccepts(String... palabras) {
		for (String palabra : palabras) {
			if (!in(palabra, this.invalid))
				return false;
		}	
		return true;
	}

	private boolean in(String palabra, List<Cadena> cadenas) {
		for (Cadena cadena : cadenas)
			if (cadena.getText().equals(palabra))
				return true;
		return false;
	}

	public boolean acceptsAll() {
		return invalid.isEmpty();
	}

	public void addException(String message) {
		this.exceptions.add(message);
	}

	public List<Cadena> invalid() {
		return this.invalid;
	}
	
	@Override
	public String toString() {
		return "Válidas: " + valid.toString() + "\nInválidas: " + invalid.toString() + "\nPoints: " + this.getPoints();
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject jso = new JSONObject();
		JSONArray jsaValid=new JSONArray();
		for (Cadena cadena : valid)
			jsaValid.put(cadena.toJSON());
		jso.put("valid", jsaValid);
		JSONArray jsaInvalid=new JSONArray();
		for (Cadena cadena : invalid)
			jsaInvalid.put(cadena.toJSON());
		jso.put("invalid", jsaInvalid);
		JSONArray jsaExceptions=new JSONArray();
		for (String ex : exceptions)
			jsaExceptions.put(ex);
		jso.put("exceptions", jsaExceptions);
		jso.put("letrasNuevas", this.letrasNuevas);
		jso.put("turno", this.turno);
		jso.put("type", "resultado");
		jso.put("tablero", tablero);
		jso.put("quedanLetras", this.quedanLetras);
		return jso;
	}

	public void setLetrasNuevas(String letrasNuevas) {
		this.letrasNuevas = letrasNuevas;
	}
	
	public String getLetrasNuevas() {
		return letrasNuevas;
	}

	public void ocultarLetras() {
		this.letrasNuevas="";
	}

	public void setTurno(boolean turno) {
		this.turno=turno;
	}
}
