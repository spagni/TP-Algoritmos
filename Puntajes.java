package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Puntajes {
	
	private boolean servido;
	private HashMap<Integer,Integer> contadorValores;
	private HashMap<String,Boolean> listaPuntajes;
	
	public void validarPuntajes(Jugadores jugador, ArrayList<Dados> dados){
		
		this.servido = Dados.esServido(dados);
		
		this.contarValores(dados);
		this.cargarPuntajesValidos(dados);
		this.imprimirHashMapPuntajes(listaPuntajes);
	}
	
	private void cargarPuntajesValidos(ArrayList<Dados> dados){
		this.listaPuntajes = new HashMap<>();
		//Generala
		if(contadorValores.containsValue(5)){
			this.listaPuntajes.put("generala", true);
		}
		else{
			this.listaPuntajes.put("generala", false);
		}
		//Poker
		if(contadorValores.containsValue(4)){
			this.listaPuntajes.put("poker", true);
		}
		else{
			this.listaPuntajes.put("poker", false);
		}
		//Full
		if(contadorValores.containsValue(3) && contadorValores.containsValue(2)){
			this.listaPuntajes.put("full", true);
		}
		else{
			this.listaPuntajes.put("full", false);
		}
		//Escalera
		if(contadorValores.containsKey(1) && contadorValores.containsKey(2) && contadorValores.containsKey(3) &&
				contadorValores.containsKey(4) && contadorValores.containsKey(5) && contadorValores.containsKey(6)){
			this.listaPuntajes.put("escalera", true);
		}
		else{
			this.listaPuntajes.put("escalera", false);
		}
		//Seis
		if(contadorValores.containsKey(6)){
			this.listaPuntajes.put("seis", true);
		}
		else{
			this.listaPuntajes.put("seis", false);
		}
		//Cinco
		if(contadorValores.containsKey(5)){
			this.listaPuntajes.put("cinco", true);
		}
		else{
			this.listaPuntajes.put("cinco", false);
		}
		//Cuatro
		if(contadorValores.containsKey(4)){
			this.listaPuntajes.put("cuatro", true);
		}
		else{
			this.listaPuntajes.put("cuatro", false);
		}
		//Tres
		if(contadorValores.containsKey(3)){
			this.listaPuntajes.put("tres", true);
		}
		else{
			this.listaPuntajes.put("tres", false);
		}
		//Dos
		if(contadorValores.containsKey(2)){
			this.listaPuntajes.put("dos", true);
		}
		else{
			this.listaPuntajes.put("dos", false);
		}
		//Uno
		if(contadorValores.containsKey(1)){
			this.listaPuntajes.put("uno", true);
		}
		else{
			this.listaPuntajes.put("uno", false);
		}
	}
	
	private void contarValores(ArrayList<Dados> dados){
		contadorValores = new HashMap<>();
		//Cargo el hashMap con los valores que saco, y su cantidad
		for(Dados dado : dados){
			if(contadorValores.containsKey(dado.getValor())){
				//Si existe el key le sumo una unidad al valor 
			contadorValores.put(dado.getValor(), contadorValores.get(dado.getValor())+1);
			}
			else{
				contadorValores.put(dado.getValor(), 1);
			}
		}
	}
	
	private void imprimirHashMapValores(HashMap<Integer,Integer> map){
		Iterator<Integer> it = map.keySet().iterator();
		System.out.println("Valores | Cantidad");
		while(it.hasNext()){
			int clave = it.next();
			System.out.println(clave + " | " + map.get(clave));
		}
	}
	
	private void imprimirHashMapPuntajes(HashMap<String,Boolean> map){
		Iterator<String> it = map.keySet().iterator();
		System.out.println("Puntajes | Tiene");
		while(it.hasNext()){
			String clave = it.next();
			System.out.println(clave + " | " + map.get(clave));
		}
	}
}
