package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;

public class Generala {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		//Defino variables
		int contTurno = 1;
		int cantJugadores = 0;
		
		//Inicializo juego
		Juego juego = new Juego();
		//Ingreso jugadores
		juego.ingresoJugadores(sc);
		
				
		sc.close();
	}

}