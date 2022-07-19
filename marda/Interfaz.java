import javax.swing.*;
import java.awt.Image;
import java.awt.Color;

public abstract class Interfaz {
    private JFrame lofinFrame; 
    private JFrame pantallaPrincipal; 

    public Interfaz() {
        this.lofinFrame = new JFrame();
        this.pantallaPrincipal = new JFrame(); 
    }

    /**
     * Metodo para crear boton 
     * @param x coordenada x
     * @param y coordenada y
     * @param width ancho
     * @param height altura
     * @param texto texto del boton
     */
    public JButton crearBoton(int x, int y, int width, int height, String texto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, width, height);
        return boton;
    }

    /**
     * Metodo para crear boton con color 
     * @param x coordenada x
     * @param y coordenada y
     * @param width ancho
     * @param height altura
     * @param texto texto del boton
     * @param color color del boton
     */
    public JButton crearBoton(int x, int y, int width, int height, String texto, Color color) { 
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, width, height);
        boton.setForeground(color);
        return boton;
    }

    /**
     * Metodo para crear boton de cartas
     * @param x coordenada x
     * @param y coordenada y
     * @param width ancho
     * @param height altura
     * @param imagen imagen de la carta
     */
    public JButton crearBotonCarta(int x, int y, int width, int height, String imagen) {
        ImageIcon imageIcon = new ImageIcon(imagen);
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); 
        imageIcon = new ImageIcon(newimg);
        JButton boton = new JButton((imageIcon));
        boton.setBounds(x, y, width, height);
        return boton;
    }

     /**
     * Agrega boton a un JFrame 
     * @param boton boton a agregar
     * @param frame frame
     */
    public void agregarBoton(JButton boton, JFrame frame) {
        frame.add(boton); 
    }

     /**
     * Agrega un texto a un JFrame
     * @param texto texto a agregar
     * @param frame frame
     */
    public void agregarTexto(JPanel texto, JFrame frame) {
        frame.add(texto); 
    }

     /**
     * Metodo generico para crear las pantallas 
     */
    public abstract void crearFrame(); 
}
