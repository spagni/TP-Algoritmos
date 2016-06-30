package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;

public class Jugador {
	
	private String nombreJugador;
	private boolean esCpu;
	private int totalPuntos;
	public Puntajes puntajes = new Puntajes();
	
	public Jugador(){
		nombreJugador = "Computadora";
		esCpu = true;
	}
	
	public Jugador(int nJugador,Scanner sc){
		//ingresar datos jugador
		System.out.print("Ingrese el nombre del jugador n°"+ nJugador + ": ");
		nombreJugador = sc.next();	
		esCpu = false;
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