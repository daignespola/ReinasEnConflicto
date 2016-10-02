package pck_conflicto;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class ConflictoTest {
	@Test
	public void CasoDePrueba1() {
		Conflicto con = new Conflicto("Prueba.in");
		List<String> salida = con.Resolver();
		con.generarSalida(salida,"Salida.out");
		Scanner sc = null;
		Scanner test = null;
		try {
			sc = new Scanner(new File("Salida.out"));
			test = new Scanner(new File("SalidaTest.out"));
			
			Assert.assertTrue(sc.nextInt() == test.nextInt());
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
}
