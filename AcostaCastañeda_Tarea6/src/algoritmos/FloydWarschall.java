package algoritmos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FloydWarschall {
	private static final String RUTA_DISTANCES_5 = "././data/distances5.txt/";
	private static final String RUTA_DISTANCES_100 = "././data/distances100.txt/";
	private static final String RUTA_DISTANCES_1000_202110 = "././data/distances1000_202110.txt/";
	private static final String RUTA_DISTANCES_DISCONNECTED ="././data/distancesDisconnected.txt/";
	private static final String RUTA_DISTANCES_100_COSTOSMINIMOS ="././data/distances100_costosminimos.txt/";
	private static final String RUTA_DISTANCES_1000_202110_COSTOSMINIMOS = "././data/distances1000_202110_costosminimos.txt/";
	
	public static void main(String[] args) throws Exception {

		System.out.println("Escoge entre los 6 archivos a leer"+'\n'+"1.Distancia 5"+'\n'+"2.Distancia 100"
				+'\n'+"3.Distancia 1000 202110"+'\n'+"4.Distancias disconnected"+'\n'+"5.Distancia 100 costos minimos"
				+'\n'+"6.Distancia 1000 202110 costos minimos"+'\n'+"Escribir solo el numero a continuacion:");
		FloydWarschall instance = new FloydWarschall();
		// Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        // Reading data using readLine
        String numero = reader.readLine();
        int Numero =Integer.parseInt(numero);
        if(Numero ==1) {
        	instance.run(RUTA_DISTANCES_5);
        }
        if(Numero ==2) {
        	instance.run(RUTA_DISTANCES_100);
        }
        if(Numero ==3) {
        	instance.run(RUTA_DISTANCES_1000_202110);
        }
        if(Numero ==4) {
        	instance.run(RUTA_DISTANCES_DISCONNECTED);
        }
        if(Numero ==5) {
        	instance.run(RUTA_DISTANCES_100_COSTOSMINIMOS);
        }
        if(Numero ==6) {
        	instance.run(RUTA_DISTANCES_1000_202110_COSTOSMINIMOS);
        }
	}
	
	/**
	 * Leemos el archivo
	 * @param filename
	 * @throws IOException
	 */
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
				long startTime = System.currentTimeMillis();
				long [][] matrizCostosMinimos = solucionar(matriz);
				long endTime = System.currentTimeMillis();
				System.out.println("Tiempo gastado (milliseconds): "+(endTime-startTime));
				System.out.println("La matriz tiene como costos mínimos para el grafo indicado:");
				
				for (long[] row : matrizCostosMinimos)
		            System.out.println(Arrays.toString(row)); 
			}
	}
	/**
	 * Calcular los costos minimos de cada uno de los archivos
	 * @param matriz
	 * @return
	 */
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
