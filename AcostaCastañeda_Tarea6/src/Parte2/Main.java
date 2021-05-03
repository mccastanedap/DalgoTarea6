package Parte2;

import java.io.*;

public class Main {
	
	public static int[][] grafo;
	
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("data/" + args[0]);
			BufferedReader reader = new BufferedReader(fr);
			
			int lineNumber = 0;
			String linea = reader.readLine();
			
			while (linea != null) {
				String[] aristas = linea.split("\t");
				if (lineNumber == 0) 
					grafo = new int[aristas.length][aristas.length];
				for(int i = 0; i<aristas.length; i++) {
					grafo[lineNumber][i] = Integer.parseInt(aristas[i]);
				}
				lineNumber += 1;
				linea = reader.readLine();
			}
			reader.close();
			
			Buscador finder = new Buscador();
			finder.colorear();
			finder.imprimir();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
