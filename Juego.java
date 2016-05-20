package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;

public class Juego {
	public static int modoJuego;
	public static int cantJugadores;
	
	public static void ingresoJugadores(Scanner sc){
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
			//creo cpu
			//crear jugador
		}
		else{
			//pregunto cantidad jugadores
			System.out.print("Ingrese cantidad de jugadores: ");
			cantJugadores = sc.nextInt();
			//Creo un vector del tipo Jugadores, que tiene todos los jugadores adentro
			Jugadores[] jugadoresVec = new Jugadores[cantJugadores];
			for (int i = 0; i < jugadoresVec.length; i++) {
				jugadoresVec[i] = new Jugadores(i,sc); 
			}
			//los creo
		}
	}
}
