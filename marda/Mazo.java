import java.util.Vector;

public class Mazo extends Cartas{
    protected Vector<Cartas> mazo;

    /**
     * Constructor por omisión
     */
    public Mazo() {
        this.mazo = new Vector<Cartas>();
    }

    public boolean agregarAMazo(Cartas c) {
        return  this.mazo.add(c);
    }

    public Cartas sacarDeMazo(Cartas c) {
        Cartas sacada = null;
        for (int i = 0; i < this.mazo.size(); i += 1) {
            if (this.mazo.elementAt(i) == c) {
                sacada = this.mazo.remove(i);
                break;
            }
        }
        return sacada;
    }

    /**
     * Obtener las cartas presentes en el mazo
     * @return Cartas en estructura de datos de tipo Vector
     */
    public Vector<Cartas> getCartas() {
        return this.mazo;
    }

    /**
     * Retorna la carta presente en un índice del mazo
     * @param index indice de la carta a obtener
     * @return carta en del índice
     */
    public Cartas cartaEnPosicion(int index) {
        return this.mazo.elementAt(index);
    }
}
