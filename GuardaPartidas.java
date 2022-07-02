import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack; 

public class GuardaPartidas {

    public static void escribir_en_archivo(Jugador jugador1, Jugador jugador2, Stack<Carta> mazo, Stack<Carta> comodines) {
        
        String str = ""; 
        try {
            FileWriter archivo_con_partida = new FileWriter("Estado_de_partida_anterior.txt");
            archivo_con_partida.write(str);
            archivo_con_partida.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
