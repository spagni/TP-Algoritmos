package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;

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
	
	private String nombreJugador;
	public String getNombreJugador(){
		return this.nombreJugador;
	}
	
	private boolean esCpu;
	public boolean getEsCpu(){
		return this.esCpu;
	}
	
	public int totalPuntos;
	//Definir todos los puntajes
}
