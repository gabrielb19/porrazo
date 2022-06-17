import java.util.Vector;
import java.util.Stack;


public class Jugador {
    private Vector<Carta> cartas;
    private String nombre;

    public Jugador() {
        this.nombre = "";
        this.cartas = new Vector<Carta>();
    }

    public Jugador(String nombre){
        this.nombre = nombre;
        this.cartas = new Vector<Carta>();
    }

    public void recibir_carta(Carta c){
        this.cartas.add(c);
    }

    public Carta desechar_carta(int index) {
        return this.cartas.elementAt(index);
    }

    public String get_nombre() {
        return this.nombre;
    }

    public Vector<Carta> get_cartas() {
        return this.cartas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void ver_cartas() {
        System.out.println("LISTADO DE CARTAS: ");
        for (int i = 0; i < cartas.size(); i += 1) {
            System.out.println(i+". Numero: " + cartas.elementAt(i).get_numero() + " | " + "Tipo: " + cartas.elementAt(i).get_tipo());
        }
    }
}