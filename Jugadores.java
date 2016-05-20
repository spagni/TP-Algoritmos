package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;

public class Jugadores {
	
	public Jugadores(int nJugador,Scanner sc){
		//ingresar datos jugador
		System.out.print("Ingrese el nombre del jugador n°"+ nJugador + ": ");
		nombreJugador = sc.next();
		
	}
	
	public static String nombreJugador;
}
