import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;  
import java.util.Vector;

public class Modelo {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador_actual;
    private Stack<Carta> mazo;
    private Stack<Carta> comodines;
    private Vector<Carta> baraja;

    public Modelo(){
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();
        this.mazo = new Stack<Carta>();
        this.comodines = new Stack<Carta>();
        this.baraja = new Vector<Carta>();
    }

    public  int numero_aleatorio(int min, int max) {
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
            baraja.add(new Carta (i, "corazon", "imagenes/"+i+"_of_hearts.png"));
            baraja.add(new Carta (i, "pica", "imagenes/"+i+"_of_spades.png"));
            baraja.add(new Carta (i, "trebol", "imagenes/"+i+"_of_clubs.png"));
            baraja.add(new Carta (i, "rombo", "imagenes/"+i+"_of_diamonds.png"));
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

    public Vector<Carta> ordenar_mazo(Vector<Carta> cartas) {

        Carta arregloAux[] = new Carta[cartas.size()]; 
        Vector<Carta> cartas_ordenadas = new Vector<Carta>(); 

        for (int i = 0; i < cartas.size(); i++) {
            arregloAux[i] = cartas.elementAt(i); 
        }
        
        for (int j = 1; j < cartas.size(); j++) {
            Carta key = arregloAux[j];
            int i = j - 1;
            while (i >= 0 && arregloAux[j].get_numero() > key.get_numero()) {
                arregloAux[i+1] = arregloAux[i];
                i--;
            }
            arregloAux[i+1] = key;
        }

        for (int i = 0; i < cartas.size(); i++) {
            cartas_ordenadas.add(arregloAux[i]); 
        }

        return cartas_ordenadas; 
    }

    public boolean puede_ganar(Vector<Carta> grupo1, Vector<Carta> grupo2 ) {
        
        boolean gano = false;
        boolean hay_cartas_iguales_1 = false;
        boolean hay_escalera_1 = false;
        boolean hay_cartas_iguales_2 = false;
        boolean hay_escalera_2 = false;

        hay_cartas_iguales_1 = hay_iguales(grupo1); 

        hay_cartas_iguales_2 = hay_iguales(grupo2); 

        hay_escalera_1 = hay_escalera(grupo1); 

        hay_escalera_2 = hay_escalera(grupo2); 

        if ((hay_cartas_iguales_1 || hay_escalera_1) && (hay_cartas_iguales_2 || hay_escalera_2)) {
            gano = true; 
        }
        
        return gano; 
    }

    public boolean hay_escalera(Vector<Carta> cartas_escalera) {
        boolean tiene_escalera = false;
        boolean mismo_tipo = false;
        int contador_mismo_tipo = 0; 
        Vector<Carta> cartas_ordenadas = new Vector<Carta>();
        
        for (int i = 1; i < cartas_escalera.size(); i++) {
            if(cartas_escalera.elementAt(i).get_tipo() == cartas_escalera.elementAt(0).get_tipo()) {
                 contador_mismo_tipo++;
            } 
        }

        if (contador_mismo_tipo == cartas_escalera.size()) {
            mismo_tipo = true; 
        }

        if (mismo_tipo) {
            cartas_ordenadas = ordenar_mazo(cartas_escalera);

            for (int i = 0; i < cartas_ordenadas.size(); i++) {
                int next_index = i+1; 
                if (cartas_ordenadas.elementAt(next_index).get_numero() == cartas_ordenadas.elementAt(i).get_numero() + 1) {
                    tiene_escalera = true;
                }
                else {
                    tiene_escalera = false; 
                    break;
                }
            }

        } else {
            tiene_escalera = false;
        } 

        return tiene_escalera; 
    }

    public boolean hay_iguales(Vector<Carta> cartas_iguales) {
        boolean tiene_iguales = false; 
        int contador_cartas_iguales = 0; 

        for (int i = 1; i < cartas_iguales.size(); i++) {
            if(cartas_iguales.elementAt(i) == cartas_iguales.elementAt(0)) {
                contador_cartas_iguales++; 
            } 
        }

        if (contador_cartas_iguales == cartas_iguales.size()) {
            tiene_iguales = true;
        }

        return tiene_iguales; 
    } 

    public void repartir_cartas() {
        for (int j = 0; j < 7; j += 1) {
            this.jugador1.recibir_carta(this.mazo.pop());
            this.jugador2.recibir_carta(this.mazo.pop());
        }
    }

    public void entregar_carta(Jugador jugador, int num) {
        Carta c;
        if (num == 0) {
            jugador.recibir_carta(this.mazo.pop());
        }
        else {
            jugador.recibir_carta(this.comodines.pop());
        }
    }

    public void elegir_jugador_actual () {
        int num = this.numero_aleatorio(0, 2);
        switch (num) {
            case 0:
                this.jugador_actual = this.jugador1;
            break;
            case 1:
                this.jugador_actual = this.jugador1;
            break;
        }
    }

    public void cambiar_jugador_actual() {
        if (this.jugador_actual == this.jugador1) {
            this.jugador_actual = jugador2;
        }
        else {
            if (this.jugador_actual == this.jugador2) {
                this.jugador_actual = jugador1;
            }
        }
    }


    public Jugador get_jugador_actual() {
        return this.jugador_actual;
    }

    public void agregar_carta_comodines(Carta c) {
        this.comodines.push(c);
    }

    public Vector<String> cards_to_strings_vector(Jugador j) {
        String aux;
        Vector<String> result = new Vector<String>();
        Vector<Carta> cartas = j.get_cartas();
        for (int i = 0; i < cartas.size(); i += 1) {
            aux = cartas.elementAt(i).get_imagen();
            System.out.println(aux);
            result.add(aux);
        }
        return result;
    }

    public Stack<Carta> get_mazo() {
        return this.mazo;
    }

    public Stack<Carta> get_comodines() {
        return this.comodines;
    }





}

