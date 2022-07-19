import java.awt.event.*;

import javax.swing.JButton;

public abstract class Controlador implements ActionListener {
    protected Interfaz interfaz;
    protected Mesa mesa;

    /**
     * Constructor por parámetros
     * @param interfaz interfaz del controlador
     * @param mesa mesa del controlador
     */
    public Controlador(Interfaz interfaz, Mesa mesa) {
        this.mesa = mesa;
        this.interfaz = interfaz;
    }

    /**
     * Agrega un actionListener a un botón obtenido de la interfaz
     * @param b botón al que se le agrega el actionListener
     */
    public void agregarActionListener(JButton b) {
        b.addActionListener(this);
    }

    /**
     * Determina que acción se toma cuando se acciona un botón
     */
    public abstract void actionPerformed(ActionEvent e);

    /**
     * Inicia el juego
     */
    public abstract void iniciarJuego();
}
