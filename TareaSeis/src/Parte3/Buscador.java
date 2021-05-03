package Parte3;

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
			boolean iConCiclo = false;
			if(marked[i] == 0) {
				components.push(-1);
				Stack<Integer> pila = new Stack<Integer>();
				pila.push(i);
				while(!pila.isEmpty()) {
					int actual = pila.pop();
					if(marked[actual] == 0) {
						components.push(actual);
						marked[actual] = i+1;
						for(int x = 0; x<marked.length; x++) {
							if(Main.grafo[actual][x] > 0) {
								if(marked[x] == 0) {
									pila.push(x);
								}if(marked[x] == i+1 && !iConCiclo) {
									iConCiclo = true;
									components.push(x);
									components.push(-2);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void imprimir() {
		while(!components.isEmpty()) {
			String posibleCiclo = "";
			String posibleRespuesta = "";
			boolean hayCiclo = false;
			boolean cicloNoTerminado = true;
			int finCiclo = -5;
			
			int vertice = components.pop();
			while(vertice!=-1) {
				if(vertice == -2) {
					hayCiclo = true;
					finCiclo = components.pop();
					posibleCiclo = posibleCiclo + finCiclo + " ";
					vertice = components.pop();
				}
				
				posibleRespuesta = posibleRespuesta + vertice + " ";
				
				if(hayCiclo && cicloNoTerminado) {
					posibleCiclo = posibleCiclo + vertice + " ";
					if(finCiclo == vertice) {
						cicloNoTerminado = false;
						while(vertice!=-1) {
							vertice = components.pop();
							if(vertice == -1)
								components.push(vertice);
						}
					}
				}
				
				vertice = components.pop();
			}
			
			if(!hayCiclo) {
				System.out.print("Componente sin ciclo (Orden topologico): ");
				System.out.print(posibleRespuesta);
			}
			else {
				System.out.print("Ciclo: ");
				System.out.print(posibleCiclo);
			}
			System.out.println();
		}
	}
}
