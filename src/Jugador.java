import java.util.Vector;
import java.util.Stack;


public class Jugador {
    private Vector<Carta> cartas;
    private String nombre;
    private boolean comio;
    private boolean desecho;

    public Jugador() {
        this.nombre = "";
        this.cartas = new Vector<Carta>();
        this.comio = false;
        this.desecho = false;
    }

    public Jugador(String nombre){
        this.nombre = nombre;
        this.cartas = new Vector<Carta>();
    }

    public void recibir_carta(Carta c){
        this.cartas.add(c);
    }

    public Carta desechar_carta(int index) {
        return this.cartas.remove(index);
    }

    public String get_nombre() {
        return this.nombre;
    }

    public boolean get_comio() {
        return this.comio;
    }

    public boolean get_desecho() {
        return this.desecho;
    }

    public void set_comio(boolean var) {
        this.comio = var;
    }

    public void set_desecho(boolean var) {
        this.desecho = var;
    }

    public Vector<Carta> get_cartas() {
        return this.cartas;
    }

    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }

    public void ver_cartas() {
        System.out.println("LISTADO DE CARTAS: ");
        for (int i = 0; i < cartas.size(); i += 1) {
            System.out.println(i+". Numero: " + cartas.elementAt(i).get_numero() + " | " + "Tipo: " + cartas.elementAt(i).get_tipo());
        }
    }
}