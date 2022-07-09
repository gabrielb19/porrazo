import javax.swing.*;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.Dimension;

public class Interfaz /* implements ActionListener */ {
    private JFrame loginFrame;
    private JFrame frame;
    private JFrame eleccion_frame;
    private Vector<JButton> mano_cartas;
    private Vector<JButton> mano_cartas_eleccion;
    private Vector<JButton> grupo1;
    private Vector<JButton> grupo2;
    private JButton carta_secreta;
    private JButton comodin;
    private JButton boton_reglas;
    private JButton boton_partida;
    private JButton boton_eleccion;
    private JButton boton_jugar;
    private JButton boton_cargar;
    private JButton deshacer_grupos;
    private JButton boton_ganar; 
    private JTextField nombre_jugador_1;
    private JTextField nombre_jugador_2;

    public Interfaz() {
        this.deshacer_grupos = new JButton("Deshacer grupos");
        this.boton_ganar = new JButton("Ganar!");
        this.frame = new JFrame();
        this.loginFrame = new JFrame();
        this.eleccion_frame = new JFrame();
        this.mano_cartas = new Vector<JButton>();
        this.mano_cartas_eleccion = new Vector<JButton>();
        this.carta_secreta = new JButton();
        this.comodin = new JButton();
        this.grupo1 = new Vector<JButton>();
        this.grupo2 = new Vector<JButton>();
    }

    public JButton crear_boton_carta(int x, int y, int width, int height, String imagen) {
        ImageIcon imageIcon = new ImageIcon(imagen);
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        JButton boton = new JButton((imageIcon));
        boton.setBounds(x, y, width, height);
        return boton;
    }

    public void set_imagen_boton(JButton boton, String imagen) {
        ImageIcon imageIcon = new ImageIcon(imagen);
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(boton.getWidth(), boton.getHeight(), java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        boton.setIcon(imageIcon);
    }

    public JButton boton_generico(int x, int y, int width, int height, String texto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, width, height);
        return boton;
    }

    public JTextField texto_jugador_1() {
        JTextField userTextField1 = new JTextField();
        userTextField1.setBounds(180, 200, 150, 30);
        return userTextField1;
    }

    public JTextField texto_jugador_2() {
        JTextField userTextField2 = new JTextField();
        userTextField2.setBounds(180, 250, 150, 30);
        return userTextField2;
    }

    public void pantalla_login() {
        JLabel texto = new JLabel();
        texto.setText("Bienvenido a Golpeado");
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        texto.setBounds(150, 20, 1000, 200);
        loginFrame.add(texto);

        JLabel texto1 = new JLabel();
        texto1.setText("Nombre Jugador 1");
        texto1.setForeground(Color.WHITE);
        texto1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        texto1.setBounds(180, 90, 1000, 200);
        loginFrame.add(texto1);

        JLabel texto2 = new JLabel();
        texto2.setText("Nombre Jugador 2");
        texto2.setForeground(Color.WHITE);
        texto2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        texto2.setBounds(180, 140, 1000, 200);
        loginFrame.add(texto2);

        this.boton_jugar = boton_generico(180, 300, 150, 50, "Jugar");
        this.loginFrame.add(boton_jugar);

        this.boton_cargar =  boton_generico(180, 360, 150, 50, "Cargar Partida");
        this.loginFrame.add(boton_cargar);

        this.loginFrame.setBounds(700, 200, 400, 400);
        this.nombre_jugador_1 = texto_jugador_1();
        this.nombre_jugador_2 = texto_jugador_2();

        this.loginFrame.add(nombre_jugador_1);
        this.loginFrame.add(nombre_jugador_2);

        loginFrame.getContentPane().setBackground(new Color(0, 100, 0));
        loginFrame.setResizable(false);
        loginFrame.setSize(500, 500);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
    }

    public void pantalla_principal(Vector<String> mano, String tope_desechadas, String titulo) {
        // Se crean botones de la mano de cartas
        int x = 600;
        JLabel texto1 = new JLabel();
        texto1.setText(titulo);
        texto1.setForeground(Color.WHITE);
        texto1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        texto1.setBounds(760, 10, 1000, 200);
        frame.add(texto1);
        for (int i = 0; i < mano.size(); i += 1) {
            JButton boton = crear_boton_carta(x, 300, 127, 200, mano.elementAt(i));
            this.mano_cartas.add(boton);
            this.frame.add(boton);
            x += 127;
        }

        this.boton_eleccion = boton_generico(981, 550, 127, 75, "Formar grupo");
        frame.add(boton_eleccion);

        this.boton_reglas = boton_generico(10, 10, 100, 50, "Reglas");
        frame.add(boton_reglas);

        this.boton_partida = boton_generico(1690, 10, 200, 50, "Guardar partida");
        frame.add(boton_partida);

        this.carta_secreta = crear_boton_carta(250, 300, 127, 200, "../imagenes/carta.png");
        frame.add(carta_secreta);

        this.comodin = crear_boton_carta(250, 650, 127, 200, tope_desechadas);
        frame.add(comodin);

        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0, 100, 0));
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void pantalla_eleccion_cartas() {
        int x = 40;
        int x2 = 40;
        JLabel titulo = new JLabel();
        titulo.setText("Seleccione las cartas para formar los grupos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        titulo.setBounds(170, 10, 1000, 200);
        this.eleccion_frame.add(titulo);

        for (int i = 0; i < 7; i += 1) {
            JButton boton = crear_boton_carta(x, 300, 127, 200, "");
            this.mano_cartas_eleccion.add(boton);
            this.eleccion_frame.add(boton);

            if (i == 4) {
                x2 += 50;
            }
            boton = crear_boton_carta(x2, 600, 95, 150, "");
            if (i < 4) {
                this.grupo1.add(boton);
            }
            else {
                this.grupo2.add(boton);
            }
            boton.setVisible(false);

            this.eleccion_frame.add(boton);

            x2 += 95;
            x += 127;
        }

        this.boton_ganar.setBounds(415, 675, 150,50);
        this.boton_ganar.setForeground(Color.RED);
        this.eleccion_frame.add(this.boton_ganar); 

        this.deshacer_grupos.setBounds(800, 675, 150,50);
        this.eleccion_frame.add(this.deshacer_grupos);

        this.eleccion_frame.getContentPane().setBackground(new Color(0, 100, 0));
        this.eleccion_frame.setBounds(500, 1000, 0, 400);
        this.eleccion_frame.setSize(1000, 1000);
        this.eleccion_frame.setLayout(null);
        this.eleccion_frame.setVisible(false);
    }

    public void reset(JFrame f) {
        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
    }

    public JFrame get_pantalla_principal() {
        return this.frame;
    }

    public JFrame get_pantalla_login() {
        return this.loginFrame;
    }

    public Vector<JButton> get_mano_cartas() {
        return this.mano_cartas;
    }

    public Vector<JButton> get_mano_cartas_eleccion() {
        return this.mano_cartas_eleccion;
    }

    public JButton get_comodin() {
        return this.comodin;
    }

    public JButton get_boton_reglas() {
        return this.boton_reglas;
    }

    public JButton get_boton_eleccion() {
        return this.boton_eleccion;
    }

    public JButton get_boton_ganar() {
        return this.boton_ganar;
    }

    public JButton get_boton_jugar() {
        return this.boton_jugar;
    }

    public JButton get_boton_cargar() {
        return this.boton_cargar;
    }

    public JButton get_carta_secreta() {
        return this.carta_secreta;
    }

    public JButton get_boton_partida() {
        return this.boton_partida;
    }

    public JButton get_boton_deshacer_grupos() {
        return this.deshacer_grupos;
    }

    public JTextField get_jugador_1() {
        return this.nombre_jugador_1;
    }

    public JTextField get_jugador_2() {
        return this.nombre_jugador_2;
    }

    public Vector<JButton> get_grupo1() {
        return this.grupo1;
    }

    public Vector<JButton> get_grupo2() {
        return this.grupo2;
    }

    public JFrame get_pantalla_eleccion() {
        return this.eleccion_frame;
    }

    public void desplegar_reglas() {
        Path filePath = Path.of("reglas.txt");
        String content = "";
        try {
            content = Files.readString(filePath, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            System.out.println("Error en cargar de archivo");
        }

        JOptionPane.showMessageDialog(null, content);
    }

}