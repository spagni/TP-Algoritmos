package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Random;

public class Dados {
	
		private int valor;
		
		public int getValor(){
			return this.valor;
		}

		public void setValor(int valor){
			this.valor = valor;
		}
		
		public void tirar(){
			Random rnd = new Random();
			this.valor = rnd.nextInt(6) + 1;
		}
}
