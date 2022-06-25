import javax.swing.*;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.*;


public class Controlador implements ActionListener{
    private Interfaz interfaz;
    private FuncionalidadJugador jugadores;
    private FuncionalidadCartas cartas; 

    Controlador() {
        this.interfaz = new Interfaz();
        this.jugadores = FuncionalidadJugador.get(); 
        this.cartas = FuncionalidadCartas.get(); 
    }

    public void iniciar_juego() {
        this.cartas.crear_baraja();
        this.cartas.crear_mazo();
        this.jugadores.repartir_cartas();
        
        boolean primera_iteracion = true;
        boolean hay_ganador = false;
        
        this.jugadores.elegir_jugador_actual();

        while(!hay_ganador) {
            Vector<String> imagenes = this.cartas.cards_to_strings_vector(this.jugadores.get_jugador_actual().get_cartas());
            String comodin = "";
            if (!primera_iteracion && !this.cartas.get_comodines().empty()) {
                comodin = this.cartas.get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }

            this.interfaz.pantalla_principal(imagenes, comodin, "TURNO DE COMER DEL JUGADOR "+ this.jugadores.get_jugador_actual().get_nombre());
            agregar_action_listeners_comer();

            while (!this.jugadores.get_jugador_actual().get_comio()){
                System.out.print("");
            }

            this.interfaz.reset();
            this.interfaz = new Interfaz();

            imagenes = this.cartas.cards_to_strings_vector(this.jugadores.get_jugador_actual().get_cartas());
            if (!primera_iteracion && !this.cartas.get_comodines().empty()) {
                comodin = this.cartas.get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }
            this.interfaz.pantalla_principal(imagenes, comodin, "TURNO DE BOTAR UNA CARTA DEL JUGADOR "+ this.jugadores.get_jugador_actual().get_nombre());
            agregar_action_listeners_desechar();

            while (!this.jugadores.get_jugador_actual().get_desecho()){
                System.out.print("");
            }

            this.interfaz.reset();
            this.interfaz = new Interfaz();

            this.jugadores.get_jugador_actual().set_comio(false);
            this.jugadores.get_jugador_actual().set_desecho(false);
            
            this.jugadores.cambiar_jugador_actual();
            primera_iteracion = false;
        }
    }

    public void agregar_action_listeners_comer() {
        this.interfaz.get_comodin().addActionListener(this);
        this.interfaz.get_carta_secreta().addActionListener(this);
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
                this.cartas.agregar_carta_comodines(this.jugadores.get_jugador_actual().desechar_carta(index));
                this.jugadores.get_jugador_actual().set_desecho(true);
                break;
            }
        }
        if (index == -1) {
            if (e.getSource() == this.interfaz.get_comodin()) {
                if (this.jugadores.entregar_carta(this.jugadores.get_jugador_actual(), 1)) {
                    this.jugadores.get_jugador_actual().set_comio(true);
                }
            }
            else {
                if (e.getSource() == this.interfaz.get_carta_secreta()) {
                    if (this.jugadores.entregar_carta(this.jugadores.get_jugador_actual(), 0)) {
                        this.jugadores.get_jugador_actual().set_comio(true);
                    }
                }
            }
        }
    }
}
