import javax.swing.*;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class InterfazPorrazo extends Interfaz {
    
    private JFrame loginFrame; 
    private JFrame pantallaPrincipal; 
    private JFrame eleccionFrame;
    private Vector<JButton> manoCartas;
    private Vector<JButton> manoCartasEleccion;
    private Vector<JButton> grupo1;
    private Vector<JButton> grupo2;
    private JButton cartaSecreta;
    private JButton comodin;
    private JButton botonReglas;
    private JButton botonPartida;
    private JButton botonEleccion;
    private JButton botonJugar;
    private JButton botonCargar;
    private JButton deshacerGrupos;
    private JButton botonVerificarGrupos;
    private JTextField nombreJugador1;
    private JTextField nombreJugador2;

    public InterfazPorrazo() {
        this.loginFrame = crearPantalla(500, 200, 400, 400, new Color(0, 100, 0)); 
        this.pantallaPrincipal = crearPantalla(760, 10, 1000, 200, new Color(0, 100, 0));
        this.eleccionFrame = crearPantalla(170, 10, 1000, 200, new Color(0, 100, 0)); 
        this.cartaSecreta = crearBoton(250, 300, 127, 200, "../imagenes/carta.png"); 
        this.comodin = crearBoton(250, 650, 127, 200, " ");
        this.botonReglas = crearBoton(10, 10, 100, 50, "Reglas"); 
        this.botonPartida = crearBoton(1690, 10, 200, 50, "Guardar partida"); 
        this.botonEleccion = crearBoton(981, 550, 127, 75, "Formar grupo"); 
        this.botonJugar = crearBoton(180, 300, 150, 50, "Jugar"); 
        this.botonCargar = crearBoton(180, 360, 150, 50, "Cargar Partida");
        this.deshacerGrupos = crearBoton(800, 675, 150, 50, "Deshacer grupos"); 
        this.botonVerificarGrupos = crearBoton(425, 800, 150, 50, "Verificar grupos"); 
        this.nombreJugador1 = crearTextField(180, 200, 150, 30);
        this.nombreJugador2 = crearTextField(180, 250, 150, 30);


        this.manoCartas = new Vector<JButton>(); 

        int x = 600;
        for (int i = 0; i < 8; i += 1) {
            JButton boton = crearBoton(x, 300, 127, 200, " ");
            this.manoCartas.add(boton);
            agregarComponente(boton, pantallaPrincipal);
            x += 127;
        }

        this.grupo1 = new Vector<JButton>();
        this.grupo2 = new Vector<JButton>();
        this.manoCartasEleccion = new Vector<JButton>(); 

        int x1 = 40;
        int x2 = 40;
        for (int i = 0; i < 7; i += 1) {
            JButton boton = crearBoton(x1, 300, 127, 200, "");
            this.manoCartasEleccion.add(boton);
            agregarComponente(boton, eleccionFrame);

            if (i == 4) {
                x2 += 50;
            }
            boton = crearBoton(x2, 600, 95, 150, "");
            if (i < 4) {
                this.grupo1.add(boton);
            }
            else {
                this.grupo2.add(boton);
            }
            boton.setVisible(false);

            this.eleccionFrame.add(boton);

            x2 += 95;
            x1 += 127;
        }
    }

    public void pantallaLogin() {

        JLabel textoBienvenido = crearLabel("Bienvenido a Golpeado", 150, 20, 1000, 200, Color.WHITE, new Font(Font.SANS_SERIF, Font.BOLD, 20)); 
        agregarComponente(textoBienvenido, loginFrame);

        JLabel textoJ1 = crearLabel("Nombre Jugador 1", 180, 90, 1000, 200, Color.WHITE, new Font(Font.SANS_SERIF, Font.BOLD, 10)); 
        agregarComponente(textoJ1, loginFrame);

        JLabel textoJ2 = crearLabel("Nombre Jugador 2", 180, 140, 1000, 200, Color.WHITE, new Font(Font.SANS_SERIF, Font.BOLD, 10)); 
        agregarComponente(textoJ2, loginFrame);

        agregarComponente(this.botonJugar, loginFrame);

        agregarComponente(botonCargar, loginFrame);

        agregarComponente(this.nombreJugador1, loginFrame);

        agregarComponente(this.nombreJugador2, loginFrame);
    }

    public void pantallaPrincipal(Vector<String> mano, String tope_desechadas, String titulo) {
        
        JLabel titulo1 = crearLabel(titulo, 760, 10, 1000, 200, Color.WHITE, new Font(Font.SANS_SERIF, Font.BOLD, 30));
        agregarComponente(titulo1, pantallaPrincipal);
        
        // Se agregan imagenes a las cartas de la mano
    
        for (int i = 0; i < mano.size(); i += 1) {
            setImagenCarta(mano.elementAt(i), 127, 200, this.manoCartas.get(i)); 
        }

        agregarComponente(this.botonEleccion, this.pantallaPrincipal);

        agregarComponente(this.botonReglas, this.pantallaPrincipal);

        agregarComponente(this.botonPartida, this.pantallaPrincipal);

        agregarComponente(this.cartaSecreta, this.pantallaPrincipal);

        setImagenCarta(tope_desechadas, 127, 200, comodin);
    
        agregarComponente(this.comodin, this.pantallaPrincipal); // 
    }

    public void pantalla_eleccion_cartas() {
        // int x = 40;
        // int x2 = 40;
        JLabel tituloEleccion = crearLabel("Seleccione las cartas para formar los grupos", 170, 10, 1000, 200, Color.WHITE, new Font(Font.SANS_SERIF, Font.BOLD, 30));
        agregarComponente(tituloEleccion, eleccionFrame);

        // for (int i = 0; i < 7; i += 1) {
        //     JButton boton = crear_boton_carta(x, 300, 127, 200, "");
        //     this.mano_cartas_eleccion.add(boton);
        //     this.eleccion_frame.add(boton);

        //     if (i == 4) {
        //         x2 += 50;
        //     }
        //     boton = crear_boton_carta(x2, 600, 95, 150, "");
        //     if (i < 4) {
        //         this.grupo1.add(boton);
        //     }
        //     else {
        //         this.grupo2.add(boton);
        //     }
        //     boton.setVisible(false);

        //     this.eleccion_frame.add(boton);
        //     this.eleccion_frame.setBounds(500, 0, 400, 400);

        //     x2 += 95;
        //     x += 127;
        // }
        agregarComponente(botonVerificarGrupos, eleccionFrame);

        agregarComponente(deshacerGrupos, eleccionFrame);
    }

    public void pantalla_ganar(boolean gano) {
        if (gano) {
            JOptionPane.showMessageDialog(null, "FELICIDADES! Has ganado");
            
        }
        else {
            JOptionPane.showMessageDialog(null, "Con los grupos que formaste no puedes ganar");
        }
    }

    public void impedimento_eleccion() {
        JOptionPane.showMessageDialog(null,"No puedes formar grupos aún, debes botar una carta primero");
    }

    public void reset(JFrame f) {
        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
    }

    public JFrame getPantallaPrincipal() {
        return this.pantallaPrincipal;
    }

    public JFrame getPantallaLogin() {
        return this.loginFrame;
    }

    public Vector<JButton> getManoCartas() {
        return this.manoCartas;
    }

    public Vector<JButton> getManoCartasEleccion() {
        return this.manoCartasEleccion;
    }

    public JButton getComodin() {
        return this.comodin;
    }

    public JButton getBotonReglas() {
        return this.botonReglas;
    }

    public JButton getBotonEleccion() {
        return this.botonEleccion;
    }

    public JButton getBotonJugar() {
        return this.botonJugar;
    }

    public JButton getBotonCargar() {
        return this.botonCargar;
    }

    public JButton getCartaSecreta() {
        return this.cartaSecreta;
    }

    public JButton getBotonVerificarGrupos() {
        return this.botonVerificarGrupos;
    }

    public JButton getBotonPartida() {
        return this.botonPartida;
    }

    public JButton getBotonDeshacerGrupos() {
        return this.deshacerGrupos;
    }

    public JTextField getJugador1() {
        return this.nombreJugador1;
    }

    public JTextField getJugador2() {
        return this.nombreJugador2;
    }

    public Vector<JButton> getGrupo1() {
        return this.grupo1;
    }

    public Vector<JButton> getGrupo2() {
        return this.grupo2;
    }

    public JFrame getPantallaEleccion() {
        return this.eleccionFrame;
    }

    public void desplegar_reglas() {
        Path filePath = Path.of("../archivos_de_texto/reglas.txt");
        String content = "";
        try {
            content = Files.readString(filePath, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            System.out.println("Error en cargar de archivo");
        }

        JOptionPane.showMessageDialog(null, content);
    }

}
