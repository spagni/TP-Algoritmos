package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;
import java.util.ArrayList;

public class Juego {
	private int modoJuego;
	private int cantJugadores;
	private int contTurnos = 1;
	private boolean hayGanador = false;
	private int contTiros = 1;
	private boolean sigueTirando = true;
	
	public void jugar(){
		Scanner sc = new Scanner(System.in);
		
		//Inicializo los jugadores
		ArrayList<Jugadores> jugadores = this.ingresoJugadores(sc);
		
		//Arranca el juego
		while(!hayGanador && contTurnos <= 11){
			System.out.println("Turno Nª" + contTurnos);
			
			//Recorro todos los jugadores
			for (Jugadores jugador : jugadores){
				System.out.println("Juega " + jugador.nombreJugador + "...");
				//Me fijo si juega la cpu o un jugador
				if(jugador.esCpu = false){
					//Juega jugador
				}
				else{
					//Juega Cpu
				}
			}
			//Recorri todos los jugadores, sumo un turno
			contTurnos ++;
		}
		System.out.println("FIN");
		sc.close();
	}
	
	public ArrayList<Jugadores> ingresoJugadores(Scanner sc){
		//Defino la lista de jugadores
		ArrayList<Jugadores> jugadores = new ArrayList<Jugadores>();
		
		System.out.println("Modos de juego:");
		System.out.println("0 - Jugador vs Computadora");
		System.out.println("1 - Jugador vs Jugador");
		System.out.println();
		System.out.print("Elija el modo de juego(0/1): ");
		modoJuego = sc.nextInt();
		while(modoJuego != 0 && modoJuego != 1){
			System.out.print("Opcion incorrecta. Vuelva a elegir(0/1): ");
			modoJuego = sc.nextInt();
		}
		System.out.println();
		//Creo jugadores y computadore(si hace falta)
		if(modoJuego == 0){
			//crear jugadores
			jugadores = creoJugadores(sc);
			//creo cpu
			Jugadores jugadorCpu = new Jugadores();
			jugadores.add(jugadorCpu);
			//Imprimo array
			imprimirArray(jugadores);			
		}
		else{
			//creo jugadores
			jugadores = creoJugadores(sc);
			//Imprimo array
			imprimirArray(jugadores);	
		}
		return jugadores;
	}
	
	public ArrayList<Jugadores> creoJugadores(Scanner sc){
		System.out.print("Ingrese cantidad de jugadores: ");
		cantJugadores = sc.nextInt();
		//Creo una lista del tipo Jugadores, que tiene todos los jugadores adentro
		ArrayList<Jugadores> jugadoresList = new ArrayList<Jugadores>();
		for (int i = 0; i < cantJugadores; i++) {
			Jugadores jug = new Jugadores(i,sc);
			jugadoresList.add(jug); 
		}
		return jugadoresList;
	}
	
	public void imprimirArray(ArrayList<Jugadores> array){
		System.out.println("Los jugadores son: ");
		for(int i = 0;i<array.size();i++){
	           System.out.println(" - " + array.get(i).nombreJugador +" - "+ array.get(i).esCpu);
		}
	}
	public void juegaJugador(Jugadores jugador){
		while(sigueTirando && contTiros <= 3){
			//Tirar dados
		}
	}
}
