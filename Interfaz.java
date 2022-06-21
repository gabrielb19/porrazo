import javax.swing.*;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.*;

public class Interfaz /*implements ActionListener*/ {
    private JFrame frame;
    private Vector<JButton> mano_cartas;
    private JButton carta_secreta;
    private JButton comodin;
    
    public Interfaz(){
        this.frame = new JFrame();
        this.mano_cartas = new Vector<JButton>();
        this.carta_secreta = new JButton();
        this.comodin = new JButton();
    }

    public JButton crear_boton_carta(int x, int y, int width, int height, String imagen) {
        ImageIcon imageIcon = new ImageIcon(imagen);
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        JButton boton = new JButton((imageIcon));
        boton.setBounds(x,y,width,height);
        return boton;
    }

    public void pantalla_principal(Vector<String> mano, String tope_desechadas) {
        // Se crean botones de la mano de cartas
        int x = 600;
        for (int i = 0; i < mano.size(); i += 1) {
            JButton boton = crear_boton_carta(x, 100, 127, 200, mano.elementAt(i));
            this.mano_cartas.add(boton);
            this.frame.add(boton);
            x += 127;
        }

        this.carta_secreta = crear_boton_carta(250, 100, 127, 200, "imagenes/carta.png");
        frame.add(carta_secreta);

        this.comodin = crear_boton_carta(250, 450, 127, 200, tope_desechadas);
        frame.add(comodin);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLayout(null);

    
        frame.setVisible(true);

    }

    public void reset() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public Vector<JButton> get_mano_cartas() {
        return this.mano_cartas;
    }

    public JButton get_comodin() {
        return this.comodin;
    }

    public JButton get_carta_secreta() {
        return this.carta_secreta;
    }

    /* public void actionPerformed(ActionEvent e){
        if (e) {

        }
    }*/


}