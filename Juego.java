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
	private Scanner sc = new Scanner(System.in);
	
	public void jugar(){		
		//Inicializo y cargo las listas de jugadores y Dados 
		ArrayList<Jugadores> jugadores = this.ingresoJugadores(sc);
		ArrayList<Dados> dados = this.cargarDados();
		
		//Arranca el juego
		while(!hayGanador && contTurnos <= 11){
			System.out.println("Turno N°" + contTurnos);
			System.out.println();
			
			//Recorro todos los jugadores
			for (Jugadores jugador : jugadores){
				System.out.println("Juega " + jugador.getNombreJugador() + "..." + jugador.getEsCpu());
				System.out.println();
				//Me fijo si juega la cpu o un jugador
				if(jugador.getEsCpu() == false){
					//Juega jugador
					this.juegaJugador(jugador, dados);
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
		System.out.println();
		//Creo una lista del tipo Jugadores, que tiene todos los jugadores adentro
		ArrayList<Jugadores> jugadoresList = new ArrayList<Jugadores>();
		for (int i = 0; i < cantJugadores; i++) {
			Jugadores jug = new Jugadores(i,sc);
			jugadoresList.add(jug); 
		}
		return jugadoresList;
	}
	
	public ArrayList<Dados> cargarDados(){
		ArrayList<Dados> dadosList = new ArrayList<Dados>();
		for (int i = 0; i < 5; i++) {//Itero 5 veces porque son 5 dados
			Dados dado = new Dados();
			dadosList.add(dado);
		}
		return dadosList;
	}
	
	public void imprimirArray(ArrayList<Jugadores> array){
		System.out.println("Los jugadores son: ");
		for(int i = 0;i<array.size();i++){
	           System.out.println(" - " + array.get(i).getNombreJugador() +" - "+ array.get(i).getEsCpu());
		}
	}
	
	public void juegaJugador(Jugadores jugador, ArrayList<Dados> dados){
		//Ciclo para que juegue dentro de los tres turnos, o hasta que no quiera tirar mas
		while(this.sigueTirando && this.contTiros <= 3){
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
				sc.reset();
				if (rta.toLowerCase().equals("n")){
					this.sigueTirando = false;
					//Validar puntajes y juegos
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
					if (rta.toLowerCase().equals("n")){
						this.sigueTirando = false;
						//Validar puntajes y juegos
					}
				}
				this.contTiros++;
			}
		}
		this.contTiros = 1;//Reinicio el contador de tiros al tiro 1
		this.sigueTirando = true; //Reinicio el valor de seguir tirando
		this.resetDados(dados);//Reseteo los dados
		this.imprimirSeparador();
	}
	
	public void imprimirSeparador(){
		System.out.println("\n-----------------\n");
	}
	
	public void tirarDados(ArrayList<Dados> dados){
		//Chequeo los dados uno por uno y si estan en true los tiro
		for (Dados dado : dados){
			if (dado.getUsar()){
				dado.tirar();
			}
		}
	}
	
	public void imprimirDados(ArrayList<Dados> dados){
		System.out.println(" _   _   _   _   _");
		for (Dados dado : dados){
			System.out.print("|" + dado.getValor() + "| ");
		}
		System.out.println("\n ¯   ¯   ¯   ¯   ¯\n");
	}
	
	public void seleccionarDados(ArrayList<Dados> dados){
			
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
	
	public void resetDados(ArrayList<Dados> dados){
		for (int i = 0; i < dados.size(); i++) {
			dados.get(i).setUsar(true);
		}
	}
}
