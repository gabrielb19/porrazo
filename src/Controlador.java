import java.util.Vector;
import java.awt.event.*;


public class Controlador implements ActionListener{
    private Interfaz interfaz;
    private boolean jugar; 
    private Mesa mesa;
    private Jugador j1;
    private Jugador j2; 
    private Jugador jugador_actual;

    Controlador() {
        this.interfaz = new Interfaz();
        this.jugar = false; 
        this.mesa = new Mesa();
        this.j1 = new Jugador();
        this.j2 = new Jugador();
        this.jugador_actual = new Jugador();
    }

    public void iniciar_juego() {
        mesa.crear_mazo();
        mesa.repartir_cartas(this.j1,this.j2);
        
        boolean primera_iteracion = true;
        boolean hay_ganador = false;

        this.interfaz.pantalla_login();
        agregar_action_listeners_login();

        while(jugar == false) {
            System.out.print("");
        }

        conseguir_nombres();

        this.interfaz.reset(this.interfaz.get_pantalla_login());

        elegir_jugador_actual();

        while(!hay_ganador) {     
            Vector<String> imagenes = mesa.cards_to_strings_vector(this.jugador_actual.get_cartas());
            String comodin = "";
            if (!primera_iteracion && !mesa.get_comodines().empty()) {
                comodin = mesa.get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }

            this.interfaz.pantalla_principal(imagenes, comodin, "Turno De Comer De: "+ this.jugador_actual.get_nombre());
            agregar_action_listeners_comer();

            agregar_action_listeners_reglas();
            agregar_action_listeners_partida();

            while (!this.jugador_actual.get_comio()){
                System.out.print("");
            }

            this.interfaz.reset(this.interfaz.get_pantalla_principal());
            this.interfaz = new Interfaz();

            imagenes = mesa.cards_to_strings_vector(this.jugador_actual.get_cartas());
            if (!primera_iteracion && !mesa.get_comodines().empty()) {
                comodin = mesa.get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }
            this.interfaz.pantalla_principal(imagenes, comodin, "Turno de botar una carta de: "+ this.jugador_actual.get_nombre());
            agregar_action_listeners_desechar();

            agregar_action_listeners_reglas();
            agregar_action_listeners_partida();

            while (!this.jugador_actual.get_desecho()){
                System.out.print("");
            }

            this.interfaz.reset(this.interfaz.get_pantalla_principal());
            this.interfaz = new Interfaz();

            this.jugador_actual.set_comio(false);
            this.jugador_actual.set_desecho(false);
            
            cambiar_jugador_actual();
            primera_iteracion = false;
        }
    }

    public void agregar_action_listeners_login() {
        this.interfaz.get_boton_jugar().addActionListener(this);
    }

    public void conseguir_nombres() {
        String nombre1 = "";
        String nombre2 = "";

        nombre1 = this.interfaz.get_jugador_1().getText();
        nombre2 = this.interfaz.get_jugador_2().getText();  

        this.j1.setNombre(nombre1);
        this.j2.setNombre(nombre2);
    }

    public void agregar_action_listeners_comer() {
        this.interfaz.get_comodin().addActionListener(this);
        this.interfaz.get_carta_secreta().addActionListener(this);
    }

    public void agregar_action_listeners_partida() {
        this.interfaz.get_boton_partida().addActionListener(this);
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
                mesa.agregar_carta_comodines(this.jugador_actual.desechar_carta(index));
                this.jugador_actual.set_desecho(true);
                break;
            }
        }
        if (index == -1) {
            if (e.getSource() == this.interfaz.get_comodin()) {
                if (mesa.entregar_carta(this.jugador_actual, 1)) {
                    this.jugador_actual.set_comio(true);
                }
            }
            else {
                if (e.getSource() == this.interfaz.get_carta_secreta()) {
                    if (mesa.entregar_carta(this.jugador_actual, 0)) {
                        this.jugador_actual.set_comio(true);
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
                        else {
                            if (e.getSource() == this.interfaz.get_boton_partida()) {
                                GuardaPartidas g = new GuardaPartidas();
                                g.escribir_en_archivo(this.j1, this.j2
                                    , mesa.get_mazo(), mesa.get_comodines());
                                this.interfaz.reset(this.interfaz.get_pantalla_principal());
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
    }

    public void elegir_jugador_actual () {        
        this.jugador_actual = this.j1;
    }

    public void cambiar_jugador_actual() {
        if (this.jugador_actual == this.j1) {
            this.j1 = this.jugador_actual;
            this.jugador_actual = j2;
        }
        else {
            if (this.jugador_actual == this.j2) {
                this.j2 = this.jugador_actual;
                this.jugador_actual = j1;
            }
        }
    }


}
