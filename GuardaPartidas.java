import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack; 
import java.util.Vector;

public class GuardaPartidas {
    public GuardaPartidas() {}

    public void escribir_en_archivo(Jugador jugador1, Jugador jugador2, Stack<Carta> mazo, Stack<Carta> comodines) {
        try {
            FileWriter archivo_con_partida = new FileWriter("partida.txt");
            // JUGADOR 1 
            archivo_con_partida.write(jugador1.get_nombre()+'\n');
            archivo_con_partida.write(String.valueOf(jugador1.get_cartas().size())+'\n');
            for (int i = 0; i < jugador1.get_cartas().size(); i += 1) {
                archivo_con_partida.write(jugador1.get_cartas().elementAt(i).get_imagen()+"\n" + jugador1.get_cartas().elementAt(i).get_numero()+ "\n"+ jugador1.get_cartas().elementAt(i).get_tipo()+'\n');
            }

            // JUGADOR 2
            archivo_con_partida.write(jugador2.get_nombre()+'\n');
            archivo_con_partida.write(String.valueOf(jugador2.get_cartas().size())+'\n');
            for (int i = 0; i < jugador2.get_cartas().size(); i += 1) {
                archivo_con_partida.write(jugador2.get_cartas().elementAt(i).get_imagen()+"\n" + jugador2.get_cartas().elementAt(i).get_numero()+ "\n"+ jugador2.get_cartas().elementAt(i).get_tipo()+'\n');
            }

            archivo_con_partida.write(String.valueOf(mazo.size())+'\n');
            for (int i = 0; i < mazo.size(); i += 1) {
                archivo_con_partida.write(mazo.elementAt(i).get_imagen()+"\n" + mazo.elementAt(i).get_numero()+ "\n"+ mazo.elementAt(i).get_tipo()+'\n');
            }

            archivo_con_partida.write(String.valueOf(comodines.size())+'\n');
            for (int i = 0; i < comodines.size(); i += 1) {
                archivo_con_partida.write(comodines.elementAt(i).get_imagen()+"\n" + comodines.elementAt(i).get_numero()+ "\n"+ comodines.elementAt(i).get_tipo()+'\n');
            }

            archivo_con_partida.close(); 
        } catch (IOException e) {
            System.out.println("Error encontrado con el archivo");
            e.printStackTrace();
        }
    }

    public Vector<Object> leer_jugador() {
        Vector<Object> vec = new Vector<Object>();
        return vec;
    }
}