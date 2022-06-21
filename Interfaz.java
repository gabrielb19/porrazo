import javax.swing.*;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.*;

public class Interfaz implements ActionListener {
    private JFrame frame;
    private Vector<JButton> botones;
    
    public Interfaz(){
        this.frame = new JFrame();
        this.botones = new Vector<JButton>();
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

    public Vector<JButton> pantalla_principal(Vector<String> mano, String tope_desechadas) {
        Vector<JButton> mano_cartas = new Vector<JButton>();
        JButton carta_secreta;
        JButton carta_comestible;
        JFrame frame = new JFrame(); 

        // Se crean botones de la mano de cartas
        int x = 600;
        for (int i = 0; i < mano.size(); i += 1) {
            JButton boton = crear_boton_carta(x, 100, 127, 200, mano.elementAt(i));
            mano_cartas.add(boton);
            frame.add(boton);
            x += 127;
        }

        carta_secreta = crear_boton_carta(250, 100, 127, 200, "imagenes/carta.png");
        frame.add(carta_secreta);

        carta_comestible = crear_boton_carta(250, 450, 127, 200, tope_desechadas);
        frame.add(carta_comestible);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLayout(null);

    
        frame.setVisible(true);

        return mano_cartas;
    }

    public void actionPerformed(ActionEvent e){
        if (e) {

        }
    }


}