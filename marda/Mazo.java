import java.util.Vector;

public class Mazo extends Cartas{
    protected Vector<Cartas> mazo;

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

    public Vector<Cartas> getCartas() {
        return this.mazo;
    }

    public Cartas cartaEnPosicion(int index) {
        return this.mazo.elementAt(index);
    }
}
