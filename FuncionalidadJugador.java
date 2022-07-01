import java.util.Stack;  

public class FuncionalidadJugador {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador_actual;
    private static FuncionalidadJugador instancia;  
   
    public static FuncionalidadJugador get() {
        if (instancia == null) {
            instancia = new FuncionalidadJugador(); 
        }
        return instancia; 
    }
    
    private FuncionalidadJugador(){
        this.jugador1 = new Jugador("1");
        this.jugador2 = new Jugador("2");
    }

    public void repartir_cartas() {
        Stack<Carta> mazo = FuncionalidadCartas.get().get_mazo(); 
        
        for (int j = 0; j < 7; j += 1) {
            this.jugador1.recibir_carta(mazo.pop());
            this.jugador2.recibir_carta(mazo.pop());
        }
    }

    public boolean entregar_carta(Jugador jugador, int num) {
        Stack<Carta> mazo = FuncionalidadCartas.get().get_mazo();
        Stack<Carta> comodines = FuncionalidadCartas.get().get_comodines(); 
        boolean entregada = false;
        if (num == 0) {
            if (mazo.empty()) {
                System.out.println("El mazo está vacio");
            }
            else {
                jugador.recibir_carta(mazo.pop());
                entregada = true;
            }
        }
        else {
            if (comodines.empty()) {
                System.out.println("La pila de comodines esta vacia");
            }
            else {
                jugador.recibir_carta(comodines.pop());
                entregada = true;
            }
        }
        return entregada;
    }


    public  int numero_aleatorio(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void elegir_jugador_actual () {        
        this.jugador_actual = this.jugador1;

    }

    public void cambiar_jugador_actual() {
        if (this.jugador_actual == this.jugador1) {
            this.jugador1 = this.jugador_actual;
            this.jugador_actual = jugador2;
        }
        else {
            if (this.jugador_actual == this.jugador2) {
                this.jugador2 = this.jugador_actual;
                this.jugador_actual = jugador1;
            }
        }
    }


    public Jugador get_jugador_actual() {
        return this.jugador_actual;
    }

    public Jugador get_jugador_1() {
        return this.jugador1;
    }

    public Jugador get_jugador_2() {
        return this.jugador2;
    }
}

