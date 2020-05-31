package edu.uclm.esi.apalabreitor.apalabreitor.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTest {
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_DESCONFIARE_DESAGRUPE_PRESTE_PETALOS_SUBSTANCIAREMOS_ASTAR_TU() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));
			
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaConfiare = buildJSA("CON", 7, 1, 8, 1, 9, 1);
			resultado = board.movement(jsaConfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesconfiare = buildJSA("DES", 4, 1, 5, 1, 6, 1);
			resultado = board.movement(jsaDesconfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesagrupe = buildJSA("DSAGRUE", 5, 0, 5, 2, 5, 3, 5, 4, 5, 5, 5, 6, 5, 8);
			resultado = board.movement(jsaDesagrupe);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("DESAGRUPE"));
			
			List<JSONObject> jsaPreste = buildJSA("PREST", 0, 8, 1, 8, 2, 8, 3, 8, 4, 8);
			resultado = board.movement(jsaPreste);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("PRESTE"));
			
			List<JSONObject> jsaPetalo = buildJSA("ETALOS", 0, 9, 0, 10, 0, 11, 0, 12, 0, 13, 0, 14, 0, 15);
			resultado = board.movement(jsaPetalo);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("PETALOS"));
			
			List<JSONObject> jsaSubstanciaremos = buildJSA("UBSTANCIAREMOS", 1, 14, 2, 14, 3, 14, 4, 14, 5, 14, 6, 14, 7, 14, 8, 14, 9, 14, 10, 14, 11, 14, 12, 14, 13, 14, 14, 14);
			resultado = board.movement(jsaSubstanciaremos);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("SUBSTANCIAREMOS"));
			
			List<JSONObject> jsaAstar = buildJSA("STAR", 11, 0, 12, 0, 13, 0, 14, 0);
			resultado = board.movement(jsaAstar);
			assertTrue(resultado.notAccepts("ASTAR", "AR", "RE"));
			assertTrue(resultado.accepts("SI", "TA"));
			
			List<JSONObject> jsaTu = buildJSA("U", 11, 4);
			resultado = board.movement(jsaTu);
			assertTrue(resultado.accepts("TU"));
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_DESCONFIARE_DESAGRUPE_PRESTE_PETALOS_SUBSTANCIAREMOS() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaConfiare = buildJSA("CON", 7, 1, 8, 1, 9, 1);
			resultado = board.movement(jsaConfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesconfiare = buildJSA("DES", 4, 1, 5, 1, 6, 1);
			resultado = board.movement(jsaDesconfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesagrupe = buildJSA("DSAGRUE", 5, 0, 5, 2, 5, 3, 5, 4, 5, 5, 5, 6, 5, 8);
			resultado = board.movement(jsaDesagrupe);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("DESAGRUPE"));
			
			List<JSONObject> jsaPreste = buildJSA("PREST", 0, 8, 1, 8, 2, 8, 3, 8, 4, 8);
			resultado = board.movement(jsaPreste);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("PRESTE"));
			
			List<JSONObject> jsaPetalo = buildJSA("ETALOS", 0, 9, 0, 10, 0, 11, 0, 12, 0, 13, 0, 14, 0, 15);
			resultado = board.movement(jsaPetalo);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("PETALOS"));
			
			List<JSONObject> jsaSubstanciaremos = buildJSA("UBSTANCIAREMOS", 1, 14, 2, 14, 3, 14, 4, 14, 5, 14, 6, 14, 7, 14, 8, 14, 9, 14, 10, 14, 11, 14, 12, 14, 13, 14, 14, 14);
			resultado = board.movement(jsaSubstanciaremos);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("SUBSTANCIAREMOS"));
			
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_DESCONFIARE_DESAGRUPE_PRESTE_PETALOS() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaConfiare = buildJSA("CON", 7, 1, 8, 1, 9, 1);
			resultado = board.movement(jsaConfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesconfiare = buildJSA("DES", 4, 1, 5, 1, 6, 1);
			resultado = board.movement(jsaDesconfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesagrupe = buildJSA("DSAGRUE", 5, 0, 5, 2, 5, 3, 5, 4, 5, 5, 5, 6, 5, 8);
			resultado = board.movement(jsaDesagrupe);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("DESAGRUPE"));
			
			List<JSONObject> jsaPreste = buildJSA("PREST", 0, 8, 1, 8, 2, 8, 3, 8, 4, 8);
			resultado = board.movement(jsaPreste);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("PRESTE"));
			
			List<JSONObject> jsaPetalo = buildJSA("ETALOS", 0, 9, 0, 10, 0, 11, 0, 12, 0, 13, 0, 14, 0, 15);
			resultado = board.movement(jsaPetalo);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("PETALOS"));
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_DESCONFIARE_DESAGRUPE_PRESTE() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaConfiare = buildJSA("CON", 7, 1, 8, 1, 9, 1);
			resultado = board.movement(jsaConfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesconfiare = buildJSA("DES", 4, 1, 5, 1, 6, 1);
			resultado = board.movement(jsaDesconfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesagrupe = buildJSA("DSAGRUE", 5, 0, 5, 2, 5, 3, 5, 4, 5, 5, 5, 6, 5, 8);
			resultado = board.movement(jsaDesagrupe);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("DESAGRUPE"));
			
			List<JSONObject> jsaPreste = buildJSA("PREST", 0, 8, 1, 8, 2, 8, 3, 8, 4, 8);
			resultado = board.movement(jsaPreste);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("PRESTE"));
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_DESCONFIARE_DESAGRUPE() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaConfiare = buildJSA("CON", 7, 1, 8, 1, 9, 1);
			resultado = board.movement(jsaConfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesconfiare = buildJSA("DES", 4, 1, 5, 1, 6, 1);
			resultado = board.movement(jsaDesconfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesagrupe = buildJSA("DSAGRUE", 5, 0, 5, 2, 5, 3, 5, 4, 5, 5, 5, 6, 5, 8);
			resultado = board.movement(jsaDesagrupe);
			assertTrue(resultado.invalid().isEmpty());
			assertTrue(resultado.accepts("DESAGRUPE"));
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_DESCONFIARE() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("UNADAFORT", 10, 5, 10, 6, 10, 7, 10, 8, 10, 0, 10, 1, 10, 2, 10, 3, 10, 4);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaConfiare = buildJSA("CON", 7, 1, 8, 1, 9, 1);
			resultado = board.movement(jsaConfiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaDesconfiare = buildJSA("DES", 4, 1, 5, 1, 6, 1);
			resultado = board.movement(jsaDesconfiare);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_CONFIARE() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));

			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaConfiare = buildJSA("CON", 7, 1, 8, 1, 9, 1);
			resultado = board.movement(jsaConfiare);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS_FIARE() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaFiare = buildJSA("IARE", 11, 1, 12, 1, 13, 1, 14, 1);
			resultado = board.movement(jsaFiare);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADOS() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.accepts("PACO"));
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.accepts("ASA"));
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.accepts("CAPON"));
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
			
			List<JSONObject> jsaAfortunados = buildJSA("S", 10, 10);
			resultado = board.movement(jsaAfortunados);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO_AFORTUNADO() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAfortunado = buildJSA("AFORTUNAD", 10, 0, 10, 1, 10, 2, 10, 3, 10, 4, 10, 5, 10, 6, 10, 7, 10, 8);
			resultado = board.movement(jsaAfortunado);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON_PELO() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaPelo = buildJSA("ELO", 8, 9, 9, 9, 10, 9);
			resultado = board.movement(jsaPelo);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPACO_ASA_CAPON() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaCapon = buildJSA("PON", 7, 9, 7, 10, 7, 11);
			resultado = board.movement(jsaCapon);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testPACO_ASA() {
		Board board = new Board();
		try {
			List<JSONObject> jsaPaco=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
			ResultadoJugada resultado = board.movement(jsaPaco);
			assertTrue(resultado.invalid().isEmpty());
			List<JSONObject> jsaAsa=buildJSA("ASA", 7, 8, 8, 8, 9, 8);
			resultado = board.movement(jsaAsa);
			assertTrue(resultado.invalid().isEmpty());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testTableroVacio() {
		try {
			{
				Board board = new Board();
				List<JSONObject> jsa=buildJSA("ACPO", 6, 7, 7, 7, 5, 7, 8, 7);
				ResultadoJugada resultado = board.movement(jsa);
				assertTrue(resultado.accepts("PACO"));
			}
			
			{
				Board board = new Board();
				List<JSONObject> jsa=buildJSA("OCAP", 8, 7, 7, 7, 6, 7, 5, 7);
				ResultadoJugada resultado = board.movement(jsa);
				assertTrue(resultado.accepts("PACO"));
			}
			
			{
				Board board = new Board();
				List<JSONObject> jsa=buildJSA("PACO", 5, 7, 6, 7, 7, 7, 8, 7);
				ResultadoJugada resultado = board.movement(jsa);
				assertTrue(resultado.accepts("PACO"));
			}
			
			{
				Board board = new Board();
				List<JSONObject> jsa=buildJSA("PACO", 7, 5, 7, 6, 7, 7, 7, 8);
				ResultadoJugada resultado = board.movement(jsa);
				assertTrue(resultado.accepts("PACO"));
			}
			{
				Board board = new Board();
				List<JSONObject> jsa=buildJSA("ACPO", 7, 6, 7, 7, 7, 5, 7, 8);
				ResultadoJugada resultado = board.movement(jsa);
				assertTrue(resultado.accepts("PACO"));
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	private List<JSONObject> buildJSA(String cadena, int... coords) throws JSONException {
		List<JSONObject> jsa = new ArrayList<JSONObject>();
		for (int i=0; i<cadena.length(); i++) {
			JSONObject jso = new JSONObject();
			jso.put("letter", "" + cadena.charAt(i));
			jso.put("row", coords[2*i]);
			jso.put("col", coords[2*i+1]);
			jsa.add(jso);
		}
		return jsa;
	}

}
