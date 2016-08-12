package ar.edu.davinci.algoritmos.nb.tp.grupo1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Juego {
	
	private int modoJuego;
	private int cantJugadores;
	private int contTurnos = 1;
	public static boolean hayGanador = false;
	private int contTiros = 1;
	private boolean sigueTirando = true;
	private Scanner sc = new Scanner(System.in);
	
	public void jugar(){
		//Inicializo y cargo las listas de jugadores y Dados 
		ArrayList<Jugador> jugadores = this.ingresoJugadores(sc);
		ArrayList<Dado> dados = this.cargarDados();
		
		//Arranca el juego
		while(!hayGanador && contTurnos <= 11){
			System.out.println("Turno N°" + contTurnos + "\n");
			
			//Recorro todos los jugadores
			for (Jugador jugador : jugadores){
				System.out.println("Juega " + jugador.getNombreJugador() + "...");
				System.out.println();
				//Me fijo si juega la cpu o un jugador
				if(jugador.getEsCpu() == false && !hayGanador){
					//Juega jugador
					this.juegaJugador(jugador, dados);
				}
				else{
					if(!hayGanador){
						//Juega Cpu
						this.juegaCpu(jugador, dados);
					}
				}
			}
			//Recorri todos los jugadores, sumo un turno
			contTurnos ++;
		}
		Jugador ganador = this.validarGanador(jugadores);
		System.out.println("¡El ganador del juego es: " + ganador.getNombreJugador() + "! con " + ganador.getTotalPuntos() + " puntos\n");
		System.out.println("FIN");
		Juego.imprimirSeparador();
		sc.close();
	}
	
	public ArrayList<Jugador> ingresoJugadores(Scanner sc){
		//Defino la lista de jugadores
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		
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
			JugadorCpu jugadorCpu = new JugadorCpu();
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
	
	public ArrayList<Jugador> creoJugadores(Scanner sc){
		System.out.print("Ingrese cantidad de jugadores: ");
		cantJugadores = sc.nextInt();
		System.out.println();
		//Creo una lista del tipo Jugadores, que tiene todos los jugadores adentro
		ArrayList<Jugador> jugadoresList = new ArrayList<Jugador>();
		for (int i = 0; i < cantJugadores; i++) {
			JugadorPersona jug = new JugadorPersona(i,sc);
			jugadoresList.add(jug); 
		}
		return jugadoresList;
	}
	
	public ArrayList<Dado> cargarDados(){
		ArrayList<Dado> dadosList = new ArrayList<Dado>();
		for (int i = 0; i < 5; i++) {//Itero 5 veces porque son 5 dados
			Dado dado = new Dado();
			dadosList.add(dado);
		}
		return dadosList;
	}
	
	public void imprimirArray(ArrayList<Jugador> array){
		System.out.println();
		System.out.println("Los jugadores son: ");
		for(int i = 0;i<array.size();i++){
	           System.out.println(" - " + array.get(i).getNombreJugador());
		}
		System.out.println();
	}
	
	public void juegaJugador(Jugador jugador, ArrayList<Dado> dados){
		//Ciclo para que juegue dentro de los tres turnos, o hasta que no quiera tirar mas
		while(this.sigueTirando && this.contTiros <= 3 && !hayGanador){
			//Tirar dados
			String rta;
			System.out.println("--> Tiro " + this.contTiros);
			if(this.contTiros == 1){
				this.tirarDados(dados);
				this.imprimirDados(dados);
				//Validar generala servida
				//Valido si sigue tirando o no
				System.out.print("Quiere seguir tirando? (S/N): ");
				rta = sc.next();
				System.out.println();
				sc.reset();
				if (rta.toLowerCase().equals("n")){
					this.sigueTirando = false;
					//Validar puntajes y juegos
					jugador.puntajes.validarPuntajes(jugador, dados);
				}
				this.contTiros++;
			}
			else{
				this.seleccionarDados(dados);
				this.tirarDados(dados);
				this.imprimirDados(dados);
				//Validar generala servida
				//Valido si sigue tirando o no
				if (this.contTiros != 3){
					System.out.print("Quiere seguir tirando? (S/N): ");
					rta = sc.next();
					System.out.println();
					if (rta.toLowerCase().equals("n")){
						this.sigueTirando = false;
						//Validar puntajes y juegos
						jugador.puntajes.validarPuntajes(jugador, dados);
					}
				}
				else{
					jugador.puntajes.validarPuntajes(jugador, dados);
				}
				this.contTiros++;
			}
		}
		System.out.println(jugador.getNombreJugador() + " suma " + jugador.getTotalPuntos());
		this.contTiros = 1;//Reinicio el contador de tiros al tiro 1
		this.sigueTirando = true; //Reinicio el valor de seguir tirando
		this.resetDados(dados);//Reseteo los dados
		imprimirSeparador();
	}
	
	public void juegaCpu(Jugador jugador, ArrayList<Dado> dados){
		//Ciclo para que juegue dentro de los tres turnos, o hasta que no quiera tirar mas
		while(this.sigueTirando && this.contTiros <= 3 && !hayGanador){
			System.out.println("--> Tiro " + this.contTiros);
			if(this.contTiros == 1){
				this.tirarDados(dados);
				this.imprimirDados(dados);
				this.setSigueTirando(jugador, dados);
				if(!this.sigueTirando){
					//validar
					jugador.puntajes.validarPuntajesCpu(jugador, dados);
				}
				this.contTiros++;
			}
			else{
				this.buscarValores(jugador, dados);
				this.tirarDados(dados);
				this.imprimirDados(dados);
				this.setSigueTirando(jugador, dados);
				if(!this.sigueTirando || this.contTiros == 3){
					//validar
					jugador.puntajes.validarPuntajesCpu(jugador, dados);
				}
				this.contTiros++;
			}
		}
		System.out.println(jugador.getNombreJugador() + " suma " + jugador.getTotalPuntos());
		this.contTiros = 1;//Reinicio el contador de tiros al tiro 1
		this.sigueTirando = true; //Reinicio el valor de seguir tirando
		this.resetDados(dados);//Reseteo los dados
		imprimirSeparador();
	}
	
	private void setSigueTirando(Jugador jugador, ArrayList<Dado> dados){
		//Armo el hashMap con la cantidad de veces que salio cada valor
		jugador.puntajes.contarValores(dados);
		if(jugador.puntajes.contadorValores.containsValue(5)){
			this.sigueTirando = false;
		}
		if(jugador.puntajes.contadorValores.containsValue(4)){
			this.sigueTirando = false;
		}
		if(jugador.puntajes.contadorValores.containsValue(3) && jugador.puntajes.contadorValores.containsValue(2)){
			this.sigueTirando = false;
		}
		if(jugador.puntajes.contadorValores.containsKey(1) && jugador.puntajes.contadorValores.containsKey(2) && 
				jugador.puntajes.contadorValores.containsKey(3) && jugador.puntajes.contadorValores.containsKey(4) && 
				jugador.puntajes.contadorValores.containsKey(5) && jugador.puntajes.contadorValores.containsKey(6)){
			this.sigueTirando = false;
		}		
	}
	
	private void buscarValores(Jugador jugador, ArrayList<Dado> dados){
		//Armo el hashMap con la cantidad de veces que salio cada valor
		jugador.puntajes.contarValores(dados);
		Iterator<Integer> it = jugador.puntajes.contadorValores.keySet().iterator();
		while(it.hasNext()){
			int clave = it.next();
			if((jugador.puntajes.contadorValores.get(clave) == 2) || (jugador.puntajes.contadorValores.get(clave) == 3)){
				for(Dado dado : dados){
					if(dado.getValor() == clave){
						dado.setUsar(false);
					}
				}
			}
		}
	}
	
	public void tirarDados(ArrayList<Dado> dados){
		//Chequeo los dados uno por uno y si estan en true los tiro
		for (Dado dado : dados){
			if (dado.getUsar()){
				dado.tirar();
			}
		}
	}
	
	public void imprimirDados(ArrayList<Dado> dados){
		System.out.println(" _   _   _   _   _");
		for (Dado dado : dados){
			System.out.print("|" + dado.getValor() + "| ");
		}
		System.out.println("\n ¯   ¯   ¯   ¯   ¯\n");
	}
	
	public void seleccionarDados(ArrayList<Dado> dados){
			
		System.out.println("Ingrese los dados que quiere guardar(0/1/2/3/4) separados por coma. Ingrese 9 para volver a tirar los 5 dados: ");
		String dadosATirar = sc.next();
		String[] dadoATirar = dadosATirar.split(",");
		//Recorro el vector y cambio el valor de dado.usar
		if (Integer.parseInt(dadoATirar[0]) != 9){
			for (int i = 0; i < dadoATirar.length; i++) {
				dados.get(Integer.parseInt(dadoATirar[i])).setUsar(false);
			}
		}
		else{
			this.resetDados(dados);
		}
	}
	
	public void resetDados(ArrayList<Dado> dados){
		for (int i = 0; i < dados.size(); i++) {
			dados.get(i).setUsar(true);
		}
	}
	
	public Jugador validarGanador(ArrayList<Jugador> jugadores){
		Jugador ganador = jugadores.get(0);
		
		for (Jugador j : jugadores){
			if (j.getTotalPuntos() > ganador.getTotalPuntos()){
				ganador = j;
			}
		}
		return ganador;
	}
	
	public static void generalaServida(Jugador jugador){
		Juego.hayGanador = true;
		System.out.println("\n" + jugador.getNombreJugador() + " sacó generala sevida. ¡¡Gana el juego!!\n");
	}
	
	public static void imprimirSeparador(){
		System.out.println("\n-----------------\n");
	}
}
