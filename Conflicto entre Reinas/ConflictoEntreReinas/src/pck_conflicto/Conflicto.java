package pck_conflicto;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Conflicto {
	private int dimTablero;
	private int cantReinas;
	private int [][] posReina;
	
	public Conflicto(String path){
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));

			this.dimTablero = sc.nextInt();
			this.cantReinas = sc.nextInt();
			
			posReina = new int [this.cantReinas][3];
			for (int i = 0; i < this.posReina.length; i++) {
				this.posReina[i][0] = sc.nextInt();
				this.posReina[i][1] = sc.nextInt();
				this.posReina[i][2] = i+1;
			}
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public List<String> Resolver(){
		int posXbase;
		int posYbase;
		List<String> listaResultado = new ArrayList<String>();
		for (int i = 0; i < posReina.length; i++) {
			String resultado = null;
			posXbase= posReina[i][0];
			posYbase= posReina[i][1];

			Map <String,Integer> reinaMap = new HashMap<String,Integer>();
			reinaMap.clear();
			for (int j = 0; j < posReina.length; j++) {
				if(j != i){
					if(posXbase == posReina[j][0]){//horizontal
						if(posYbase < posReina[j][1])
						{
							if(reinaMap.containsKey("HorizontalIzquierda"))
							{
								if(posReina[j][1] < posReina[(reinaMap.get("HorizontalIzquierda")-1)][1])
									reinaMap.put("HorizontalIzquierda", j+1);
							}
							else
								reinaMap.put("HorizontalIzquierda", j+1);
						}
						else
							if(reinaMap.containsKey("HorizontalDerecha"))
							{
								if(posReina[j][1] > posReina[(reinaMap.get("HorizontalDerecha")-1)][1])
									reinaMap.put("HorizontalDerecha", j+1);
							}
							else
								reinaMap.put("HorizontalDerecha", j+1);
					}
					if(posYbase == posReina[j][1]){ //vertical
						if(posXbase < posReina[j][0])
						{
							if(reinaMap.containsKey("VerticalInferior"))
							{
								if(posReina[j][0] < posReina[(reinaMap.get("VerticalInferior")-1)][0])
									reinaMap.put("VerticalInferior", j+1);
							}
							else
								reinaMap.put("VerticalInferior", j+1);
						}
						else
							if(reinaMap.containsKey("VerticalSuperior"))
							{
								if(posReina[j][0] > posReina[(reinaMap.get("VerticalSuperior")-1)][0])
									reinaMap.put("VerticalSuperior", j+1);
							}
							else
								reinaMap.put("VerticalSuperior", j+1);
					}
					if(Math.abs(posXbase - posReina[j][0]) == Math.abs(posYbase - posReina[j][1])){ //diagonales
						if(posXbase <posReina[j][0] && posYbase < posReina[j][1]) //Superior Izquierda
							if(reinaMap.containsKey("DiagonalSupIzquierda"))
							{
								if(posReina[j][0] < posReina[(reinaMap.get("DiagonalSupIzquierda")-1)][0] && posReina[j][1] < posReina[(reinaMap.get("DiagonalSupIzquierda")-1)][1])
									reinaMap.put("DiagonalSupIzquierda", j+1);
							}
							else
								reinaMap.put("DiagonalSupIzquierda", j+1);
						
						if(posXbase <posReina[j][0] && posYbase > posReina[j][1]) //Superior Derecha
							if(reinaMap.containsKey("DiagonalSupDerecha"))
							{
								if(posReina[j][0] < posReina[(reinaMap.get("DiagonalSupDerecha")-1)][0] && posReina[j][1] > posReina[(reinaMap.get("DiagonalSupDerecha")-1)][1])
									reinaMap.put("DiagonalSupDerecha", j+1);
							}
							else
								reinaMap.put("DiagonalSupDerecha", j+1);
						
						if(posXbase > posReina[j][0] && posYbase < posReina[j][1]) //Inferior Izquierda
							if(reinaMap.containsKey("DiagonalInfIzquierda"))
							{
								if(posReina[j][0] > posReina[(reinaMap.get("DiagonalInfIzquierda")-1)][0] && posReina[j][1] < posReina[(reinaMap.get("DiagonalInfIzquierda")-1)][1])
									reinaMap.put("DiagonalInfIzquierda", j+1);
							}
							else
								reinaMap.put("DiagonalInfIzquierda", j+1);
						
						if(posXbase >posReina[j][0] && posYbase > posReina[j][1]) //Inferior Derecha
							if(reinaMap.containsKey("DiagonalInfDerecha"))
							{
								if(posReina[j][0] > posReina[(reinaMap.get("DiagonalInfDerecha")-1)][0] && posReina[j][1] > posReina[(reinaMap.get("DiagonalInfDerecha")-1)][1])
									reinaMap.put("DiagonalInfDerecha", j+1);
							}
							else
								reinaMap.put("DiagonalInfDerecha", j+1);
					}
					
				}
			}
			reinaMap = MapUtil.sortByValue(reinaMap);
			resultado = Objects.toString(reinaMap.size());
		
			for (String key : reinaMap.keySet()) {
				resultado=resultado + " " + reinaMap.get(key);
			}
			listaResultado.add(resultado);
		}
		return listaResultado;
	}
	
	public void generarSalida(List<String> Lista,String nomArch){
		FileWriter fichero = null;
		try {

			fichero = new FileWriter(nomArch);

			// Escribimos linea a linea en el fichero
			for (String linea : Lista) {
				fichero.write(linea + "\n");
			}

			fichero.close();

		} catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}

}
