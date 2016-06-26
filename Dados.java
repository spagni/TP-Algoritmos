package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Random;
import java.util.ArrayList;

public class Dados {
	
		private int valor;
		
		public int getValor(){
			return this.valor;
		}

		public void setValor(int valor){
			this.valor = valor;
		}
		
		private boolean usar = true;
		
		public boolean getUsar(){
			return this.usar;
		}

		public void setUsar(boolean usar){
			this.usar = usar;
		}
		
		public void tirar(){
			Random rnd = new Random();
			this.valor = rnd.nextInt(6) + 1;
		}
		
		public void reset(){
			this.usar = true;
		}
		
		public static boolean esServido(ArrayList<Dados> dados){
			
			boolean servido = true;
			
			for(Dados dado : dados){
				if (!dado.getUsar()){
					servido = false;
					break;
				}
			}
			return servido;					
		}
}
