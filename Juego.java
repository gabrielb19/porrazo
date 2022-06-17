import java.util.Stack;  
import java.util.Vector;


public class Juego {
    private Vector<Jugador> jugadores;
    private Stack<Carta> pila;
    private Stack<Carta> comodines;
    private Vector<Carta> baraja;

    public Juego(){
        this.jugadores = new Vector<Jugador>();
        this.jugadores.add(new Jugador("Player 1"));
        this.jugadores.add(new Jugador("Player 2"));
        this.pila = new Stack<Carta>();
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
            baraja.add(new Carta (i, "corazon"));
            baraja.add(new Carta (i, "pica"));
            baraja.add(new Carta (i, "trebol"));
            baraja.add(new Carta (i, "rombo"));
        }
    }

    public void crear_pila() {
        Vector<Integer> barajadas = new Vector<Integer>();
        int index = 0;
        for (int i = 0; i < 52; i += 1) {
            while (pertenece(barajadas, index = numero_aleatorio(0, 52)));
            barajadas.add(index);
            this.pila.push(this.baraja.elementAt(index));
        }
        System.out.println(barajadas);
    }

    public void repartir_cartas() {
        for (int i = 0; i < jugadores.size(); i += 1) {
            for (int j = 0; j < 7; j += 1) {
                jugadores.elementAt(i).recibir_carta(this.pila.pop());
            }
        }
    }

    public void turno(Jugador jugador, int num) {
       /*Carta c;
        if (num == 0) {
            c = this.pila.pop();
            jugador.recibir_carta(c);
            System.out.println("Has recibido: "+ c.get_numero() + " | " + c.get_tipo());
        }
        else {
            System.out.println("De donde deseas comer una carta?");
        }*/
    }

    public void jugar() {
       /*  this.crear_baraja();
        this.crear_pila();
        this.repartir_cartas();
        int turno = this.numero_aleatorio(0, 2);
        boolean hay_ganador = false;
        while (!hay_ganador) {

        }*/
    }

}
