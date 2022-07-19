import java.util.Vector;

public class Mesa {

    public void repartirCartas(Vector<Jugador> jugadores, Mazo m, int cantidad) {
        for (int i = 0; i < jugadores.size(); i +=1 ) {
            for (int j = 0; j < cantidad; j += 1) {
                jugadores.elementAt(i).recibirCarta(m.sacarDeMazo(m.cartaEnPosicion(0)));
            }
        }
    }

    private int numero_aleatorio(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private boolean pertenece(Vector<Integer> nums, int num) {
        boolean pertenece = false;
        for (int i = 0; i < nums.size(); i += 1) {
            if (nums.elementAt(i) == num) {
                pertenece = true;
                break;
            }
        }
        return pertenece;
    }

    public void barajarMazo(Mazo m) {
        Mazo mazoBarajado = new Mazo();
        Vector<Integer> barajadas = new Vector<Integer>();
        int index = 0;
        for (int i = 0; i < 52; i += 1) {
            while (pertenece(barajadas, index = numero_aleatorio(0, m.getCartas().size())));
            barajadas.add(index);
            mazoBarajado.agregarAMazo(m.cartaEnPosicion(index));
        }
        m = mazoBarajado;
    }
}
