package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;
import java.util.ArrayList;

public class Jugadores {
	
	public Jugadores(){
		nombreJugador = "Computadora";
		esCpu = true;
	}
	
	public Jugadores(int nJugador,Scanner sc){
		//ingresar datos jugador
		System.out.print("Ingrese el nombre del jugador n°"+ nJugador + ": ");
		nombreJugador = sc.next();	
		esCpu = false;
	}
	
	public String nombreJugador;
	public int totalPuntos;
	public boolean esCpu;
}
