import java.util.Vector;

public class Main {
    public static void main(String[] args) {
       /*Vector<Carta> grupo1 = new Vector<Carta>();
        Carta c1 = new Carta(1,"diamante", "");
        grupo1.add(c1);
        Carta c2 = new Carta(2,"diamante", "");
        grupo1.add(c2);
        Carta c3 = new Carta(3,"diamante", "");
        grupo1.add(c3);
        Carta c4 = new Carta(4,"diamante", "");
        grupo1.add(c4);



        Vector<Carta> grupo2 = new Vector<Carta>();
        Carta c5 = new Carta(1,"pica", "");
        grupo2.add(c5);
        Carta c6 = new Carta(1,"diamante", "");
        grupo2.add(c6);
        Carta c7 = new Carta(1,"trebol", "");
        grupo2.add(c7);*/

        Controlador c = new Controlador();
        /*Mesa m = new Mesa();
        System.out.println(m.puede_ganar(grupo1, grupo2));*/
        c.iniciar_juego();
    }
}
