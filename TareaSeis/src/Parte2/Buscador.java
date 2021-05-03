package Parte2;

import java.util.Stack;

public class Buscador {
	
	private int[] marked;
	
	private Stack<Integer> components;
	
	public Buscador() {
		marked = new int[Main.grafo.length];
		components = new Stack<Integer>();
	}
	
	public void colorear() {
		for(int i = 0; i<marked.length; i+=1) {
			if(marked[i] == 0) {
				components.push(-1);
				Stack<Integer> pila = new Stack<Integer>();
				pila.push(i);
				while(!pila.isEmpty()) {
					int actual = pila.pop();
					components.push(actual);
					if(marked[actual] == 0) {
						marked[actual] = i+1;
						for(int x = 0; x<marked.length; x++) {
							if(Main.grafo[actual][x] >= 0 && marked[x] == 0)
								pila.push(x);
						}
					}
				}
			}
		}
	}
	
	public void imprimir() {
		while(!components.isEmpty()) {
			int vertice = components.pop();
			System.out.print("Componente: ");
			while(vertice!=-1) {
				System.out.print(vertice + " ");
				vertice = components.pop();
			}
			System.out.println();
		}
	}
}
