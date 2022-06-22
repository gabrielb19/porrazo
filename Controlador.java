import javax.swing.*;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.*;


public class Controlador implements ActionListener{
    private Interfaz interfaz;
    private Modelo modelo;

    Controlador() {
        this.interfaz = new Interfaz();
        this.modelo = new Modelo();
    }

    public void iniciar_juego() {
        this.modelo.crear_baraja();
        this.modelo.crear_mazo();
        this.modelo.repartir_cartas();
        
        boolean primera_iteracion = true;
        boolean hay_ganador = false;
        
        this.modelo.elegir_jugador_actual();
        while(!hay_ganador) {
            Vector<String> imagenes = this.modelo.cards_to_strings_vector(this.modelo.get_jugador_actual());
            System.out.println(imagenes);
            String comodin;
            if (!primera_iteracion && !this.modelo.get_comodines().empty()) {
                comodin = this.modelo.get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }

            this.interfaz.pantalla_principal(imagenes, comodin);
            agregar_action_listeners();

            while (!this.modelo.get_jugador_actual().get_comio() && !this.modelo.get_jugador_actual().get_desecho());

            this.interfaz = new Interfaz();

            this.modelo.get_jugador_actual().set_comio(false);
            this.modelo.get_jugador_actual().set_desecho(false);
            
            this.modelo.cambiar_jugador_actual();
            primera_iteracion = false;
        }
    }

    public void agregar_action_listeners() {
        for (int i = 0; i < this.interfaz.get_mano_cartas().size(); i += 1) {
            this.interfaz.get_mano_cartas().elementAt(i).addActionListener(this);
        }

        this.interfaz.get_comodin().addActionListener(this);
        this.interfaz.get_carta_secreta().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        int index = -1;
        for (int i = 0; i < this.interfaz.get_mano_cartas().size(); i += 1) {
            if (e.getSource() == this.interfaz.get_mano_cartas().elementAt(i)) {
                System.out.println("BOTON CARTA");
                index = i;
                this.modelo.agregar_carta_comodines(this.modelo.get_jugador_actual().desechar_carta(index));
                this.modelo.get_jugador_actual().set_desecho(true);
                break;
            }
        }
        if (index == -1) {
            if (e.getSource() == this.interfaz.get_comodin()) {
                System.out.println("BOTON COMODIN");
                if (this.modelo.entregar_carta(this.modelo.get_jugador_actual(), 1)) {
                    this.modelo.get_jugador_actual().set_comio(true);
                }
            }
            else {
                if (e.getSource() == this.interfaz.get_carta_secreta()) {
                    System.out.println("BOTON SECRETA");
                    if (this.modelo.entregar_carta(this.modelo.get_jugador_actual(), 0)) {
                        this.modelo.get_jugador_actual().set_comio(true);
                    }
                }
            }
        }
    }
}
