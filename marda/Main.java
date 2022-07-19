import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        MazoIngles m = new MazoIngles();
        Vector<Cartas> cartas = m.getCartas();
        Carta carta = null;
        for (int i = 0; i < cartas.size(); i += 1) {
            carta = (Carta)cartas.elementAt(i);
            System.out.println(""+ i +": "+carta.getImagen());

        }
    }
}
