import java.util.Scanner;
import java.util.Stack;  
import java.util.Vector;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Stack<Carta> mazo;
    private Stack<Carta> comodines;
    private Vector<Carta> baraja;

    public Juego(){
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();
        this.mazo = new Stack<Carta>();
        this.comodines = new Stack<Carta>();
        this.baraja = new Vector<Carta>();
    }

    private  int numero_aleatorio(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private boolean pertenece(Vector<Integer> nums, int num) {
        boolean pertenece = false;
        for (int i = 0; i < nums.size(); i += 1) {
            if (nums.elementAt(i) == num) {
                pertenece = true;
                break;
            }
        }
        return pertenece;
    }


    public void crear_baraja() {
        for (int i = 1; i < 14; i += 1) {
            baraja.add(new Carta (i, "corazon", "imagenes/"+i+"_of_hearts"));
            baraja.add(new Carta (i, "pica", "imagenes"+i+"_of_spades"));
            baraja.add(new Carta (i, "trebol", "imagenes"+i+"_of_clubs"));
            baraja.add(new Carta (i, "rombo", "imagenes"+i+"_of_diamonds"));
        }
    }

    public void crear_mazo() {
        Vector<Integer> barajadas = new Vector<Integer>();
        int index = 0;
        for (int i = 0; i < 52; i += 1) {
            while (pertenece(barajadas, index = numero_aleatorio(0, 52)));
            barajadas.add(index);
            this.mazo.push(this.baraja.elementAt(index));
        }
        System.out.println(barajadas);
    }

    public void repartir_cartas() {

        for (int j = 0; j < 7; j += 1) {
            this.jugador1.recibir_carta(this.mazo.pop());
            this.jugador2.recibir_carta(this.mazo.pop());
        }
        
    }

    private void sacarCarta(Jugador jugador, int num) {
        Carta c;
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno actual:" + num);
        if (num == 0) {
            c = this.mazo.pop();
            jugador.recibir_carta(c);
            System.out.println("Has recibido: "+ c.get_numero() + " de " + c.get_tipo());
        }
        else {
            System.out.println("\nDe donde deseas comer una carta?\nMazo = 0\nComodines = 1\n");
            int decision = -1;
            decision = sc.nextInt();
            while(decision != 1 && decision != 0){
                System.out.println("Ingrese uno de los valores establecidos ");
                decision = sc.nextInt();
            }
            if (decision == 0) {
                c = this.mazo.pop();
                jugador.recibir_carta(c);
                System.out.println("Has recibido: "+ c.get_numero() + " de " + c.get_tipo());
            }
            else {
                c = this.comodines.pop();
                jugador.recibir_carta(c);
                System.out.println("Has recibido: "+ c.get_numero() + " de " + c.get_tipo());
            }
        }
    }

    public void turno(Jugador jugador, int num) {
        sacarCarta(jugador, num);
    }

    public void obtenerNombres() {
        System.out.println("Indique el nombre del jugador 1: ");
        Scanner sc = new Scanner(System.in);
        String nombre = "";
        nombre = sc.nextLine();
        this.jugador1.setNombre(nombre);
        System.out.println("Indique el nombre del jugador 2: ");
        nombre = sc.nextLine();
        this.jugador2.setNombre(nombre);
    }

    public String escogerPrimerJugador() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique el nombre del jugador que va iniciar: ");
        String nombre = "";
        
        nombre = sc.nextLine();
        
        while(!nombre.equals(this.jugador1.get_nombre()) && !nombre.equals(this.jugador2.get_nombre())){
            System.out.println("Indique un nombre válido: ");
            nombre = sc.nextLine();
        }
        return nombre;
    }

    public void jugar() {
        this.crear_baraja();
        this.crear_mazo();
        this.repartir_cartas();
        //int turno = this.numero_aleatorio(0, 2);
       
        boolean hay_ganador = false;

        this.obtenerNombres();
        String jugadorActual = this.escogerPrimerJugador();
         
        int numDeTurno = 0;

        while (!hay_ganador) {
            if(jugadorActual.equals(this.jugador1.get_nombre())){
                System.out.println("Juega el jugador 1");
                this.turno(this.jugador1, numDeTurno);
                jugadorActual = this.jugador2.get_nombre();
                numDeTurno += 1;
            }
            else if(jugadorActual.equals(this.jugador2.get_nombre())){
                System.out.println("Juega el jugador 2");
                this.turno(this.jugador2, numDeTurno);
                jugadorActual = this.jugador1.get_nombre();
                numDeTurno += 1;
            }
            
        }
        
    }  
}

