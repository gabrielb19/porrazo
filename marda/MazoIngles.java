import java.util.Vector;

public class MazoIngles extends Mazo {

    /**
     * Constructor por omisión 
     * */  
    public MazoIngles() {
        this.mazo = new Vector<Cartas>();
        for (int i = 1; i < 14; i += 1) {
            agregarAMazo(new Carta(i, "corazon", "../imagenes/" + i + "_of_hearts.png"));
            agregarAMazo(new Carta(i, "pica", "../imagenes/" + i + "_of_spades.png"));
            agregarAMazo(new Carta(i, "trebol", "../imagenes/" + i + "_of_clubs.png"));
            agregarAMazo(new Carta(i, "rombo", "../imagenes/" + i + "_of_diamonds.png"));
        }
    }
}
