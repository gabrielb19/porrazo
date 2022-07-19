import java.awt.event.*;

import javax.swing.JButton;

public class ControladorPorrazo extends Controlador {
    private JugadorPorrazo j1;
    private JugadorPorrazo j2;

    public ControladorPorrazo() {
        this.j1 = new JugadorPorrazo("jugador1");
        this.j2 = new JugadorPorrazo("jugador2");
        this.mesa = new Mesa();
        this.interfaz = new Interfaz();
    }

    public ControladorPorrazo(String j1, String j2) {
        this.j1 = new JugadorPorrazo(j1);
        this.j2 = new JugadorPorrazo(j2);
        this.mesa = new Mesa();
        this.interfaz = new Interfaz();
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void agregarActionListener(JButton boton) {
        boton.addActionListener(this);
    }

    public void iniciarJuego() {
        
    }
}
