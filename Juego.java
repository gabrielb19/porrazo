import java.util.Stack;  
import java.util.Vector;


public class Juego {
    private Vector<Jugador> jugadores;
    private Stack<Carta> pila;
    private Stack<Carta> comodines;
    private Vector<Carta> baraja;

    public Juego(){
        this.jugadores = new Vector<Jugador>();
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
            baraja.add(new Carta (i, "espada"));
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

}
