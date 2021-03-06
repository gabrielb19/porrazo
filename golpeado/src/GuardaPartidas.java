import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

public class GuardaPartidas {
    public GuardaPartidas() {
    }

    public void escribir_en_archivo(Jugador jugador1, Jugador jugador2, Stack<Carta> mazo, Stack<Carta> comodines) {
        try {
            FileWriter archivo_con_partida = new FileWriter("../archivos_de_texto/partida.txt");
            // JUGADOR 1
            archivo_con_partida.write(jugador1.get_nombre() + '\n');
            archivo_con_partida.write(String.valueOf(jugador1.get_cartas().size()) + '\n');
            for (int i = 0; i < jugador1.get_cartas().size(); i += 1) {
                archivo_con_partida.write(jugador1.get_cartas().elementAt(i).get_imagen() + "\n"
                        + jugador1.get_cartas().elementAt(i).get_numero() + "\n"
                        + jugador1.get_cartas().elementAt(i).get_tipo() + '\n');
            }

            // JUGADOR 2
            archivo_con_partida.write(jugador2.get_nombre() + '\n');
            archivo_con_partida.write(String.valueOf(jugador2.get_cartas().size()) + '\n');
            for (int i = 0; i < jugador2.get_cartas().size(); i += 1) {
                archivo_con_partida.write(jugador2.get_cartas().elementAt(i).get_imagen() + "\n"
                        + jugador2.get_cartas().elementAt(i).get_numero() + "\n"
                        + jugador2.get_cartas().elementAt(i).get_tipo() + '\n');
            }

            archivo_con_partida.write(String.valueOf(mazo.size()) + '\n');
            for (int i = 0; i < mazo.size(); i += 1) {
                archivo_con_partida.write(mazo.elementAt(i).get_imagen() + "\n" + mazo.elementAt(i).get_numero() + "\n"
                        + mazo.elementAt(i).get_tipo() + '\n');
            }

            archivo_con_partida.write(String.valueOf(comodines.size()) + '\n');
            for (int i = 0; i < comodines.size(); i += 1) {
                archivo_con_partida.write(comodines.elementAt(i).get_imagen() + "\n"
                        + comodines.elementAt(i).get_numero() + "\n" + comodines.elementAt(i).get_tipo() + '\n');
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

    public Vector<String> getline() {
        String aux = "";
        Vector<String> vec = new Vector<String>();
        try {
            FileReader partida = new FileReader("../archivos_de_texto/partida.txt");
            int valor = partida.read();
            while (valor != -1) {
                if ((char)valor != '\n') {
                    aux += (char)valor;
                } else {
                    vec.add(aux);
                    aux = "";
                }
                valor = partida.read();
            }
            partida.close();
        } catch (IOException e) {
            System.out.println("Error cargando la partida");
            e.printStackTrace();
        }
        return vec;
    }

    public Vector<Object> cargarPartida(Vector<String> vectorString) {
        Vector<Object> vec = new Vector<Object>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Stack<Carta> mazo = new Stack<Carta>();
        Stack<Carta> comodines = new Stack<Carta>();

        String nombre = vectorString.elementAt(0);
        jugador1.set_nombre(nombre);
        //System.out.println(jugador1.get_nombre());
        int cantidadCartas = Integer.parseInt(vectorString.elementAt(1));
        int index1 = 2;
        for (int i = 0; i < cantidadCartas; i++) {
            Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 + 1)),
                    vectorString.elementAt(index1 + 2), vectorString.elementAt(index1));
            jugador1.recibir_carta(c);
            index1 += 3;
        }

        jugador2.set_nombre(vectorString.elementAt(index1));
        cantidadCartas = Integer.parseInt(vectorString.elementAt(index1 + 1));
        index1 += 2;
        for (int i = 0; i < cantidadCartas; i++) {
            Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 + 1)),
                    vectorString.elementAt(index1 + 2), vectorString.elementAt(index1));
            jugador2.recibir_carta(c);
            index1 += 3;
        }

        cantidadCartas = Integer.parseInt(vectorString.elementAt(index1));
        index1 += 1;
        for (int i = 0; i < cantidadCartas; i++) {
            Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 + 1)),
                    vectorString.elementAt(index1 + 2), vectorString.elementAt(index1));
            mazo.add(c);
            index1 += 3;
        }

        cantidadCartas = Integer.parseInt(vectorString.elementAt(index1));
        index1 += 1;
        for (int i = 0; i < cantidadCartas; i++) {
            Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 + 1)),
                    vectorString.elementAt(index1 + 2), vectorString.elementAt(index1));
            comodines.add(c);
            index1 += 3;
        }

        vec.add(jugador1);
        vec.add(jugador2);
        vec.add(mazo);
        vec.add(comodines);
        return vec;
    }
}