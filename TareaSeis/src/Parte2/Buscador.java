package Parte2;

import java.util.LinkedList;
import java.util.Queue;

public class Buscador {
	
	private int[] marked;
	
	private Queue<Integer> components;
	
	public Buscador() {
		marked = new int[Main.grafo.length];
		components = new LinkedList<Integer>();
	}
	
	public void colorear() {
		for(int i = 0; i<marked.length; i+=1) {
			if(marked[i] == 0) {
				Queue<Integer> pila = new LinkedList<Integer>();
				pila.add(i);
				while(!pila.isEmpty()) {
					int actual = pila.remove();
					if(marked[actual] == 0) {
						components.add(actual);
						marked[actual] = i+1;
						for(int x = 0; x<marked.length; x++) {
							if(Main.grafo[actual][x] >= 0 && marked[x] == 0)
								pila.add(x);
						}
					}
				}
				components.add(-1);
			}
		}
	}
	
	public void imprimir() {
		while(components.size() > 0) {
			int vertice = components.remove();
			System.out.print("Componente: ");
			while(vertice!=-1) {
				System.out.print(vertice + " ");
				if(components.size() > 0)
					vertice = components.remove();
				else
					break;
			}
			System.out.println();
		}
	}
}
