import java.io.IOException;
import java.util.Stack; 
import java.util.Vector;
import java.io.FileReader;

public class cargarJuego {
    public Vector<String> getline(){
         String aux = "";
         Vector<String> vec = new Vector<String>();
         try {
             FileReader partida = new FileReader("partida.txt");
             int valor=partida.read();
             while(valor!=-1){
                 if (valor != 13){
                  aux += (char)valor;
                 } else{
                    aux = aux.replace("\n", "");
                    vec.add(aux);
                    aux = "";
                 }
                 valor=partida.read(); 
             }
             partida.close();
         } catch (IOException e) {
             System.out.println("Error cargando la partida");
             e.printStackTrace();
         }
         return vec;
     }

    public Vector<Object> cargarPartida(Vector<String> vectorString){
        Vector<Object> vec = new Vector<Object>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Stack<Carta> mazo = new Stack<Carta>();
        Stack<Carta> comodines = new Stack<Carta>();

        jugador1.set_nombre(vectorString.elementAt(0));
        int cantidadCartas = Integer.parseInt(vectorString.elementAt(1));
        int index1 = 2;
        for (int i = 0; i < cantidadCartas; i++){
                Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 +1)), vectorString.elementAt(index1 +2), vectorString.elementAt(index1));
                jugador1.recibir_carta(c);
                index1 += 3;
        }

        jugador2.set_nombre(vectorString.elementAt(index1));
        cantidadCartas = Integer.parseInt(vectorString.elementAt(index1+1));
        index1 +=2;
        for (int i = 0; i < cantidadCartas; i++){
                Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 +1)), vectorString.elementAt(index1 +2), vectorString.elementAt(index1));
                jugador2.recibir_carta(c);
                index1 += 3;
        }

        cantidadCartas = Integer.parseInt(vectorString.elementAt(index1));
        index1 +=1;
        for (int i = 0; i < cantidadCartas; i++){
            Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 +1)), vectorString.elementAt(index1 +2), vectorString.elementAt(index1));
            mazo.add(c);
            index1 += 3;
        }

        cantidadCartas = Integer.parseInt(vectorString.elementAt(index1));
        index1 +=1;
        for (int i = 0; i < cantidadCartas; i++){
            Carta c = new Carta(Integer.parseInt(vectorString.elementAt(index1 +1)), vectorString.elementAt(index1 +2), vectorString.elementAt(index1));
            comodines.add(c);
            index1 += 3;
        }
        
        for(int i = 0; i < comodines.size(); i++){
            System.out.println(comodines.elementAt(i).get_imagen());
        }
        vec.add(jugador1);
        vec.add(jugador2);
        vec.add(mazo);
        vec.add(comodines);
        return vec;
    }
}
