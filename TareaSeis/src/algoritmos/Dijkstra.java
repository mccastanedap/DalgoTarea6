package algoritmos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
	
	private static final String RUTA_DISTANCES_5 = "././data/distances5.txt/";
	private static final String RUTA_DISTANCES_100 = "././data/distances100.txt/";
	private static final String RUTA_DISTANCES_1000_202110 = "././data/distances1000_202110.txt/";
	private static final String RUTA_DISTANCES_DISCONNECTED ="././data/distancesDisconnected.txt/";
	private static final String RUTA_DISTANCES_100_COSTOSMINIMOS ="././data/distances100_costosminimos.txt/";
	private static final String RUTA_DISTANCES_1000_202110_COSTOSMINIMOS = "././data/distances1000_202110_costosminimos.txt/";
	
	public static void main(String[] args) throws Exception {

		
		Dijkstra instance = new Dijkstra();
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
	 * Hacemos la misma lectura de siempre del archivo.
	 * @param filename
	 * @throws IOException
	 */
	public void run(String filename) throws IOException {
		
		try (FileReader reader = new FileReader(filename);
				BufferedReader in = new BufferedReader(reader)) {
				String line = in.readLine();
				String [] items = line.split("\t");
				int cant_nodos = items.length;
				long [][] matriz = new long [cant_nodos][cant_nodos];
				
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
		boolean[] marcados;
		long[] costos;
		
		PriorityQueue<Nodo> pq = new PriorityQueue<Nodo>();
		
		
		for(int i =0; i<matriz.length;i++) {//Ciclo para cada fila de la matriz

			matrizCostosMinimos[i][i] = 0;
			marcados = new boolean[matriz.length];
			costos = new long[matriz.length];
			Arrays.fill(costos, Long.MAX_VALUE);
			pq.clear();
			costos[i] = 0;
			pq.add(new Nodo(i, 0));
			
			//**Se usa esta variable para saber si la distancia que está en el nodo es la misma que está en el arreglo distancia. De no ser así, es porque en el arreglo
			//distancia fue encontrada una distancia menor, por lo que debe reintentarse el Nodo
			boolean laDistanciaEsCorrecta;
			
			while(!pq.isEmpty()) {
				
				laDistanciaEsCorrecta = false;
				
				while(!laDistanciaEsCorrecta) {
					if(pq.peek().getCosto() == costos[pq.peek().getNumero()])
						laDistanciaEsCorrecta = true;
					else {
						int numero = pq.poll().getNumero();
						pq.add(new Nodo(numero, costos[numero]));
					}
				}
				
				
				Nodo actual = pq.poll();
				
				for(int j = 0; j < matriz.length; j++) {
					if(actual.getNumero() != j && matriz[actual.getNumero()][j] != -1 && !marcados[j] && costos[j] > matriz[actual.getNumero()][j] + costos[actual.getNumero()]) {
						costos[j] = matriz[actual.getNumero()][j] + costos[actual.getNumero()];
						
						Nodo nuevoNodo = new Nodo(j, costos[j]);
						
						if(!pq.contains(nuevoNodo)) {
							pq.add(nuevoNodo);
						}
					}
				}
				
				marcados[actual.getNumero()] = true;
				
			}
			
			matrizCostosMinimos[i] = costos;
		}
		
		return matrizCostosMinimos;
	}
	
	
	/**
	 * Esta clase nos permite representar nodo
	 * @author Usuario
	 *
	 */
		private class Nodo implements Comparable<Dijkstra.Nodo>{
			
			private int numero;
			private long costo;
			
			public Nodo(int numero, long costo) {
				this.numero = numero;
				this.costo = costo;
			}
			
			public int getNumero() {
				return numero;
			}

			public void setNumero(int numero) {
				this.numero = numero;
			}

			public long getCosto() {
				return costo;
			}

			public void setCosto(long costo) {
				this.costo = costo;
			}
			
			@Override
			public int compareTo(Nodo nodo){
				if(this.getCosto() < nodo.getCosto())
					return -1;
				else if(this.getCosto() == nodo.getCosto())
					return 0;
				else
					return 1;
			}
			
			@Override
			public boolean equals(Object other){
				if (other == null) return false;
			    if (other == this) return true;
			    if (!(other instanceof Nodo)) return false;
			    
			    Nodo otherMyClass = (Nodo)other;
				if(this.getNumero() == otherMyClass.getNumero()) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	
}