package algoritmos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BellmanFord {
	
	private static final String RUTA_DISTANCES_5 = "././data/distances5.txt/";
	private static final String RUTA_DISTANCES_100 = "././data/distances100.txt/";
	private static final String RUTA_DISTANCES_1000_202110 = "././data/distances1000_202110.txt/";
	private static final String RUTA_DISTANCES_DISCONNECTED ="././data/distancesDisconnected.txt/";
	private static final String RUTA_DISTANCES_100_COSTOSMINIMOS ="././data/distances100_costosminimos.txt/";
	private static final String RUTA_DISTANCES_1000_202110_COSTOSMINIMOS = "././data/distances1000_202110_costosminimos.txt/";
	
	public static void main(String[] args) throws Exception {

		
		BellmanFord instance = new BellmanFord();
		System.out.println("Escoge entre los 6 archivos a leer"+'\n'+"1.Distancia 5"+'\n'+"2.Distancia 100"
		+'\n'+"3.Distancia 1000 202110"+'\n'+"4.Distancias disconnected"+'\n'+"5.Distancia 100 costos minimos"
		+'\n'+"6.Distancia 1000 202110 costos minimos"+'\n'+"Escribir solo el numero a continuacion:");
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
	 * Metodo que se encarga de leer los diferentess archivos  y organizar la matriz para su lectura.
	 * @param NombreArchivo
	 * @throws IOException
	 */
	public void run(String NombreArchivo) throws IOException {
		
		try (FileReader reader = new FileReader(NombreArchivo);
				BufferedReader in = new BufferedReader(reader)) {//leemos el archivo que se nos otorga por parametro
				String line = in.readLine();
				String [] items = line.split("\t");//Vamos separando por filas
				int cant_nodos = items.length;//Cantidad de nodos es la cantidad de filas que haya
				long [][] matriz = new long [cant_nodos][cant_nodos];//Se crea la matriz
				
				for(int i=0;line!=null;i++) {
					items = line.split("\t");
					
					for(int j=0;j<items.length;j++)
						matriz[i][j] = Integer.parseInt(items[j]);
					
					line = in.readLine();
				}
				long startTime = System.currentTimeMillis();
				long [][] matrizCostosMinimos = solucionar(matriz);
				long endTime = System.currentTimeMillis();
				
				System.out.println("Tiempo gastado (milliseconds): "+(endTime-startTime));
				System.out.println("La matriz de costos mínimos para el grafo indicado es:");
				
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
		long[] costos;
		int contador;
		boolean[] actualizados;		
		
		for(int i =0; i<matriz.length;i++) {//Ciclo para cada fila de la matriz

			
			costos = new long[matriz.length];
			actualizados = new boolean[matriz.length];
			actualizados[i] = true;
			Arrays.fill(costos, Long.MAX_VALUE);
			costos[i] = 0;
			
			contador = 0;
			
			while(contador < matriz.length -1 ) {
				
				for(int j = 0; j < matriz.length; j++) {
					for(int k = 0; k < matriz.length; k++) {
						if(matriz[j][k] != -1 && j != k && actualizados[j] && costos[j] + matriz[j][k] < costos[k]) {
							costos[k] = costos[j] + matriz[j][k];
							actualizados[k] = true;
						}
					}
				}
				
				contador++;
			}
						
			matrizCostosMinimos[i] = costos;
		}
		
		return matrizCostosMinimos;
	}
	
}