import java.util.Stack;
import java.util.Vector;

public class Mesa {
    private Stack<Carta> mazo;
    private Stack<Carta> comodines;
    private Vector<Carta> baraja;

    public Mesa() {
        this.mazo = new Stack<Carta>();
        this.comodines = new Stack<Carta>();
        this.baraja = new Vector<Carta>();
        for (int i = 1; i < 14; i += 1) {
            baraja.add(new Carta(i, "corazon", "../imagenes/" + i + "_of_hearts.png"));
            baraja.add(new Carta(i, "pica", "../imagenes/" + i + "_of_spades.png"));
            baraja.add(new Carta(i, "trebol", "../imagenes/" + i + "_of_clubs.png"));
            baraja.add(new Carta(i, "rombo", "../imagenes/" + i + "_of_diamonds.png"));
        }
    }

    public void repartir_cartas(Jugador j1, Jugador j2) {
        for (int j = 0; j < 7; j += 1) {
            j1.recibir_carta(this.mazo.pop());
            j2.recibir_carta(this.mazo.pop());
        }
    }

    public boolean entregar_carta(Jugador jugador, int num) {
        boolean entregada = false;
        if (num == 0) {
            if (this.mazo.empty()) {
                System.out.println("El mazo está vacio");
            } else {
                jugador.recibir_carta(this.mazo.pop());
                entregada = true;
            }
        } else {
            if (this.comodines.empty()) {
                System.out.println("La pila de comodines esta vacia");
            } else {
                jugador.recibir_carta(this.comodines.pop());
                entregada = true;
            }
        }
        return entregada;
    }

    public int numero_aleatorio(int min, int max) {
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

    public void crear_mazo() {
        Vector<Integer> barajadas = new Vector<Integer>();
        int index = 0;
        for (int i = 0; i < 52; i += 1) {
            while (pertenece(barajadas, index = numero_aleatorio(0, 52)))
                ;
            barajadas.add(index);
            this.mazo.push(this.baraja.elementAt(index));
        }
        //System.out.println(barajadas);
    }

    public void ordenar_mazo(Vector<Carta> cartas) {
        for (int i = 0; i < cartas.size() - 1; i++){
            for (int j = 0; j < cartas.size() - i - 1; j++){
                if (cartas.elementAt(j).get_numero() > cartas.elementAt(j + 1).get_numero()){
                    Carta aux = cartas.elementAt(j);
                    cartas.set(j, cartas.elementAt(j+1));
                    cartas.set(j+1, aux);
                }
            }
        }
    }

    public boolean hay_escalera(Vector<Carta> cartas) {
        boolean tiene_escalera = true;
        for (int i = 0; i < cartas.size()-1; i += 1) {
            if (cartas.elementAt(i).get_numero() != (cartas.elementAt(i+1).get_numero()-1)) {
                tiene_escalera = false;
                break;
            }
        }

        return tiene_escalera;
    }

    public boolean mismo_numero(Vector<Carta> cartas) {
        boolean son_iguales = true;
        int num = cartas.elementAt(0).get_numero();
        for (int i = 1; i < cartas.size(); i += 1) {
            if (cartas.elementAt(i).get_numero() != num) {
                son_iguales = false;
                break;
            }
        }
        return son_iguales;
    }

    public boolean mismo_tipo(Vector<Carta> cartas) {
        boolean son_iguales = true;
        String tipo = cartas.elementAt(0).get_tipo();
        for (int i = 1; i < cartas.size(); i += 1) {
            if (!cartas.elementAt(i).get_tipo().equals(tipo)) {
                son_iguales = false;
                break;
            }
        }
        return son_iguales;
    }

    public boolean puede_ganar(Vector<Carta> grupo1, Vector<Carta> grupo2) {
        boolean gano = false;
        ordenar_mazo(grupo1);
        ordenar_mazo(grupo2);
        if (mismo_numero(grupo1) && mismo_numero(grupo2)) {
            gano = true;
        }
        else if (mismo_numero(grupo1) && mismo_tipo(grupo2)) {
            if (hay_escalera(grupo2)) {
                gano = true;
            }
        }
        else if (mismo_tipo(grupo1) && mismo_numero(grupo2)) {
            if (hay_escalera(grupo1)) {
                gano = true;
            }
        }
        else if (mismo_tipo(grupo1) && mismo_tipo(grupo2)) {
            if (hay_escalera(grupo2) && hay_escalera(grupo1)) {
                gano = true;
            }
        }
        return gano;
    }

    

    public void agregar_carta_comodines(Carta c) {
        this.comodines.push(c);
    }

    public Stack<Carta> get_mazo() {
        return this.mazo;
    }

    public void set_mazo(Stack<Carta> mazo) {
        this.mazo = mazo;
    }

    public Stack<Carta> get_comodines() {
        return this.comodines;
    }

    public void set_comodines(Stack<Carta> comodines) {
        this.comodines = comodines;
    }

    public Vector<String> cards_to_strings_vector(Vector<Carta> cartas) {
        String aux;
        Vector<String> result = new Vector<String>();
        for (int i = 0; i < cartas.size(); i += 1) {
            aux = cartas.elementAt(i).get_imagen();
            result.add(aux);
        }
        return result;
    }

}
