import javax.swing.*;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component; 

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

    public void setImagenCarta(String imagen, int width, int height, JButton boton) {
        ImageIcon imageIcon = new ImageIcon(imagen);
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); 
        imageIcon = new ImageIcon(newimg);
        
        boton.setIcon(imageIcon);
    }

    public JLabel crearLabel(String texto, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setText(texto);
        label.setBounds(x, y, width, height);
        
        return label; 
    }

    public JLabel crearLabel(String texto, int x, int y, int width, int height, Color color, Font fuente) {
        JLabel label = new JLabel();
        label.setText(texto);
        label.setForeground(color);
        label.setFont(fuente);
        label.setBounds(x, y, width, height);

        return label; 
    }

    public JTextField crearTextField(int x, int y, int height, int width) {
        JTextField userTextField = new JTextField();
        userTextField.setBounds(x, y, width, height);
        return userTextField;
    }

     /**
     * Agrega componente a un JFrame 
     * @param boton componente a agregar
     * @param frame frame
     */
    public void agregarComponente(Component componente, JFrame frame) {
        frame.add(componente); 
    }

    public JFrame crearPantalla(int x, int y, int height, int width) {
        JFrame pantalla = new JFrame(); 

        pantalla.setResizable(false);
        pantalla.setBounds(x, y, width, height);
        pantalla.setLayout(null);
        pantalla.setVisible(true);

        return pantalla; 
    }

    public JFrame crearPantalla(int x, int y, int height, int width, Color color) {
        JFrame pantalla = new JFrame(); 

        pantalla.setResizable(false);
        pantalla.setBounds(x, y, width, height);
        pantalla.setLayout(null);
        pantalla.setVisible(true);
        pantalla.getContentPane().setBackground(color);

        return pantalla; 
    }
 
}
