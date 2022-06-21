import java.util.Vector;

public class Main {
    public static void main(String [] args){
        Vector<String> imagenes = new Vector<String>();
        String nombre_imagen = "";
        for (int i = 6; i > 0; i -= 1) {
            nombre_imagen = "imagenes/"+i+"_of_spades.png";
            imagenes.add(nombre_imagen);
        }

        System.out.println(imagenes);
        
        Interfaz i = new Interfaz();
        i.pantalla_principal(imagenes, "imagenes/1_of_spades.png");

    }
}
