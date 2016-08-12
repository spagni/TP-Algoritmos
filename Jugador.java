package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;

public class Jugador {
	
	public String nombreJugador;
	public boolean esCpu;
	public int totalPuntos;
	public Puntajes puntajes = new Puntajes();
	
	public Jugador(){
		nombreJugador = "Computadora";
	}
	
	public Jugador(int nJugador,Scanner sc){
		//ingresar datos jugador
		System.out.print("Ingrese el nombre del jugador n°"+ nJugador + ": ");
		nombreJugador = sc.next();
	}
	
	public String getNombreJugador(){
		return this.nombreJugador;
	}
	
	public boolean getEsCpu(){
		return this.esCpu;
	}
	
	public int getTotalPuntos(){
		return this.totalPuntos;
	}
	
	public void setTotalPuntos(int valor){
		this.totalPuntos += valor;
	}
	}