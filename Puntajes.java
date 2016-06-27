package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Puntajes {
	
	public boolean generala = false;
	public boolean poker = false;
	public boolean full = false;
	public boolean escalera = false;
	public boolean seis = false;
	public boolean cinco = false;
	public boolean cuatro = false;
	public boolean tres = false;
	public boolean dos = false;
	public boolean uno = false;
	private boolean tiroServido;
	public HashMap<Integer,Integer> contadorValores;
	private HashMap<String,Boolean> listaPuntajes;
	private Scanner sc = new Scanner(System.in);
	
	public void validarPuntajes(Jugadores jugador, ArrayList<Dados> dados){
		
		this.tiroServido = Dados.esServido(dados);
		//Cargo el hashMap con las cantidades de valores
		this.contarValores(dados);
		//Cargo el hashMap con los puntajes permitidos
		this.cargarPuntajesValidos(dados);
		//Muestro los posibles puntajes  y le pido que seleccione cual tachar
		this.tacharPuntajes(jugador);
		
	}
	
	public void validarPuntajesCpu(Jugadores jugador, ArrayList<Dados> dados){
		
		this.tiroServido = Dados.esServido(dados);
		//Cargo el hashMap con las cantidades de valores
		this.contarValores(dados);
		//Cargo el hashMap con los puntajes permitidos
		this.cargarPuntajesValidos(dados);
		
		this.tacharPuntajesCpu(jugador);
	}
	
	private void cargarPuntajesValidos(ArrayList<Dados> dados){
		this.listaPuntajes = new HashMap<>();
		//Generala
		if(contadorValores.containsValue(5) && !generala){
			this.listaPuntajes.put("generala", true);
		}
		else{
			this.listaPuntajes.put("generala", false);
		}
		//Poker
		if(contadorValores.containsValue(4) && !poker){
			this.listaPuntajes.put("poker", true);
		}
		else{
			this.listaPuntajes.put("poker", false);
		}
		//Full
		if(contadorValores.containsValue(3) && contadorValores.containsValue(2) && !full){
			this.listaPuntajes.put("full", true);
		}
		else{
			this.listaPuntajes.put("full", false);
		}
		//Escalera
		if(contadorValores.containsKey(1) && contadorValores.containsKey(2) && contadorValores.containsKey(3) &&
				contadorValores.containsKey(4) && contadorValores.containsKey(5) && contadorValores.containsKey(6) && !escalera){
			this.listaPuntajes.put("escalera", true);
		}
		else{
			this.listaPuntajes.put("escalera", false);
		}
		//Seis
		if(contadorValores.containsKey(6) && !seis){
			this.listaPuntajes.put("seis", true);
		}
		else{
			this.listaPuntajes.put("seis", false);
		}
		//Cinco
		if(contadorValores.containsKey(5) && !cinco){
			this.listaPuntajes.put("cinco", true);
		}
		else{
			this.listaPuntajes.put("cinco", false);
		}
		//Cuatro
		if(contadorValores.containsKey(4) && !cuatro){
			this.listaPuntajes.put("cuatro", true);
		}
		else{
			this.listaPuntajes.put("cuatro", false);
		}
		//Tres
		if(contadorValores.containsKey(3) && !tres){
			this.listaPuntajes.put("tres", true);
		}
		else{
			this.listaPuntajes.put("tres", false);
		}
		//Dos
		if(contadorValores.containsKey(2) && !dos){
			this.listaPuntajes.put("dos", true);
		}
		else{
			this.listaPuntajes.put("dos", false);
		}
		//Uno
		if(contadorValores.containsKey(1) && !uno){
			this.listaPuntajes.put("uno", true);
		}
		else{
			this.listaPuntajes.put("uno", false);
		}
	}
	
	public void contarValores(ArrayList<Dados> dados){
		contadorValores = new HashMap<>();
		//Cargo un hashMap con los valores que salieron en los dados + la cantidad de veces que salieron en la tirada
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
	
	private void tacharPuntajes(Jugadores jugador){
		String tachar;
		boolean sumarPuntos = true;
		//Muestro los puntajes
		System.out.println("Sus posibles juegos a tachar son: ");
		Iterator<String> it = listaPuntajes.keySet().iterator();
		int i = 0;
		boolean tieneJuegos = false;
		while(it.hasNext()){
			String clave = it.next();
			if(listaPuntajes.get(clave) == true){
				System.out.println(i + " - " + clave);
				i++;
				tieneJuegos = true;
			}
		}
		//Si no tiene juegos posibles, que elija alguno para tachar
		if(!tieneJuegos){
			System.out.println("* No tiene juegos posibles, por favor tache alguno de los siguienes:");
			mostrarPuntajesFaltantes();
			sumarPuntos = false;			
		}
		System.out.println();
		//Seleccionar puntaje
		System.out.print("Ingrese el nombre del juego que desea tachar (tal como aparece en pantalla): ");
		tachar = sc.nextLine();
		//Busco el juego, lo tacho y le asigno los puntos al jugador
		buscarTacharJuego(jugador,tachar, sumarPuntos);
		Juego.imprimirSeparador();
	}
	
	private void tacharPuntajesCpu(Jugadores jugador){
		Iterator<String> it = listaPuntajes.keySet().iterator();
				
		while(it.hasNext()){
			String clave = it.next();
			if(listaPuntajes.get(clave) == true){
				if(clave == "generala" && !generala){
					sumarPuntosJuegos(jugador,50);
				}
				if(clave == "poker" && !poker){
					sumarPuntosJuegos(jugador,40);
				}
				if(clave == "full" && !full){
					sumarPuntosJuegos(jugador,30);
				}
				if(clave == "escalera" && !escalera){
					sumarPuntosJuegos(jugador,20);
				}
				if(clave == "seis" && !seis){
					sumarPuntosChicos(jugador,6);
				}
				if(clave == "cinco" && !cinco){
					sumarPuntosChicos(jugador,5);
				}
				if(clave == "cuatro" && !cuatro){
					sumarPuntosChicos(jugador,4);
				}
				if(clave == "tres" && !tres){
					sumarPuntosChicos(jugador,3);
				}
				if(clave == "dos" && !dos){
					sumarPuntosChicos(jugador,2);
				}
				if(clave == "uno" && !uno){
					sumarPuntosChicos(jugador,1);
				}
			}
		}
	}
	
	private void buscarTacharJuego(Jugadores j, String nombre, boolean sumar){
		//Busco el juegio seleccionado por el usuario y lo tacho
		switch(nombre){
			case "generala":
				this.generala = true;
				if(sumar){
					sumarPuntosJuegos(j,50);
				}
				break;
			case "poker":
				this.poker = true;
				if(sumar){
					sumarPuntosJuegos(j,40);
				}
				break;
			case "full":
				this.full = true;
				if(sumar){
					sumarPuntosJuegos(j,30);
				}
				break;
			case "escalera":
				this.escalera = true;
				if(sumar){
					sumarPuntosJuegos(j,20);
				}
				break;
			case "seis":
				this.seis = true;
				if(sumar){
					sumarPuntosChicos(j,6);
				}
				break;
			case "cinco":
				this.cinco = true;
				if(sumar){
					sumarPuntosChicos(j,5);
				}
				break;
			case "cuatro":
				this.cuatro = true;
				if(sumar){
					sumarPuntosChicos(j,4);
				}
				break;
			case "tres":
				this.tres = true;
				if(sumar){
					sumarPuntosChicos(j,3);
				}
				break;
			case "dos":
				this.dos = true;
				if(sumar){
					sumarPuntosChicos(j,2);
				}
				break;
			case "uno":
				this.uno = true;
				if(sumar){
					sumarPuntosChicos(j,1);
				}
				break;
		}			
	}
	
	private void sumarPuntosJuegos(Jugadores jugador, int valor){
		if(tiroServido){
			jugador.setTotalPuntos(valor+5);
		}
		else{
			jugador.setTotalPuntos(valor);
		}
	}
	
	private void sumarPuntosChicos(Jugadores jugador, int valor){

		jugador.setTotalPuntos(contadorValores.get(valor) * valor);
	}
	
	private void mostrarPuntajesFaltantes(){
		int i = 0;
		if (!generala) {
			System.out.println(i + " - generala" );
			i++;
		}
		if (!poker) {
			System.out.println(i + " - poker" );
			i++;
		}
		if (!full) {
			System.out.println(i + " - full" );
			i++;
		}
		if (!escalera) {
			System.out.println(i + " - escalera" );
			i++;
		}
		if (!seis) {
			System.out.println(i + " - seis" );
			i++;
		}
		if (!cinco) {
			System.out.println(i + " - cinco" );
			i++;
		}
		if (!cuatro) {
			System.out.println(i + " - cuatro" );
			i++;
		}
		if (!tres) {
			System.out.println(i + " - tres" );
			i++;
		}
		if (!dos) {
			System.out.println(i + " - dos" );
			i++;
		}
		if (!uno) {
			System.out.println(i + " - uno" );
			i++;
		}
	}
	
	public static void imprimirHashMapValores(HashMap<Integer,Integer> map){
		Iterator<Integer> it = map.keySet().iterator();
		System.out.println("Valores | Cantidad");
		while(it.hasNext()){
			int clave = it.next();
			System.out.println(clave + " | " + map.get(clave));
		}
	}
	
	public static void imprimirHashMapPuntajes(HashMap<String,Boolean> map){
		Iterator<String> it = map.keySet().iterator();
		System.out.println("Puntajes | Tiene");
		while(it.hasNext()){
			String clave = it.next();
			System.out.println(clave + " | " + map.get(clave));
		}
	}
}
