import java.awt.event.*;

import javax.swing.JButton;

public abstract class Controlador implements ActionListener{
    protected Interfaz interfaz;
    protected Mesa mesa;

    public Controlador(Interfaz interfaz, Mesa mesa) {
        this.mesa = mesa;
        this.interfaz = interfaz;
    }

    public Controlador() {
        this.mesa = new Mesa();
        this.interfaz = new Interfaz();
    }

    public abstract void agregarActionListener(JButton b); 

    public abstract void actionPerformed(ActionEvent e);

    public abstract void iniciarJuego();
}
