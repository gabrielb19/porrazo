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
            if (!primera_iteracion) {
                comodin = this.modelo.get_comodines().peek().get_imagen();
            }   
            else {
                comodin = "imagenes/vacio.png";
            }

            this.interfaz.pantalla_principal(imagenes, comodin);
            this.interfaz = new Interfaz();
            
            this.modelo.cambiar_jugador_actual();
        }

    }

    public void actionPerformed(ActionEvent e) {
        int index = -1;
        for (int i = 0; i < this.interfaz.get_mano_cartas().size(); i += 1) {
            if (e.getSource() == this.interfaz.get_mano_cartas().elementAt(i)) {
                index = i;
                this.modelo.agregar_carta_comodines(this.modelo.get_jugador_actual().desechar_carta(index));
                break;
            }
        }
        if (index == -1) {
            if (e.getSource() == this.interfaz.get_comodin()) {
                this.modelo.entregar_carta(this.modelo.get_jugador_actual(), 1);
            }
            else {
                if (e.getSource() == this.interfaz.get_carta_secreta()) {
                    this.modelo.entregar_carta(this.modelo.get_jugador_actual(), 0);
                }
            }
        }

    }
}
