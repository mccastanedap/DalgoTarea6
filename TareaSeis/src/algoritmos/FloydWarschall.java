package algoritmos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FloydWarschall {
	private static final String RUTA_DISTANCES_5 = "././data/distances5.txt/";
	private static final String RUTA_DISTANCES_100 = "././data/distances100.txt/";
	private static final String RUTA_DISTANCES_1000 = "././data/distances1000.txt/";
	
	public static void main(String[] args) throws Exception {

		
		FloydWarschall instance = new FloydWarschall();
		instance.run(RUTA_DISTANCES_100);
	}

	public void run(String filename) throws IOException {
		
		try (FileReader reader = new FileReader(filename);
				BufferedReader in = new BufferedReader(reader)) {
				String linea = in.readLine();
				String [] items = linea.split("\t");
				int cantidad_nodos = items.length;
				long [][] matriz = new long [cantidad_nodos][cantidad_nodos];
				
				for(int i=0;linea!=null;i++) {
					items = linea.split("\t");
					
					for(int j=0;j<items.length;j++)
						matriz[i][j] = Integer.parseInt(items[j]);
					
					linea = in.readLine();
				}
				
				long [][] matrizCostosMinimos = solucionar(matriz);
				
				System.out.println("La matriz tiene como costos mínimos para el grafo indicado:");
				
				for (long[] row : matrizCostosMinimos)
		            System.out.println(Arrays.toString(row)); 
			}
	}

	public long [][] solucionar(long[][] matriz) {
		
		long[][] matrizCostosMinimos = new long[matriz.length][matriz[0].length];
		boolean[][] matrizActualizados = new boolean[matriz.length][matriz[0].length];
		
		for(int a = 0; a < matriz.length; a++) {
			for(int b = 0; b < matriz.length; b++) {
				if(matriz[a][b] == -1) {
					matrizCostosMinimos[a][b] = Long.MAX_VALUE;
				}
				else {
					matrizCostosMinimos[a][b] = matriz[a][b];
					matrizActualizados[a][b] = true;
				}
			}
		}
		
		for(int k = 0; k < matriz.length; k++) {
			for(int i = 0; i < matriz.length; i++) {
				for(int j = 0; j < matriz.length; j++) {
					if(matrizActualizados[i][k] && matrizActualizados[k][j] && matrizCostosMinimos[i][j] > matrizCostosMinimos[i][k] + matrizCostosMinimos[k][j]) {
						matrizCostosMinimos[i][j] = matrizCostosMinimos[i][k] + matrizCostosMinimos[k][j];
						matrizActualizados[i][j] = true;
					}
				}
			}
		}
		
		
		return matrizCostosMinimos;
	}
	

}
