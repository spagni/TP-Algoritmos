package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Puntajes {
	
	public boolean generalaDoble = false;
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
	
	public void validarPuntajes(Jugador jugador, ArrayList<Dado> dados){
		
		this.tiroServido = Dado.esServido(dados);
		//Cargo el hashMap con las cantidades de valores
		this.contarValores(dados);
		//Cargo el hashMap con los puntajes permitidos
		this.cargarPuntajesValidos(dados);
		//Muestro los posibles puntajes  y le pido que seleccione cual tachar
		this.tacharPuntajes(jugador);
		
	}
	
	public void validarPuntajesCpu(Jugador jugador, ArrayList<Dado> dados){
		
		this.tiroServido = Dado.esServido(dados);
		//Cargo el hashMap con las cantidades de valores
		this.contarValores(dados);
		//Cargo el hashMap con los puntajes permitidos
		this.cargarPuntajesValidos(dados);
		
		this.tacharPuntajesCpu(jugador);
	}
	
	private void cargarPuntajesValidos(ArrayList<Dado> dados){
		//Carga un hashMap con los juegos posibles en true
		this.listaPuntajes = new HashMap<>();
		//Generala
		if(contadorValores.containsValue(5) && !generala){
			//Si ya saco generala agrego al mapa la generalaDoble en true
			if(listaPuntajes.get("generala") == true){
				this.listaPuntajes.put("generalaDoble", true);
			}
			this.listaPuntajes.put("generala", true);
		}
		else{
			this.listaPuntajes.put("generala", false);
			this.listaPuntajes.put("generalaDoble", false);
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
		if(((contadorValores.containsKey(1) && contadorValores.containsKey(2) && contadorValores.containsKey(3) &&
				contadorValores.containsKey(4) && contadorValores.containsKey(5)) 
				|| (contadorValores.containsKey(2) && contadorValores.containsKey(3) && contadorValores.containsKey(4) &&
						contadorValores.containsKey(5) && contadorValores.containsKey(6))) && !escalera){
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
	
	public void contarValores(ArrayList<Dado> dados){
		contadorValores = new HashMap<>();
		//Cargo un hashMap con los valores que salieron en los dados + la cantidad de veces que salieron en la tirada
		for(Dado dado : dados){
			if(contadorValores.containsKey(dado.getValor())){
				//Si existe el key le sumo una unidad al valor 
			contadorValores.put(dado.getValor(), contadorValores.get(dado.getValor())+1);
			}
			else{
				contadorValores.put(dado.getValor(), 1);
			}
		}
	}
	
	private void tacharPuntajes(Jugador jugador){
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
		System.out.print("Ingrese el nombre del juego que desea anotar (tal como aparece en pantalla): ");
		tachar = sc.nextLine();
		//Busco el juego, lo tacho y le asigno los puntos al jugador
		buscarTacharJuego(jugador,tachar, sumarPuntos);
		Juego.imprimirSeparador();
	}
	
	private void tacharPuntajesCpu(Jugador jugador){
		boolean tieneJuego = false;
		if(listaPuntajes.get("generala") == true && !generala){
			if(generala && !generalaDoble){
				generalaDoble = true;
				sumarPuntosJuegos(jugador,100);
				System.out.println("La computadora anota generala Doble\n");
				tieneJuego = true;
				return;
			}
			generala = true;
			sumarPuntosJuegos(jugador,50);
			System.out.println("La computadora anota generala\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("poker") == true && !poker){
			poker = true;
			sumarPuntosJuegos(jugador,40);
			System.out.println("La computadora anota poker\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("full") == true && !full){
			full = true;
			sumarPuntosJuegos(jugador,30);
			System.out.println("La computadora anota full\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("escalera") == true && !escalera){
			escalera = true;
			sumarPuntosJuegos(jugador,20);
			System.out.println("La computadora anota escalera\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("seis") == true && !seis){
			seis = true;
			sumarPuntosChicos(jugador,6);
			System.out.println("La computadora anota seis\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("cinco") == true && !cinco){
			cinco = true;
			sumarPuntosChicos(jugador,5);
			System.out.println("La computadora anota cinco\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("cuatro") == true && !cuatro){
			cuatro = true;
			sumarPuntosChicos(jugador,4);
			System.out.println("La computadora anota cuatro\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("tres") == true && !tres){
			tres = true;
			sumarPuntosChicos(jugador,3);
			System.out.println("La computadora anota tres\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("dos") == true && !dos){
			dos = true;
			sumarPuntosChicos(jugador,2);
			System.out.println("La computadora anota dos\n");
			tieneJuego = true;
			return;
		}
		if(listaPuntajes.get("uno") == true && !uno){
			uno = true;
			sumarPuntosChicos(jugador,1);
			System.out.println("La computadora anota uno\n");
			tieneJuego = true;
			return;
		}
		//Si no tiene juegos para anotar, lo hago tachar por jerarquia
		if (!tieneJuego){
			if(!uno){
				this.uno = true;
				System.out.println("La computadora tacha uno y no suma puntos\n");
				return;
			}
			if(!dos){
				this.dos = true;
				System.out.println("La computadora tacha dos y no suma puntos\n");
				return;
			}
			if(!tres){
				this.tres = true;
				System.out.println("La computadora tacha tres y no suma puntos\n");
				return;
			}
			if(!cuatro){
				this.cuatro = true;
				System.out.println("La computadora tacha cuatro y no suma puntos\n");
				return;
			}
			if(!cinco){
				this.cinco = true;
				System.out.println("La computadora tacha cinco y no suma puntos\n");
				return;
			}
			if(!seis){
				this.seis = true;
				System.out.println("La computadora tacha seis y no suma puntos\n");
				return;
			}
			if(!escalera){
				this.escalera = true;
				System.out.println("La computadora tacha escalera y no suma puntos\n");
				return;
			}
			if(!full){
				this.full = true;
				System.out.println("La computadora tacha full y no suma puntos\n");
				return;
			}
			if(!poker){
				this.poker = true;
				System.out.println("La computadora tacha poker y no suma puntos\n");
				return;
			}
			if(!generala){
				this.generala = true;
				System.out.println("La computadora tacha generala y no suma puntos\n");
				return;
			}
			if(!generalaDoble){
				this.generalaDoble = true;
				System.out.println("La computadora tacha generala doble y no suma puntos\n");
				return;
			}
		}
	}
	
	private void buscarTacharJuego(Jugador j, String nombre, boolean sumar){
		//Busco el juegio seleccionado por el usuario y lo tacho
		switch(nombre){
			case "generala doble":
				this.generalaDoble = true;
				if(sumar){
					sumarPuntosJuegos(j,100);
				}
				break;
			case "generala":
				this.generala = true;
				if(!this.tiroServido){
					if(sumar){
						sumarPuntosJuegos(j,50);
					}
				}
				else{
					Juego.generalaServida(j);
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
	
	private void sumarPuntosJuegos(Jugador jugador, int valor){
		if(tiroServido){
			jugador.setTotalPuntos(valor+5);
		}
		else{
			jugador.setTotalPuntos(valor);
		}
	}
	
	private void sumarPuntosChicos(Jugador jugador, int valor){

		jugador.setTotalPuntos(contadorValores.get(valor) * valor);
	}
	
	private void mostrarPuntajesFaltantes(){
		int i = 0;
		if (!generalaDoble) {
			System.out.println(i + " - generala doble" );
			i++;
		}
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
}
