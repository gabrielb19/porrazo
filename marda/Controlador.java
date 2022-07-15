import java.util.Vector;
import java.util.Stack;
import java.awt.event.*;

public class Controlador implements ActionListener {
    private int counter;
    private Interfaz interfaz;
    private boolean jugar;
    private boolean cargar;
    private Mesa mesa;
    private Jugador j1;
    private Jugador j2;
    private Jugador jugador_actual;
    private Vector<Carta> grupo1;
    private Vector<Carta> grupo2;
    boolean hay_ganador;

    Controlador() {
        this.grupo1 = new Vector<Carta>();
        this.grupo2 = new Vector<Carta>();
        this.counter = 0;
        this.interfaz = new Interfaz();
        this.jugar = false;
        this.mesa = new Mesa();
        this.j1 = new Jugador();
        this.j2 = new Jugador();
        this.jugador_actual = new Jugador();
        this.hay_ganador = false;
    }

    public void iniciar_juego() {
        mesa.crear_mazo();
        mesa.repartir_cartas(this.j1, this.j2);


        this.interfaz.pantalla_login();
        agregar_action_listeners_login();

        while ((jugar == false) && (cargar == false)) {
            System.out.print("");
        }

        conseguir_nombres();

        this.interfaz.reset(this.interfaz.get_pantalla_login());

        elegir_jugador_actual();

        while (!this.hay_ganador) {
            Vector<String> imagenes = mesa.cards_to_strings_vector(this.jugador_actual.get_cartas());
            String comodin = "";
            if (!mesa.get_comodines().empty()) {
                comodin = mesa.get_comodines().peek().get_imagen();
            } else {
                comodin = "../imagenes/vacio.png";
            }

            this.interfaz.pantalla_principal(imagenes, comodin,
                    "Turno de comer: " + this.jugador_actual.get_nombre());
            
            agregar_action_listener_formar_grupos();
            agregar_action_listeners_comer();
            agregar_action_listeners_reglas();
            agregar_action_listeners_partida();

            while (!this.jugador_actual.get_comio()) {
                System.out.print("");
            }

            this.interfaz.reset(this.interfaz.get_pantalla_principal());
            this.interfaz = new Interfaz();

            imagenes = mesa.cards_to_strings_vector(this.jugador_actual.get_cartas());
            if (!mesa.get_comodines().empty()) {
                comodin = mesa.get_comodines().peek().get_imagen();
            } else {
                comodin = "../imagenes/vacio.png";
            }
            this.interfaz.pantalla_principal(imagenes, comodin,
                    "Turno de botar una carta: " + this.jugador_actual.get_nombre());
            
            agregar_action_listener_formar_grupos();
            agregar_action_listeners_desechar();
            agregar_action_listeners_reglas();
            agregar_action_listeners_partida();

            while (!this.jugador_actual.get_desecho()) {
                System.out.print("");
            }

            this.interfaz.reset(this.interfaz.get_pantalla_principal());
            this.interfaz = new Interfaz();

            this.jugador_actual.set_comio(false);
            this.jugador_actual.set_desecho(false);

            cambiar_jugador_actual();
        }
    }

    public void agregar_action_listeners_login() {
        this.interfaz.get_boton_jugar().addActionListener(this);
        this.interfaz.get_boton_cargar().addActionListener(this); 
    }

    public void conseguir_nombres() {
        String nombre1 = "";
        String nombre2 = "";
        nombre1 = this.interfaz.get_jugador_1().getText();
        nombre2 = this.interfaz.get_jugador_2().getText();

        if (nombre1.length() != 0 && nombre2.length() != 0) {
            this.j1.set_nombre(nombre1);
            this.j2.set_nombre(nombre2);
        }
    }

    public void agregar_action_listeners_comer() {
        this.interfaz.get_comodin().addActionListener(this);
        this.interfaz.get_carta_secreta().addActionListener(this);
    }

    public void agregar_action_listeners_partida() {
        this.interfaz.get_boton_partida().addActionListener(this);
    }

    public void agregar_action_listeners_reglas() {
        this.interfaz.get_boton_reglas().addActionListener(this);
    }

    public void agregar_action_listener_formar_grupos() {
        this.interfaz.get_boton_eleccion().addActionListener(this);
    }

    public void agregar_action_listener_cartas_grupos() {
        for (int i = 0; i < 7; i += 1) {
            this.interfaz.get_mano_cartas_eleccion().elementAt(i).addActionListener(this);
        }
        this.interfaz.get_boton_deshacer_grupos().addActionListener(this);
        this.interfaz.get_boton_verificar_grupos().addActionListener(this);
    }

    public void agregar_action_listeners_desechar() {
        for (int i = 0; i < this.interfaz.get_mano_cartas().size(); i += 1) {
            this.interfaz.get_mano_cartas().elementAt(i).addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int index = -1;
        //int index2 = -1;
        for (int i = 0; i < this.interfaz.get_mano_cartas().size(); i += 1) {
            if (e.getSource() == this.interfaz.get_mano_cartas().elementAt(i)) {
                //System.out.println("HOLA CARTA PRESIONADA");
                index = i;
                mesa.agregar_carta_comodines(this.jugador_actual.desechar_carta(index));
                this.jugador_actual.set_desecho(true);
                break;
            }
        }

        if (index == -1 /*&& index2 == -1*/) {
            for (int i = 0; i < this.interfaz.get_mano_cartas_eleccion().size(); i += 1) {
                if (e.getSource() == this.interfaz.get_mano_cartas_eleccion().elementAt(i)) {
                    this.interfaz.get_mano_cartas_eleccion().elementAt(i).setVisible(false);
                    if (counter < 4) {
                        //System.out.println(counter);
                        this.interfaz.set_imagen_boton(this.interfaz.get_grupo1().elementAt(counter)
                        , this.jugador_actual.get_cartas().elementAt(i).get_imagen());
                        this.interfaz.get_grupo1().elementAt(counter).setVisible(true);
    
                        this.grupo1.add(this.jugador_actual.get_cartas().elementAt(i));
                    }
                    else {
                        //System.out.println(counter);
                        this.interfaz.set_imagen_boton(this.interfaz.get_grupo2().elementAt(counter-4)
                        , this.jugador_actual.get_cartas().elementAt(i).get_imagen());
                        this.interfaz.get_grupo2().elementAt(counter-4).setVisible(true);
                        this.grupo2.add(this.jugador_actual.get_cartas().elementAt(i));
                    }
                    if (counter == 6) {
                        counter = 0;
                    }
                    else {
                        counter += 1;
                    }
    
                    //index2 = i;
                    break;
                }
            }
            if (e.getSource() == this.interfaz.get_comodin()) {
                if (mesa.entregar_carta(this.jugador_actual, 1)) {
                    this.jugador_actual.set_comio(true);
                }
            } else {
                if (e.getSource() == this.interfaz.get_carta_secreta()) {
                    if (mesa.entregar_carta(this.jugador_actual, 0)) {
                        this.jugador_actual.set_comio(true);
                    }
                } else {
                    if (e.getSource() == this.interfaz.get_boton_reglas()) {
                        this.interfaz.desplegar_reglas();
                    } else {
                        if (e.getSource() == this.interfaz.get_boton_jugar()) {
                            this.jugar = true;
                        } else {
                            if (e.getSource() == this.interfaz.get_boton_cargar()) {
                                GuardaPartidas g = new GuardaPartidas();
                                Vector<String> data = g.getline();
                                //System.out.println(data.size());
                                Vector<Object> vecObj = g.cargarPartida(data); 
                                this.j1 = ((Jugador)vecObj.elementAt(0));
                                this.j2 = (Jugador) vecObj.elementAt(1); 
                                
                                this.mesa.set_mazo((Stack<Carta>)vecObj.elementAt(2));

                                this.mesa.set_comodines((Stack<Carta>)vecObj.elementAt(3));

                                this.cargar = true; 

                            } else {
                                if (e.getSource() == this.interfaz.get_boton_partida()) {
                                    GuardaPartidas g = new GuardaPartidas();
                                    g.escribir_en_archivo(this.j1, this.j2, mesa.get_mazo(), mesa.get_comodines());
                                    this.interfaz.reset(this.interfaz.get_pantalla_principal());
                                    System.exit(0);
                                }
                                else {
                                    if (e.getSource() == this.interfaz.get_boton_eleccion()) {
                                        if (this.jugador_actual.get_cartas().size() > 7) {
                                            this.interfaz.impedimento_eleccion();
                                        }
                                        else {
                                            this.interfaz.pantalla_eleccion_cartas();
                                            agregar_action_listener_cartas_grupos();
                                           for (int i = 0; i < this.interfaz.get_mano_cartas_eleccion().size(); i += 1) {
                                                this.interfaz.set_imagen_boton(this.interfaz.get_mano_cartas_eleccion().elementAt(i)
                                                    , this.jugador_actual.get_cartas().elementAt(i).get_imagen());
                                           }
                                           this.interfaz.get_pantalla_eleccion().setVisible(true);
                                        }
                                    }
                                    else {
                                        if (e.getSource() == this.interfaz.get_boton_deshacer_grupos()) {
                                            this.grupo1.clear();
                                            this.grupo2.clear();
                                            for (int i = 0; i < 7; i += 1) {
                                                this.interfaz.get_mano_cartas_eleccion().elementAt(i).setVisible(true);
                                                if (i < 4) {
                                                    this.interfaz.get_grupo1().elementAt(i).setVisible(false);
                                                }
                                                else {
                                                    this.interfaz.get_grupo2().elementAt(i-4).setVisible(false);
                                                }
                                            }
                                        }
                                        else {
                                            if (e.getSource() == this.interfaz.get_boton_verificar_grupos()) {
                                                boolean puede_ganar = this.mesa.puede_ganar(this.grupo1, this.grupo2);
                                                
                                                this.interfaz.pantalla_ganar(puede_ganar);
                                                if (puede_ganar) {
                                                    this.hay_ganador = true;
                                                    System.exit(0);
                                                }
                                            }
                                        }
                                    }
                                }
                            }    
                        }
                    }
                }
            }
        }
    }

    public void elegir_jugador_actual() {
        this.jugador_actual = this.j1;
    }

    public void cambiar_jugador_actual() {
        if (this.jugador_actual == this.j1) {
            this.j1 = this.jugador_actual;
            this.jugador_actual = j2;
        } else {
            if (this.jugador_actual == this.j2) {
                this.j2 = this.jugador_actual;
                this.jugador_actual = j1;
            }
        }
    }

}
