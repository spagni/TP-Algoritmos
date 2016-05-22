package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;
import java.util.ArrayList;

public class Generala {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//Inicializo juego
		Juego juego = new Juego();
		//Ingreso jugadores
		ArrayList<Jugadores> jugadores = juego.ingresoJugadores(sc);
				
		sc.close();
	}

}
