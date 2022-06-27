import java.util.Vector;
import java.awt.event.*;


public class Controlador implements ActionListener{
    private Interfaz interfaz;
    private boolean jugar; 

    Controlador() {
        this.interfaz = new Interfaz();
        this.jugar = false; 
    }

    public void iniciar_juego() {
        FuncionalidadCartas.get().crear_baraja();
        FuncionalidadCartas.get().crear_mazo();
        FuncionalidadJugador.get().repartir_cartas();
        
        boolean primera_iteracion = true;
        boolean hay_ganador = false;

        this.interfaz.pantalla_login();
        agregar_action_listeners_login();

        while(jugar == false) {
            System.out.print("");
        }

        conseguir_nombres();

        this.interfaz.reset(this.interfaz.get_pantalla_login());

        FuncionalidadJugador.get().elegir_jugador_actual();

        while(!hay_ganador) {
            
            Vector<String> imagenes = FuncionalidadCartas.get().cards_to_strings_vector(FuncionalidadJugador.get().get_jugador_actual().get_cartas());
            String comodin = "";
            if (!primera_iteracion && !FuncionalidadCartas.get().get_comodines().empty()) {
                comodin = FuncionalidadCartas.get().get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }

            this.interfaz.pantalla_principal(imagenes, comodin, "Turno De Comer De: "+ FuncionalidadJugador.get().get_jugador_actual().get_nombre());
            agregar_action_listeners_comer();

            agregar_action_listeners_reglas();

            while (!FuncionalidadJugador.get().get_jugador_actual().get_comio()){
                System.out.print("");
            }

            this.interfaz.reset(this.interfaz.get_pantalla_principal());
            this.interfaz = new Interfaz();

            imagenes = FuncionalidadCartas.get().cards_to_strings_vector(FuncionalidadJugador.get().get_jugador_actual().get_cartas());
            if (!primera_iteracion && !FuncionalidadCartas.get().get_comodines().empty()) {
                comodin = FuncionalidadCartas.get().get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }
            this.interfaz.pantalla_principal(imagenes, comodin, "TURNO DE BOTAR UNA CARTA DEL JUGADOR: "+ FuncionalidadJugador.get().get_jugador_actual().get_nombre());
            agregar_action_listeners_desechar();

            agregar_action_listeners_reglas();

            while (!FuncionalidadJugador.get().get_jugador_actual().get_desecho()){
                System.out.print("");
            }

            this.interfaz.reset(this.interfaz.get_pantalla_principal());
            this.interfaz = new Interfaz();

            FuncionalidadJugador.get().get_jugador_actual().set_comio(false);
            FuncionalidadJugador.get().get_jugador_actual().set_desecho(false);
            
            FuncionalidadJugador.get().cambiar_jugador_actual();
            primera_iteracion = false;
        }
    }

    public void agregar_action_listeners_login() {
        this.interfaz.get_boton_jugar().addActionListener(this);
    }

    public void conseguir_nombres() {
        
        FuncionalidadJugador jugadores = FuncionalidadJugador.get(); 

        String nombre1 = "";
        String nombre2 = "";

        nombre1 = this.interfaz.get_jugador_1().getText();
        nombre2 = this.interfaz.get_jugador_2().getText();  

        jugadores.get_jugador_1().setNombre(nombre1);
        jugadores.get_jugador_2().setNombre(nombre2);

    }

    public void agregar_action_listeners_comer() {
        this.interfaz.get_comodin().addActionListener(this);
        this.interfaz.get_carta_secreta().addActionListener(this);
        this.interfaz.get_boton_reglas().addActionListener(this);
    }

    public void agregar_action_listeners_grupos() {
        this.interfaz.get_boton_eleccion().addActionListener(this);
    }

    public void agregar_action_listeners_reglas() {
        this.interfaz.get_boton_reglas().addActionListener(this);
    }

    public void agregar_action_listeners_desechar() {
        for (int i = 0; i < this.interfaz.get_mano_cartas().size(); i += 1) {
            this.interfaz.get_mano_cartas().elementAt(i).addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int index = -1;
        for (int i = 0; i < this.interfaz.get_mano_cartas().size(); i += 1) {
            if (e.getSource() == this.interfaz.get_mano_cartas().elementAt(i)) {
                System.out.println("CARTA PRESIONADA");
                index = i;
                FuncionalidadCartas.get().agregar_carta_comodines(FuncionalidadJugador.get().get_jugador_actual().desechar_carta(index));
                FuncionalidadJugador.get().get_jugador_actual().set_desecho(true);
                break;
            }
        }
        if (index == -1) {
            if (e.getSource() == this.interfaz.get_comodin()) {
                if (FuncionalidadJugador.get().entregar_carta(FuncionalidadJugador.get().get_jugador_actual(), 1)) {
                    FuncionalidadJugador.get().get_jugador_actual().set_comio(true);
                }
            }
            else {
                if (e.getSource() == this.interfaz.get_carta_secreta()) {
                    if (FuncionalidadJugador.get().entregar_carta(FuncionalidadJugador.get().get_jugador_actual(), 0)) {
                        FuncionalidadJugador.get().get_jugador_actual().set_comio(true);
                    }
                }
                else {
                    if(e.getSource() == this.interfaz.get_boton_reglas()) {
                        this.interfaz.desplegar_reglas();
                    }
                    else {
                        if(e.getSource() == this.interfaz.get_boton_jugar()) {
                            this.jugar = true; 
                        }
                    }
                }
            }
        }
    }
}
